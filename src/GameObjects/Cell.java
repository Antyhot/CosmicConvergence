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

        //TODO: calculate the bounds where the cells cans spawn using the camera.visibleArea values

        this.position.set(
                Math.random() * GameManager.SCREEN_WIDTH,
                Math.random() * GameManager.SCREEN_HEIGHT
        );

        this.collider.setRadius(this.getRadius() * 3);

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
