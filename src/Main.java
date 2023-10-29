import javax.swing.JFrame;
import managers.GameManager;

/**
 * The Main class is responsible for starting the game by creating a JFrame
 * window and adding a GameManager instance to it.
 * The game window is set to a fixed size of SCREEN_WIDTH and SCREEN_HEIGHT.
 */
public class Main {

    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 800;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Cosmic Convergence");

        GameManager gameManager = new GameManager();

        window.add(gameManager);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
