package gameObjects.UI;

import gameObjects.GameObject;
import managers.GameManager;
import managers.Utils;
import java.awt.*;

/**
 * The DebugWindow class represents a debug window in a game. It extends the GameObject class.
 */
public class DebugWindow extends GameObject {

    /**
     * Initializes a new instance of the DebugWindow class.
     *
     * @param gameManager The game manager associated with the debug window.
     */
    public DebugWindow(GameManager gameManager) {
        super(gameManager);
    }

    /**
     * Draws the debug information on the debug window.
     *
     * @param g2d The Graphics2D object used for drawing.
     */
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 16));

        StringBuilder debugInfo = new StringBuilder(this.debugInfo() + "\n");

        debugInfo.append(gameManager.getCamera().debugInfo()).append("\n");
        for (GameObject gameObject : gameManager.getGameObjects()) {
            debugInfo.append(gameObject.debugInfo()).append("\n");
        }

        Utils.drawText(g2d, debugInfo.toString(), 5, 16);
    }

    /**
     * Returns the debug information as a formatted string.
     *
     * @return The debug information.
     */
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
            (double) gameManager.screenWidth, (double) gameManager.screenHeight 
        );
    }
}
