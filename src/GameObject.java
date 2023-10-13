import java.awt.Graphics2D;

/**
 * GameObject class for the game.
 */
public class GameObject {
    public Vector2D screenPosition;
    public Vector2D oldPosition;
    public Vector2D position;
    private Boolean active;
    public GameManager gameManager;

    /**
     * Constructor for the GameObject class.
     */
    public GameObject(GameManager gameManager) {
        this.gameManager = gameManager;
        this.screenPosition = new Vector2D();
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

    /**
     * Sets the position of the GameObject.
     */
    public void setScreenPosition() {
        this.screenPosition.set(this.position);
        this.screenPosition.subtract(this.gameManager.camera.position);
        // make the camera the center of the screen
        this.screenPosition.add(
            GameManager.SCREEN_WIDTH / 2, 
            GameManager.SCREEN_HEIGHT / 2
        );
    }

    public void init() {
        // Override this method
    }

    /**
     * Updates the GameObject.
     */
    public void update() {
        // Override this method
        this.setScreenPosition();
    }

    public void draw(Graphics2D g2d) {
        // Override this method
    }
}
