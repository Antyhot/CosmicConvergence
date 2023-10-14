package Managers;

import GameObjects.PhysicsObject;
import GameObjects.Vector2D;
import java.util.ArrayList;

/**
 * PhysicsManager class.
 */
public class PhysicsManager {
    public ArrayList<PhysicsObject<?>> physicsObjects = new ArrayList<>();

    /**
     * Handle collisions between physics objects.
     */
    public void handleCollisions() {
        for (PhysicsObject<?> object : this.physicsObjects) {
            for (PhysicsObject<?> other : this.physicsObjects) {
                if (object != other) {
                    if (object.getCollider().collidesWith(other.getCollider())) {
                        object.onCollision(other);
        
                        Vector2D normal = object.getCollider()
                            .resolveCollision(other.getCollider());
                        object.position.add(normal);
                    }
                }
            }
        }

        // if object is inactive remove it from the list
        this.physicsObjects.removeIf(object -> !object.isActive());
    }
}
