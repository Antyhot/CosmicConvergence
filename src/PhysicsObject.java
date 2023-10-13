import java.awt.Graphics2D;

/**
 * PhysicsObject class.
 */
public class PhysicsObject<T extends GameObject> extends GameObject {
    public Vector2D velocity;
    public Vector2D acceleration;
    public CircleCollider<T> collider;

    /**
     * Constructor for the PhysicsObject class.
     */
    public PhysicsObject(GameManager gameManager) {
        super(gameManager);
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.collider = new CircleCollider<T>(gameManager);
    }

    /**
     * Init method for the PhysicsObject class.
     * 
     * @param parent The parent object.
     */
    public void init(T parent) {
        super.init();
        this.collider.init(parent);
        gameManager.physicsManager.physicsObjects.add(this);
    }

    @Override
    public void update() {
        super.update();

        this.oldPosition.set(this.position);
        this.velocity.add(this.acceleration);
        this.position.add(this.velocity);

        this.acceleration.set(0, 0);
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        new Arrow(
            this.screenPosition.copy(),
            this.velocity.copy(),
            100
        ).draw(g2d);

        this.collider.draw(g2d);
    }

    public void onCollision(PhysicsObject<?> other) {
        // Override this method
    }
}
