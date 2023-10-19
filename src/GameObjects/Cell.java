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

        this.position.set(
                x,
                y
        );

        this.collider.setRadius(this.getRadius());

        this.isStatic = true;
    }

    public double getRadius() {
        return Math.sqrt(this.size / Math.PI);
    }

    @Override
    public void update() {
        super.update();
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
}
