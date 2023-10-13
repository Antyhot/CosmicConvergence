import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Cell class.
 */
public class Cell extends PhysicsObject<Cell> {
    public double size = 10;

    /**
     * Constructor for the Cell class.
     * 
     */
    public Cell(GameManager gameManager) {
        super(gameManager);
    }

    /**
     * Initializes the cell.
     */
    public void init() {
        super.init(this);
        // this.position.set(0, 0);
        this.position.set(
            Math.random() * GameManager.SCREEN_WIDTH,
            Math.random() * GameManager.SCREEN_HEIGHT
        );
    }

    public double getRadius() {
        return Math.sqrt(this.size / Math.PI);
    }

    @Override
    public void update() {
        super.update();

        this.collider.radius = this.getRadius();
    }

    @Override
    public void draw(Graphics2D g2d) {
        double radius = this.getRadius();
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(
            (int) (this.screenPosition.x - radius),
            (int) (this.screenPosition.y - radius),
            (int) (radius * 2),
            (int) (radius * 2)
        );
    }
}
