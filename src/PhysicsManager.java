import java.util.ArrayList;

/**
 * PhysicsManager class.
 */
public class PhysicsManager {
    public ArrayList<PhysicsObject<?>> physicsObjects = new ArrayList<PhysicsObject<?>>();

    /**
     * Handle collisions between physics objects.
     */
    public void handleCollisions() {
        for (PhysicsObject<?> object : this.physicsObjects) {
            for (PhysicsObject<?> other : this.physicsObjects) {
                if (object != other) {
                    if (object.collider.collidesWith(other.collider)) {
                        object.onCollision(other);

                        Vector2D normal = object.collider.resolveCollision(other.collider);
                        object.position.add(normal);

                    }
                }
            }
        }

        // if object is inactive remve it from the list
        this.physicsObjects.removeIf(object -> !object.isActive());
    }
}
