package GameObjects.UI;

import GameObjects.GameObject;
import GameObjects.Player;
import Managers.GameManager;
import java.awt.*;

/**
 * ScoreCounter class.
 */
public class ScoreCounter extends GameObject {
    Player player;
    
    static final int WIDTH = 200;
    static final int HEIGHT = 36;

    /**
     * Constructor for the GameObject class.
     *
     * @param gameManager The game manager.
     */
    public ScoreCounter(GameManager gameManager) {
        super(gameManager);
    }

    /**
     * Init method for the ScoreCounter class.
     * @param player The player.
     */
    public void init(Player player) {
        super.init();
        this.player = player;
    }

    /**
     * Update method for the ScoreCounter class.
     */
    @Override
    public void update(double delta) {
        super.update(delta);

        // Set the position of the score counter to the top center of the screen
        this.position.set(
            this.gameManager.screenWidth / 2 - ScoreCounter.WIDTH / 2,
            0
        );
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        // draw background
        g2d.setColor(new Color(255, 255, 255, 50));
        g2d.fillRect(
            (int) this.position.getX(),
            (int) this.position.getY(),
            ScoreCounter.WIDTH,
            ScoreCounter.HEIGHT
        );

        String scoreText = String.format("%.0f", this.gameManager.getScore());
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.setColor(Color.WHITE);

        g2d.drawString(
            scoreText,
            (int) this.position.getX() + ScoreCounter.WIDTH / 2 
                  - g2d.getFontMetrics().stringWidth(scoreText) / 2,
            (int) this.position.getY() + ScoreCounter.HEIGHT / 2 + 10
        );
    }
}
