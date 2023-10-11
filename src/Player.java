import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Player class for the game.
 */
public class Player extends PhysicsObject {
    InputHandler inputHandler;
    final double maxSpeed = 5;
    final double maxForce = 2;
    final double radius = 10;

    /**
     * Constructor for the Player class.
     * 
     * @param inputHandler The input handler for the game.
     */
    public Player(InputHandler inputHandler) {
        super();
        this.inputHandler = inputHandler;
    }

    @Override
    public void init() {
        super.init();

        this.position.set(100, 100);
    }

    @Override
    public void update() {
        Vector2D force = this.inputHandler.getMousePosition();
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
    }
}
