// import java.awt.Color;
import java.awt.Graphics2D;

/**
 * GameObject class for the game.
 */
public class GameObject {
    public Vector2D oldPosition;
    public Vector2D position;
    private Boolean active;

    /**
     * Constructor for the GameObject class.
     */
    public GameObject() {
        this.oldPosition = new Vector2D();
        this.position = new Vector2D();
        this.active = true;
    }

    public Boolean isActive() {
        return this.active;
    }

    public void markObjectForRemoval() {
        this.active = false;
    }

    public void init() {
        // Override this method
        // this.position.add(100, 100);
    }

    public void update() {
        // Override this method
    }

    public void draw(Graphics2D g2d) {
        // Override this method
        // g2d.setColor(Color.WHITE);
        // g2d.fillRect(
        //     (int) this.position.x,
        //     (int) this.position.y,
        //     10,
        //     10
        // );
    }
}