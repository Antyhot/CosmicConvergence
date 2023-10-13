package GameObjects;

import Managers.GameManager;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Cell class.
 */
public class Cell extends PhysicsObject<Cell> {
    public double radius;

    /**
     * Constructor for the Cell class.
     * 
     */
    public Cell(GameManager gameManager) {
        super(gameManager);

        this.setRadius(100);
    }

    /**
     * Sets the radius of the cell.
     * 
     * @param radius The radius of the cell.
     */
    public void setRadius(double radius) {
        this.radius = radius;
        this.collider.radius = radius;
    }

    /**
     * Initializes the cell.
     */
    public void init() {
        super.init(this);
        this.position.set(
            // Math.random() * GameManager.SCREEN_WIDTH,
            // Math.random() * GameManager.SCREEN_HEIGHT
            0,
            0
        );

        this.collider.radius = this.radius;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(
            (int) (this.screenPosition.getX() - this.radius),
            (int) (this.screenPosition.getY() - this.radius),
            (int) (this.radius * 2),
            (int) (this.radius * 2)
        );
    }
}
