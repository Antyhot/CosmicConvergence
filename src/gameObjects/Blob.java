package gameObjects;

// import Managers.GameManager;
// import Managers.Utils;

import java.awt.*;

/**
 * Blob class for the game.
 */
public class Blob extends PhysicsObject<Blob> {
    public static final int MIN_SIZE = 50;

    private final Player player;
    public double size = 1300;
    private double dsize = 0;
    public final double maxForce = 2;
    public boolean canCombine = false;

    // make a variable for time needed to merge back
    // make a variable for time needed to split
    public final long mergeDelayMillis = 15000;
    public long lastSplitTime = 0;

    /**
     * Creates a new Blob object for the given player.
     *
     * @param player the player object for which the blob is created
     */
    public Blob(Player player) {
        super(player.gameManager);
        this.player = player;

        this.init();
    }

    public double getSpeed() {
        return 1 / Math.sqrt(this.getRadius());
    }

    public double getRadius() {
        return Math.sqrt(this.dsize / Math.PI);
    }

    /**
     * Initializes the blob.
     */
    public void init() {
        super.init(this);
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        Vector2D force = this.player.inputHandler.getMousePosition();
        force.subtract(this.screenPosition);
        force.limit(maxForce);

        this.acceleration.lerp(force, 0.3);

        // depending on the size of the blob, the max speed will be different
        this.velocity.limit(this.getSpeed());

        this.collider.radius = this.getRadius();

        this.dsize += (this.size - this.dsize) * 0.4;

        long currentTime = System.currentTimeMillis();
        this.canCombine = currentTime - this.lastSplitTime > this.mergeDelayMillis;

        this.collider.setActive(!this.canCombine);
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        double radius = this.getRadius();

        // Draw the blob
        g2d.setColor(Color.WHITE);
        g2d.fillOval(
            (int) (this.screenPosition.getX() - radius),
            (int) (this.screenPosition.getY() - radius),
            (int) (radius * 2),
            (int) (radius * 2)
        );

        if (! gameManager.getDebug()) return;

        String text = this.player.name;
        double maxTextLength = 2 * radius
            * this.gameManager.getCamera().dzoom / 10;
        double fontSize = maxTextLength
            / g2d.getFontMetrics().stringWidth(text) * g2d.getFont().getSize2D();

        g2d.setFont(new Font(
            "Monospace",
            Font.BOLD,
            (int) (fontSize)
        ));

        int lineWidth = g2d.getFontMetrics().stringWidth(text);
        int lineHeight = g2d.getFontMetrics().getHeight();

        g2d.setColor(Color.BLACK);
        g2d.drawString(
            text,
            (int) (this.screenPosition.getX() - lineWidth / 2),
            (int) (this.screenPosition.getY() + lineHeight / 3)
        );

        g2d.setFont(new Font(
            "Monospace",
            Font.BOLD,
            (int) (fontSize / 2)
        ));

        text = Integer.toString((int) (this.size));
        lineWidth = g2d.getFontMetrics().stringWidth(text);

        g2d.drawString(
            text,
            (int) (this.screenPosition.getX() - lineWidth / 2),
            (int) (this.screenPosition.getY() + lineHeight)
        );
    }

    @Override
    public void onCollision(PhysicsObject<?> other) {
        boolean contains = this.getCollider().contains(other.getCollider());

        // Cell eating behavior
        if (other instanceof Cell cell) {
            this.size += cell.size;
            this.player.score += (int) cell.size;
            cell.markObjectForRemoval();

        }

        // Merge behaviour
        if (other instanceof Blob blob) {
            double distance = this.position.distance(blob.position);
            double totalRadius = this.getRadius() + blob.getRadius();
            double overlap = totalRadius - distance;

            if ((overlap > Math.min(this.getRadius(), blob.getRadius()) || contains)
                 && blob.canCombine && this.canCombine) {

                if (this.size > blob.size) {
                    this.size += blob.size;
                    blob.markObjectForRemoval();
                } else {
                    blob.size += this.size;
                    this.markObjectForRemoval();
                }
            }

            // resolve collision with another blob
            if (this.getCollider().isActive() || blob.getCollider().isActive()) {
                this.getCollider().resolveCollision(blob.getCollider());
            }
        }
    }

    @Override
    public String debugInfo() {
        return String.format(
            "XY: %.2f / %.2f\n"
            + "size: %.2f\n"
            + "vel: %.2f\n",
            this.position.getX(), this.position.getY(), 
            this.dsize, 
            this.velocity.magnitude()
        );
    }

    /**
     * Splits the blob into two blobs.
     */
    public void split() {
        this.size /= 2;

        Blob blob = new Blob(this.player);
        blob.size = this.size;

        blob.position.set(this.position);

        blob.init();
        this.player.blobs.add(blob);

        this.lastSplitTime = System.currentTimeMillis();
        blob.lastSplitTime = System.currentTimeMillis();
    }

    
}
