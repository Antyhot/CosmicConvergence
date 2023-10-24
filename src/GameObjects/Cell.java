package GameObjects;

import Managers.GameManager;
import java.awt.*;

/**
 * Cell class.
 */
public class Cell extends PhysicsObject<Cell> {
    public double size;

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
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(
            (int) (this.screenPosition.getX() - radius),
            (int) (this.screenPosition.getY() - radius),
            (int) (radius * 2),
            (int) (radius * 2)
        );
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
