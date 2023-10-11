import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Blob class for the game.
 */
public class Blob extends PhysicsObject {
    private Player player;
    public double radius = 10;
    public double maxForce = 1;
    public double maxSpeed = 0.1;
    
    /**
     * Constructor for the Blob class.
     */
    public Blob(Player player) {
        super();
        this.player = player;
    }

    /**
     * Calculates the size of the blob.
     * 
     * @return size of the blob.
     */
    public double getSize() {
        return Math.sqrt(this.radius * 100);
    }

    @Override
    public void init() {
        this.position.set(
            Math.random() * GameManager.SCREEN_WIDTH,
            Math.random() * GameManager.SCREEN_HEIGHT
        );
    }

    @Override
    public void update() {
        Vector2D force = this.player.inputHandler.getMousePosition();
        force.subtract(this.position);
        force.limit(maxForce);

        this.acceleration.add(force);
        this.velocity.limit(maxSpeed);

        super.update();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(
            (int) (this.position.x - radius / 2),
            (int) (this.position.y - radius / 2),
            (int) radius,
            (int) radius
        );

        new Arrow(
            this.position.copy(),
            this.velocity.copy(),
            50
        ).draw(g2d);
    }
}
