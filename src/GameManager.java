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
    final int screenWidth = 800;
    final int screenHeight = 600;

    final int FPS = 60;

    public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    Thread gameThread;
    InputHandler inputHandler = new InputHandler();

    /**
     * Constructor for the GameManager class.
     */
    public GameManager() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
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
        Player player = new Player(inputHandler);
        this.gameObjects.add(player);
        
        for (GameObject object : this.gameObjects) {
            object.init();
        }
    }

    @Override
    public void run() {
        this.init();

        double drawInterval = 1e9 / this.FPS;
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
        for (GameObject object : this.gameObjects) {
            object.update();
        }
    }

    /**
     * Paints the game.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        for (GameObject object : this.gameObjects) {
            object.draw(g2d);
            g2d.dispose();
        }
    }
}
