package GameObjects;

/**
 * Collider class.
 */
interface Collider<T> {
    /**
     * Checks if this collider collides with another collider.
     * 
     * @param other collider to check collision with.
     * @return true if collides, false otherwise.
     */
    boolean collidesWith(T other);

    /**
     * resolve collision with another collider.
     * 
     * @param other collider to resolve collision with.
     * @return Vector2D representing the new velocity to push out of collision.
     */
    Vector2D resolveCollision(T other);
}