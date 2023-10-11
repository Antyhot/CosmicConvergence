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
    public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    Thread gameThread;

    /**
     * Constructor for the GameManager class.
     */
    public GameManager() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
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
        GameObject object = new GameObject();
        this.gameObjects.add(object);

        for (GameObject gameObject : this.gameObjects) {
            gameObject.init();
        }
    }

    @Override
    public void run() {
        this.init();
        while (this.gameThread != null) {
            this.update();
            this.repaint();
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
