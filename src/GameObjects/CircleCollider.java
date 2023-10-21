package GameObjects;

import Managers.GameManager;
import java.awt.*;

/**
 * CircleCollider class.
 */
public class CircleCollider<T extends PhysicsObject<?>> 
        extends GameObject implements Collider<CircleCollider<?>> {

    // FIXME: Find a way to generalize isActive variable
    protected boolean isActive = true;
    public double radius;
    public T parent;

    public CircleCollider(GameManager gameManager) {
        super(gameManager);
    }

    /**
     * Init method for the CircleCollider class.
     *
     * @param parent The parent object.
     */
    public void init(T parent) {
        super.init();
        this.parent = parent;
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        g2d.setColor(Color.RED);
        g2d.drawOval(
                (int) (this.parent.screenPosition.getX() - this.radius),
                (int) (this.parent.screenPosition.getY() - this.radius),
                (int) (this.radius * 2),
                (int) (this.radius * 2)
        );
    }

    @Override
    public boolean collidesWith(CircleCollider<?> other) {
        return this.parent.position.distance(
                other.parent.position
        ) < this.radius + other.radius;
    }

    @Override
    public boolean contains(CircleCollider<?> other) {
        return this.parent.position.distance(
                other.parent.position
        ) < this.radius - other.radius;
    }

    @Override
    public void resolveCollision(CircleCollider<?> other) {
        // generic circle collision resolution
        Vector2D normal = this.parent.position.copy();

        normal.subtract(other.parent.position);

        double distance = normal.magnitude();

        normal.normalize();

        double totalRadius = this.radius + other.radius;

        Vector2D mtv = normal.copy(); // minimum translation vector

        mtv.multiply((totalRadius - distance) * 0.05);

        this.parent.position.add(mtv);
        other.parent.position.subtract(mtv);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
