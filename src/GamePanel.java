import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Thread;
import javax.swing.JPanel;

/**
 * GamePanel class for the game.
 */
public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int screenWidth = 800;
    final int screenHeight = 600;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {
        while (this.gameThread != null) {
            this.update();
            this.repaint();
        }
    }

    public void update() {
        System.out.println("Game loop running...");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
    }
}
