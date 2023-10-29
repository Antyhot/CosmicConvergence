package managers;

import gameObjects.PhysicsObject;

import java.util.ArrayList;

/**
 * PhysicsManager class resolves collisions between physics objects.
 */
public class PhysicsManager {
    public final ArrayList<PhysicsObject<?>> physicsObjects = new ArrayList<>();

    /**
     * This method handles collisions between physics objects. It iterates through
     * each physics object and checks for collisions with other physics objects
     * in the list. If a collision is detected, it calls the onCollision method
     * on the object. Additionally, it removes any inactive objects from the list.
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
                    }
                }
            }
        }

        // if the object is inactive remove it from the list
        this.physicsObjects.removeIf(object -> !object.isActive());
    }
}
