package GameObjects.UI;

import GameObjects.GameObject;
import Managers.GameManager;
import Managers.Utils;
import java.awt.*;

/**
 * DebugWindow class.
 */
public class DebugWindow extends GameObject {

    /**
     * Creates a new DebugWindow object.
     *
     * @param gameManager the game manager for the debug window
     */
    public DebugWindow(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        String debugInfo = this.debugInfo() + "\n";
        debugInfo += gameManager.getCamera().debugInfo() + "\n";
        for (GameObject gameObject : gameManager.getGameObjects()) {
            debugInfo += gameObject.debugInfo() + "\n";
        }

        Utils.drawText(g2d, debugInfo, 5, 16);
    }

    @Override
    public String debugInfo() {
        return String.format(
            "FPS: %d\n"
            + "GameObjects count: %d\n"
            + "MousePosition XY: %.2f / %.2f\n"
            + "Screen width / height: %.2f / %.2f\n",
            gameManager.getDrawCount(),
            gameManager.getGameObjects().size(),
            gameManager.getInputHandler().getMousePosition().getX(),
            gameManager.getInputHandler().getMousePosition().getY(),
            (double) gameManager.SCREEN_WIDTH, (double) gameManager.SCREEN_HEIGHT 
        );
    }
}
