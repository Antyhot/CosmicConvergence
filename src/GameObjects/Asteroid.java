package GameObjects;

import Managers.GameManager;
import java.awt.*;

/**
 * Asteroid class.
 */
public class Asteroid extends PhysicsObject<Asteroid> {
    public double size = 10;

    /**
     * Constructor for the PhysicsObject class.
     *
     * @param gameManager The game manager.
     */
    public Asteroid(GameManager gameManager) {
        super(gameManager);
    }

    public double getRadius() {
        return Math.sqrt(this.size / Math.PI);
    }

    @Override
    public void init() {
        super.init(this);

        this.velocity.set(
            Math.random(),
            Math.random()
        );
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void onCollision(PhysicsObject<?> other) {
        super.onCollision(other);

        if (other instanceof Blob blob) {
            blob.size -= this.size;

            if (blob.size <= Blob.MIN_SIZE) {
                blob.markObjectForRemoval();
            } else {
                blob.split();
            }

            this.markObjectForRemoval();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        double radius = this.getRadius();
        g2d.setColor(Color.WHITE);
        g2d.drawOval(
                (int) (this.screenPosition.getX() - radius),
                (int) (this.screenPosition.getY() - radius),
                (int) (radius * 2),
                (int) (radius * 2)
        );
    }
}
