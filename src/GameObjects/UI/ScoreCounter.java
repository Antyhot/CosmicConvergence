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

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        //Draw a filled white rectangle
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 300, 50);

        //Displaying debug information
        String score = String.format("%.0f",this.gameManager.getScore());
        //Print the score on white background (rectangle) using comic sans font
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Score: " + score, 15, 25);
    }
}
