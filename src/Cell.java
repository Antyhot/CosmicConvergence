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

        this.setRadius(5);
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
            Math.random() * GameManager.SCREEN_WIDTH,
            Math.random() * GameManager.SCREEN_HEIGHT
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
            (int) (this.screenPosition.x - this.radius),
            (int) (this.screenPosition.y - this.radius),
            (int) (this.radius * 2),
            (int) (this.radius * 2)
        );
    }
}
