import javax.swing.JFrame;

/**
 * Main class for the game.
 */
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Cosmic Convergence");

        GameManager gameManager = new GameManager();
        window.add(gameManager);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameManager.startGameThread();
    }
}
