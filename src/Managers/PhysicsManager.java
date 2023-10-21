package Managers;

import GameObjects.PhysicsObject;
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
        ArrayList<PhysicsObject<?>> physicsObjectsCopy = new ArrayList<>(this.physicsObjects);
        for (PhysicsObject<?> object : physicsObjectsCopy) {
            for (PhysicsObject<?> other : physicsObjectsCopy) {
                if (object != other) {
                    if (!object.isActive() || !other.isActive()) {
                        continue;
                    }

                    if (object.getCollider().collidesWith(other.getCollider())) {
                        object.onCollision(other);

                        if (!object.getCollider().isActive() || !other.getCollider().isActive()) {
                            continue;
                        }                            

                        object.getCollider().resolveCollision(other.getCollider());
                    }
                }
            }
        }

        // if object is inactive remove it from the list
        this.physicsObjects.removeIf(object -> !object.isActive());
    }
}
