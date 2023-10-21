package GameObjects;

// import Managers.GameManager;
// import Managers.Utils;
import java.awt.*;

/**
 * Blob class for the game.
 */
public class Blob extends PhysicsObject<Blob> {
    public static final double MIN_SIZE = 50;

    private final Player player;
    public double size = 1000;
    private double dsize = 0;
    public double maxForce = 1;
    public boolean canCombine = false;
    
    /**
     * Constructor for the Blob class.
     */
    public Blob(Player player) {
        super(player.gameManager);
        this.player = player;

        this.init();
    }

    public double getSpeed() {
        return 1 / Math.sqrt(this.getRadius());
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
        // this.position.set(
        //     Math.random() * GameManager.SCREEN_WIDTH / 2, 
        //     Math.random() * GameManager.SCREEN_HEIGHT / 2
        // );
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

        this.dsize += (this.size - this.dsize) * 0.1;
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

        // if (gameManager.getDebug()) {
        //     String format = this.debugInfo();
        //     Utils.drawText(
        //         g2d,
        //         format,
        //         (int) (this.screenPosition.getX() - g2d.getFontMetrics().stringWidth(format) / 2),
        //         (int) (this.screenPosition.getY() - radius - 20)
        //     );
        // }
    }

    @Override
    public void onCollision(PhysicsObject<?> other) {
        // Cell eat behaviour
        if (other instanceof Cell cell) {
            this.size += cell.size;
            cell.markObjectForRemoval();
        }

        // Merge behaviour
        if (other instanceof Blob blob) {
            double distance = this.position.distance(blob.position);
            double totalRadius = this.getRadius() + blob.getRadius();
            if (distance < totalRadius && blob.canCombine && this.canCombine) {    
                if (this.size > blob.size) {
                    this.size += blob.size;
                    blob.markObjectForRemoval();
                } else {
                    blob.size += this.size;
                    this.markObjectForRemoval();
                }
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
    }
}
