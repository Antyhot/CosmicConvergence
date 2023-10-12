import GameObjects.GameObject;
import GameObjects.Vector2D;

/**
 * PhysicsObject class.
 */
public class PhysicsObject extends GameObject {
    public Vector2D velocity;
    public Vector2D acceleration;
//    CircleColliderShape collider;

    /**
     * Constructor for the PhysicsObject class.
     */
    public PhysicsObject() {
        super();
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
//        this.collider = new CircleColliderShape();
    }

    @Override
    public void init() {
        super.init();
//        this.collider.init();
    }

    @Override
    public void update() {
        super.update();
        this.oldPosition.set(this.position);
        this.velocity.add(this.acceleration);
        this.position.add(this.velocity);

        this.acceleration.set(0, 0);
//        this.collider.update();
    }
}
