package GameObjects;

import Managers.GameManager;

import java.awt.*;

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

        if (gameManager.getDebug()) {
            g2d.setColor(Color.WHITE);
            //draw text of size 15
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));

            String formatted = "FPS: %d, Zoom: %.2f, Left Corner: (%.2f, %.2f)"
                    .formatted(
                            gameManager.drawCount,
                            gameManager.getCamera().zoom,
                            gameManager.getCamera().visibleArea[0].getX(), gameManager.getCamera().visibleArea[0].getY());
            g2d.drawString(formatted, 10, 35);
        }


        super.draw(g2d);


    }

}
