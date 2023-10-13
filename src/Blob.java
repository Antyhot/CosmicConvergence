import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Blob class for the game.
 */
public class Blob extends PhysicsObject<Blob> {
    private Player player;
    public double radius;
    public double maxForce = 1;
    public double maxSpeed = 1;
    
    /**
     * Constructor for the Blob class.
     */
    public Blob(Player player) {
        super(player.gameManager);
        this.player = player;
    }

    /**
     * Sets the radius of the blob.
     * 
     * @param radius The radius of the blob.
     */
    public void setRadius(double radius) {
        this.radius = radius;
        this.collider.radius = radius;
    }

    /**
     * Calculates the size of the blob.
     * 
     * @return size of the blob.
     */
    public double getSize() {
        return Math.sqrt(this.radius * 100);
    }

    /**
     * Initializes the blob.
     */
    public void init(double radius) {
        super.init(this);
        this.position.set(
            Math.random() * GameManager.SCREEN_WIDTH,
            Math.random() * GameManager.SCREEN_HEIGHT
        );
    
        this.setRadius(radius);
    }

    @Override
    public void update() {
        super.update();

        Vector2D force = this.player.inputHandler.getMousePosition();
        force.subtract(this.screenPosition);
        force.limit(maxForce);

        this.acceleration.add(force);
        this.velocity.limit(maxSpeed);
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(
            (int) (this.screenPosition.x - this.radius),
            (int) (this.screenPosition.y - this.radius),
            (int) (this.radius * 2),
            (int) (this.radius * 2)
        );
    }
}
