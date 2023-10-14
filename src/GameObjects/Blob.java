package GameObjects;

import Managers.GameManager;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Blob class for the game.
 */
public class Blob extends PhysicsObject<Blob> {
    private Player player;
    public double size = 50;
    public double maxForce = 1;
    public double maxSpeed = 1;
    
    /**
     * Constructor for the Blob class.
     */
    public Blob(Player player) {
        super(player.gameManager);
        this.player = player;
    }

    /**
     * Calculates the size of the blob.
     * 
     * @return size of the blob.
     */
    public double getSize() {
        return this.size;
    }

    public double getRadius() {
        return Math.sqrt(this.size / Math.PI);
    }

    /**
     * Initializes the blob.
     */
    public void init() {
        super.init(this);
        this.position.set(
            Math.random() * GameManager.SCREEN_WIDTH,
            Math.random() * GameManager.SCREEN_HEIGHT
        );
    }

    @Override
    public void update() {
        super.update();

        Vector2D force = this.player.inputHandler.getMousePosition();
        force.subtract(this.screenPosition);
        force.limit(maxForce);

        this.acceleration.add(force);
        this.velocity.limit(maxSpeed);

        this.collider.radius = this.getRadius();
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        double radius = this.getRadius();
        g2d.setColor(Color.WHITE);
        g2d.fillOval(
            (int) (this.screenPosition.getX() - radius),
            (int) (this.screenPosition.getY() - radius),
            (int) (radius * 2),
            (int) (radius * 2)
        );

        if (gameManager.getDebug()) {
            String format = String.format(
                "x: %.2f, y: %.2f, force: %.2f, velocity: %.2f",
                this.position.getX(), this.position.getY(), 
                this.acceleration.magnitude(), this.velocity.magnitude()
            );
            g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            g2d.drawString(
                    format,
                    (int) (this.screenPosition.getX() - g2d.getFontMetrics().stringWidth(format) / 2),
                    (int) (this.screenPosition.getY() - radius - 20)
            );
        }
    }

    @Override
    public void onCollision(PhysicsObject<?> other) {
        if (other instanceof Cell) {
            Cell cell = (Cell) other;
            this.size += cell.size;
            cell.markObjectForRemoval();
        }
    }
}
