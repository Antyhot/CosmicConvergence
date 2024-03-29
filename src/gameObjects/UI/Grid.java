package gameObjects.UI;

import gameObjects.GameObject;
import java.awt.*;
import managers.GameManager;

/**
 * Grid.
 */
public class Grid extends GameObject {

    public Grid(GameManager gameManager) {
        super(gameManager);
    }

    /**
     * Returns the size of the grid.
     * 
     * @return double
     */
    double getGridSize() {
        return 25;
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(255, 255, 255, 100));

        double gridSize = this.getGridSize();
        int worldWidth = (int) (this.gameManager.screenWidth 
            / this.gameManager.getCamera().dzoom / gridSize) + 3;
        int worldHeight = (int) (this.gameManager.screenHeight 
            / this.gameManager.getCamera().dzoom / gridSize) + 3;
        
        // get the camera's position
        double cameraX = this.gameManager.getCamera().getPosition().getX();
        double cameraY = this.gameManager.getCamera().getPosition().getY();

        // calculate the offset of the grid lines based on the camera's position
        int offsetX = (int) (cameraX % gridSize);
        int offsetY = (int) (cameraY % gridSize);

        // draw the grid dots
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldHeight; j++) {
                g2d.fillOval(
                    (int) (i * gridSize) - offsetX,
                    (int) (j * gridSize) - offsetY,
                    2,
                    2
                );
            }
        }

    }
}
