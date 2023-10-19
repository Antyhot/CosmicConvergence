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
        //TODO: print the score on the screen keeping constant scaling and position
        super.draw(g2d);
    }
}
