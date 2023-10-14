package GameObjects;

import Managers.GameManager;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * CircleCollider class.
 */
public class CircleCollider<T extends GameObject>
    extends GameObject implements Collider<CircleCollider<?>> {

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
    public void update() {
        super.update();
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
    public Vector2D resolveCollision(CircleCollider<?> other) {
        Vector2D push = this.parent.position.copy().subtract(other.parent.position);

        double distance = this.parent.position.distance(other.parent.position);

        double overlap = this.radius + other.radius - distance;
    
        push.setMagnitude(overlap);

        return push;
    }
}
