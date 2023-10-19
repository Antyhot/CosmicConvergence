package GameObjects;

import Managers.GameManager;
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
        this.screenPosition.subtract(this.gameManager.getCamera().position);

        Vector2D center = new Vector2D(
            (double) GameManager.SCREEN_WIDTH / 2,
            (double) GameManager.SCREEN_HEIGHT / 2
        );

        center.multiply(1 / this.gameManager.getCamera().dzoom);

        this.screenPosition.add(center);
    }

    public void setScreenPosition(Vector2D screenPosition) {
        this.screenPosition = screenPosition;
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

    /**
     * Returns the debug info for the GameObject.
     * @return The debug info for the GameObject.
     */
    public String debugInfo() {
        // Override this method
        return "";
    }

    public Vector2D getScreenPosition() {
        return screenPosition;
    }

    public Vector2D getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Vector2D oldPosition) {
        this.oldPosition = oldPosition;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
