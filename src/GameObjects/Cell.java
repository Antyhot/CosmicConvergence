package GameObjects;

import Managers.GameManager;
import java.awt.*;
import java.util.ArrayList;

/**
 * Cell class.
 */
public class Cell extends PhysicsObject<Cell> {
    public double size;
    private Color hue;

    /**
     * Constructor for the Cell class.
     * 
     */
    public Cell(GameManager gameManager, double size) {
        super(gameManager);
        this.size = size;
        this.init();
    }

    /**
     * Initializes the cell.
     */
    public void init() {
        super.init(this);

        Vector2D[] visibleArea = this.gameManager.getCamera().visibleArea;

        double x = visibleArea[0].getX() + 2 * Math.random() * (visibleArea[1].getX() - visibleArea[0].getX());
        double y = visibleArea[0].getY() + 2 * Math.random() * (visibleArea[3].getY() - visibleArea[0].getY());

        this.position.set(x, y);

        this.collider.setRadius(this.getRadius());
        this.collider.setActive(false);

        this.isStatic = true;

        // Create a random color for the cell. Must be bright enough to be visible on the background.
        this.hue = Color.getHSBColor(
            (float) Math.random(), 
            (float) (0.5 + Math.random() * 0.5), 
            (float) (0.5 + Math.random() * 0.5)
        );
    }

    public double getRadius() {
        return Math.sqrt(this.size / Math.PI);
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        this.checkForRemoval();
    }

    @Override
    public void draw(Graphics2D g2d) {
        double radius = this.getRadius();

        // Draw the cell
        g2d.setColor(this.hue);
        g2d.fillOval(
            (int) (this.screenPosition.getX() - radius),
            (int) (this.screenPosition.getY() - radius),
            (int) (radius * 2),
            (int) (radius * 2)
        );

        // Draw outline of the cell
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawOval(
            (int) (this.screenPosition.getX() - radius),
            (int) (this.screenPosition.getY() - radius),
            (int) (radius * 2),
            (int) (radius * 2)
        );
        g2d.setStroke(new BasicStroke(1));
    }

    private void checkForRemoval() {
        if (this.position.getX() < this.gameManager.getCamera().visibleArea[0].getX() - this.gameManager.screenWidth ||
            this.position.getX() > this.gameManager.getCamera().visibleArea[1].getX() + this.gameManager.screenWidth ||
            this.position.getY() < this.gameManager.getCamera().visibleArea[0].getY() - this.gameManager.screenHeight ||
            this.position.getY() > this.gameManager.getCamera().visibleArea[2].getY() + this.gameManager.screenHeight) {
            this.markObjectForRemoval();
        }
    }
}
