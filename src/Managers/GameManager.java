package Managers;

import GameObjects.*;
import GameObjects.UI.DebugWindow;
import GameObjects.UI.Grid;
import GameObjects.UI.ScoreCounter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * GameManager class for the game.
 */
public class GameManager extends JPanel implements Runnable, ComponentListener {
    public static boolean GAME_RUNNING = false;

    //DEBUG SETTINGS
    public static boolean DEBUG = false;
    public static boolean PAUSED = false;
    public int drawCount = 0;

    // SCREEN SETTINGS
    public int screenWidth = 1200;
    public int screenHeight = 800;

    static final int FPS = 60;

    TitleScreen titleScreen = new TitleScreen(this);

    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    ArrayList<GameObject> pendingGameObjects = new ArrayList<>();

    Player player;
    Camera camera = new Camera(this);
    DebugWindow debugWindow;
    ScoreCounter scoreCounter;

    Thread gameThread;
    InputHandler inputHandler = new InputHandler(this);
    PhysicsManager physicsManager = new PhysicsManager();

    /**
     * Constructor for the GameManager class.
     */
    public GameManager() {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.BLACK);
        // TODO: check if this is really needed. It seems that the game works better without it.
        // this.setDoubleBuffered(true);

        this.addMouseListener(this.inputHandler);
        this.addMouseMotionListener(this.inputHandler);
        this.addKeyListener(this.inputHandler);
        this.addComponentListener(this);

        // Set the layout to GridBagLayout
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH; // Fill the available space
        c.weightx = 1.0; // Take up all horizontal space
        c.weighty = 1.0; // Take up all vertical space
        this.add(titleScreen, c); // Add the titleScreen with the constraints
    }

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        GAME_RUNNING = true;
        this.setFocusable(true);
        this.requestFocusInWindow();
    
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * Initializes the game.
     */
    public void init() {
        this.player = new Player(this, inputHandler);
        this.debugWindow = new DebugWindow(this);
        this.scoreCounter = new ScoreCounter(this);

        this.gameObjects.clear();
        this.gameObjects.add(new Grid(this));
        this.gameObjects.add(player);

        this.camera.init(player);

        for (GameObject object : this.gameObjects) {
            object.init();
        }
    }

    @Override
    public void run() {
        this.init();

        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (this.gameThread != null && GAME_RUNNING) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                if (!PAUSED) {
                    this.update(delta);
                    this.repaint();
                }
                delta--;
                drawCount++;
            }

            if (timer >= 1e9 && !PAUSED) {
                drawCount = 0;
                timer = 0;

                for (int i = 0; i < 5; i++) {
                    this.pendingGameObjects.add(new Cell(this, Math.random() * 50 + 50));
                }

                this.pendingGameObjects.add(new Asteroid(this));

            }
        }
    }


    /**
     * Updates the game.
     */
    public void update(double delta) {
        if (this.player.isDead()) {
            GAME_RUNNING = false;
            this.gameThread = null;
            this.titleScreen.setVisible(true);
            this.repaint();

            return;
        }

        this.camera.update(delta);

        this.physicsManager.handleCollisions();
        ArrayList<GameObject> gameObjectsCopy = new ArrayList<>(this.gameObjects);
        for (GameObject gameObject : gameObjectsCopy) {
            gameObject.update(delta);
        }

        for (GameObject gameObject : this.pendingGameObjects) {
            gameObject.init();
        }
        this.gameObjects.addAll(this.pendingGameObjects);
        this.pendingGameObjects.clear();

        this.gameObjects.removeIf(object -> !object.isActive());
    }

    /**
     * Paints the game.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (DEBUG) {
            this.debugWindow.draw(g2d);
        }

        Graphics2D g2dCopy = (Graphics2D) g.create();
        this.camera.draw(g2d);

        ArrayList<GameObject> copy = new ArrayList<>(this.gameObjects);
        for (GameObject object : copy) {
            object.draw(g2d);
        }

        if (DEBUG) {
            // mouse position
            g2d.setColor(Color.WHITE);
            g2d.drawOval(
                (int) (this.inputHandler.getMousePosition().getX() - 10),
                (int) (this.inputHandler.getMousePosition().getY() - 10),
                10 * 2,
                10 * 2
            );
        }

        this.scoreCounter.draw(g2dCopy);

        g2d.dispose();
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public PhysicsManager getPhysicsManager() {
        return physicsManager;
    }

    public void setPhysicsManager(PhysicsManager physicsManager) {
        this.physicsManager = physicsManager;
    }

    public boolean getDebug() {
        return DEBUG;
    }

    /**
     * Toggles pause.
     */
    public void togglePause() {
        PAUSED = !PAUSED;
    }

    /**
     * Toggles debug mode.
     */
    public void toggleDebug() {
        DEBUG = !DEBUG;
    }

    public double getScore() {
        return this.player.calcTotalScore();
    }

    public int getDrawCount() {
        return drawCount;
    }

    public ArrayList<GameObject> getPendingGameObjects() {
        return pendingGameObjects;
    }

    // ComponentListener methods
    @Override
    public void componentResized(ComponentEvent e) {
        // Update the screenWidth and screenHeight variables
        this.screenWidth = this.getWidth();
        this.screenHeight = this.getHeight();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // Do nothing
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // Do nothing
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // Do nothing
    }
}
