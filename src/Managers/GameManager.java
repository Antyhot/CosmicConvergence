package Managers;

import GameObjects.Camera;
import GameObjects.GameObject;
import GameObjects.Player;
import GameObjects.Cell;
import GameObjects.DebugWindow;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * GameManager class for the game.
 */
public class GameManager extends JPanel implements Runnable {
    //DEBUG SETTINGS
    public static boolean DEBUG = false;
    public static boolean PAUSED = false;
    public int drawCount = 0;



    // SCREEN SETTINGS
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    static final int FPS = 60;

    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    Camera camera = new Camera(this);
    DebugWindow debugWindow = new DebugWindow(this);

    Thread gameThread;
    InputHandler inputHandler = new InputHandler(this);
    PhysicsManager physicsManager = new PhysicsManager();


    /**
     * Constructor for the GameManager class.
     */
    public GameManager() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.addMouseListener(this.inputHandler);
        this.addMouseMotionListener(this.inputHandler);
        this.addKeyListener(this.inputHandler);


    }

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * Initializes the game.
     */
    public void init() {
        Player player = new Player(this, inputHandler);
        DebugWindow debugWindow = new DebugWindow(this);

        this.gameObjects.add(camera);
        this.gameObjects.add(player);

        for (int i = 0; i < 100; i++) {
            this.gameObjects.add(new Cell(this));
        }

        this.gameObjects.add(debugWindow);

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

        while (this.gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                if (!PAUSED) {
                    this.update();
                    this.repaint();
                }
                delta--;
                drawCount++;
            }

            if (timer >= 1e9) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Updates the game.
     */
    public void update() {

        System.out.println("Updating");
        this.physicsManager.handleCollisions();
        for (GameObject object : this.gameObjects) {
            object.update();
        }

        this.gameObjects.removeIf(object -> !object.isActive());
    }

    /**
     * Paints the game.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        for (GameObject object : this.gameObjects) {
            object.draw(g2d);
        }

        // mouse position
        g2d.setColor(Color.WHITE);
        g2d.drawOval(
            (int) (this.inputHandler.getMousePosition().getX() - 10),
            (int) (this.inputHandler.getMousePosition().getY() - 10),
                10 * 2,
                10 * 2
        );

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

        // System.out.println("PAUSED: " + PAUSED);
    }

    /**
     * Toggles debug mode.
     */
    public void toggleDebug() {
        DEBUG = !DEBUG;

        // System.out.println("DEBUG: " + DEBUG);
    }
}
