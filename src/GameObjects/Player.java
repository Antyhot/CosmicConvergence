package GameObjects;

import Managers.GameManager;
import Managers.InputHandler;
import java.awt.*;
import java.util.ArrayList;

/**
 * Player class for the game.
 */
public class Player extends GameObject {
    public static final int MAX_SIZE = 10000;

    InputHandler inputHandler;
    public ArrayList<Blob> blobs = new ArrayList<>();

    /**
     * Constructor for the Player class.
     * 
     * @param inputHandler The input handler for the game.
     */
    public Player(GameManager gameManager, InputHandler inputHandler) {
        super(gameManager);
        this.inputHandler = inputHandler;
    }

    /**
     * Calculates an average position of all blobs.
     * 
     * @return center of all blobs.
     */
    public Vector2D calcAverageCenter() {
        Vector2D center = new Vector2D();

        for (Blob blob: this.blobs) {
            center.add(blob.position);
        }

        center.divide(this.blobs.size());

        return center;
    }

    /**
     * Calculates total size of all blobs.
     * 
     * @return total size.
     */
    public double calcTotalSize() {
        double totalSize = 0;

        for (Blob blob: this.blobs) {
            totalSize += blob.getSize();
        }

        return totalSize;
    }

    /**
     * Init method for the Player class.
     */
    @Override
    public void init() {
        for (int i = 0; i < 1; i++) {
            this.blobs.add(new Blob(this));
        }

        for (Blob blob: this.blobs) {
            blob.init();
        }
    }

    @Override
    public void update(double delta) {
        ArrayList<Blob> blobsCopy = new ArrayList<>(this.blobs);
        for (Blob blob: blobsCopy) {
            blob.update(delta);
        }

        this.blobs.removeIf(blob -> blob.getSize() <= 0);
        this.blobs.removeIf(blob -> !blob.isActive());
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (Blob blob: this.blobs) {
            blob.draw(g2d);
        }
    }

    @Override
    public String debugInfo() {
        return String.format(
            "Blob count: %d\n"
            + "Total size: %.2f\n",
            this.blobs.size(),
            this.calcTotalSize()
        );
    }
}
