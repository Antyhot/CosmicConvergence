package gameObjects;

import managers.GameManager;
import java.awt.*;

/**
 * PhysicsObject class.
 */
public class PhysicsObject<T extends PhysicsObject<?>> extends GameObject {
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

    /**
     * Update method for the PhysicsObject class.
     * <p>
     * After calling the super method,
     * the old position is set to the current position and the velocity is added to the position.
     * @param delta The time difference between updates.
     */
    @Override
    public void update(double delta) {
        super.update(delta);

        if (!this.isStatic) {
            this.oldPosition.set(this.position);

            this.velocity.add(this.acceleration);
            this.position.add(this.velocity);

            this.acceleration.set(0, 0);
        }
    }

    /**
     * Draw method for the PhysicsObject class.
     * <p>
     * This method is responsible for rendering the physics object on the screen.
     * <p>
     * If the debug mode is enabled,
     * it will also draw additional debug information such as arrows for velocity and acceleration,
     * and the collider shape.
     *
     * @param g2d The Graphics2D object to draw on.
     */
    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        if (!gameManager.getDebug()) {
            return;
        }

        new Arrow(
            this.screenPosition.copy(),
            this.velocity.copy(),
            this.velocity.magnitude() * 100
        ).draw(g2d, Color.BLUE);

        new Arrow(
            this.screenPosition.copy(),
            this.acceleration.copy(),
            this.acceleration.magnitude() * 100
        ).draw(g2d, Color.RED);

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
