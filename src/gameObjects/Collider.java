package gameObjects;

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
     * Checks if this collider contains another collider.
     * 
     * @param other collider to check containment with.
     * @return true if contains, false otherwise.
     */
    boolean contains(T other);

    /**
     * resolve collision with another collider.
     * 
     * @param other collider to resolve collision with.
     */
    void resolveCollision(T other);
}