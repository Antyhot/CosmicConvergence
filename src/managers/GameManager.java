package managers;

import gameObjects.*;
import gameObjects.UI.DebugWindow;
import gameObjects.UI.Grid;
import gameObjects.UI.ScoreCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

/**
 * The GameManager class manages the game logic and rendering.
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

    final TitleScreen titleScreen = new TitleScreen(this);

    //Two arrays are used to avoid concurrent modification exceptions
    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    final ArrayList<GameObject> pendingGameObjects = new ArrayList<>();

    Player player;
    Camera camera = new Camera(this);
    DebugWindow debugWindow;
    ScoreCounter scoreCounter;

    Thread gameThread;
    InputHandler inputHandler = new InputHandler(this);
    PhysicsManager physicsManager = new PhysicsManager();
    private boolean initialized = false;

    /**
     * Constructor for the GameManager class.
     */
    public GameManager() {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.BLACK);
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
     * This method initializes the game by creating player, a debug window, score counter,
     * and initializing game objects.
     * It also clears the existing game objects and physics objects,
     * and adds the grid and player objects to the game objects list.
     * Finally, it initializes the camera and sets the initialized flag to true.
     */
    public void init() {
        this.player = new Player(this, inputHandler);
        this.debugWindow = new DebugWindow(this);
        this.scoreCounter = new ScoreCounter(this);

        this.gameObjects.clear();
        this.physicsManager.physicsObjects.clear();

        this.gameObjects.add(new Grid(this));
        this.gameObjects.add(player);

        this.camera.init(player);

        for (GameObject object : this.gameObjects) {
            object.init();
        }

        this.initialized = true;
    }

    /**
     * Runs the game.
     * This method is the main loop of the game.
     * It initializes the game using the init() method, and then enters a while loop.
     * The loop continues as long as the game thread is not null and the GAME_RUNNING flag is true.
     */
    @Override
    public void run() {
        this.init();

        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        long asteroidDelay = (long) 1e9;
        long lastAsteroidSpawn = System.nanoTime();

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

            asteroidDelay = Math.max(100000000, asteroidDelay - 1);
            if (System.nanoTime() - lastAsteroidSpawn >= asteroidDelay) {
                this.pendingGameObjects.add(new Asteroid(this));
                lastAsteroidSpawn = System.nanoTime();
            }

            if (timer >= 1e9 && !PAUSED) {
                drawCount = 0;
                timer = 0;

                for (int i = 0; i < 5; i++) {
                    this.pendingGameObjects.add(new Cell(this, Math.random() * 50 + 50));
                }


            }
        }
    }


    /**
     * Updates the game state based on the elapsed time since the last update.
     */
    public void update(double delta) {
        if (this.player.isDead()) {
            GAME_RUNNING = false;

            this.gameThread = null;
            this.titleScreen.setVisible(true);
            this.titleScreen.displayScore(this.player.score);
            this.repaint();

            return;
        }

        this.scoreCounter.update(delta);

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
     * The paintComponent method is responsible for rendering the game graphics on the screen.
     *
     * @param g the Graphics object to be used for rendering
     */
    public void paintComponent(Graphics g) {

        if (!this.initialized) return;

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

    /**
     * Toggles pause.
     */
    public void togglePause() {
        PAUSED = !PAUSED;
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
     * Toggles debug mode.
     */
    public void toggleDebug() {
        DEBUG = !DEBUG;
    }

    public int getScore() {
        return this.player.score;
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
