package GameObjects;

import Managers.GameManager;
import java.awt.*;

/**
 * PhysicsObject class.
 */
public class PhysicsObject<T extends GameObject> extends GameObject {
    protected Boolean isStatic = false;

    protected Vector2D velocity;
    protected Vector2D acceleration;
    protected CircleCollider<T> collider;

    /**
     * Constructor for the PhysicsObject class.
     */
    public PhysicsObject(GameManager gameManager) {
        super(gameManager);
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.collider = new CircleCollider<>(gameManager);

    }

    /**
     * Init method for the PhysicsObject class.
     * 
     * @param parent The parent object.
     */
    public void init(T parent) {
        super.init();
        this.collider.init(parent);
        gameManager.getPhysicsManager().physicsObjects.add(this);
    }

    @Override
    public void update() {
        super.update();

        if (!this.isStatic) {
            this.oldPosition.set(this.position);
            this.velocity.add(this.acceleration);
            this.position.add(this.velocity);
    
            this.acceleration.set(0, 0);
        }
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

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    public CircleCollider<T> getCollider() {
        return collider;
    }

    public void setCollider(CircleCollider<T> collider) {
        this.collider = collider;
    }
}
