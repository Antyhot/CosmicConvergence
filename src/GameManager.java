import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Thread;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * GameManager class for the game.
 */
public class GameManager extends JPanel implements Runnable {
    // SCREEN SETTINGS
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;

    static final int FPS = 60;

    public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    Camera camera = new Camera(this);

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

        this.addMouseListener(this.inputHandler);
        this.addMouseMotionListener(this.inputHandler);
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

        this.gameObjects.add(camera);
        this.gameObjects.add(player);

        for (int i = 0; i < 100; i++) {
            this.gameObjects.add(new Cell(this));
        }

        camera.init(player);
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
        long drawCount = 0;

        while (this.gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                this.update();
                this.repaint();
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
            (int) (this.inputHandler.getMousePosition().x - 10),
            (int) (this.inputHandler.getMousePosition().y - 10),
            (int) (10 * 2),
            (int) (10 * 2)
        );

        g2d.dispose();
    }
}
