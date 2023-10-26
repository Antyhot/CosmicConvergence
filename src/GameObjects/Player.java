package GameObjects;

import Managers.GameManager;
import Managers.InputHandler;
import java.awt.*;
import java.util.ArrayList;

/**
 * Player class for the game.
 */
public class Player extends GameObject {
    public static final int MAX_SIZE = 1500;
    public String name = "ABC";

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

        if (this.blobs.isEmpty()) {
            return this.gameManager.getCamera().position;
        }

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

        // Sqrt allows natural zoom out effect on large amount of blobs.
        // since sqrt(a + b) <= sqrt(a) + sqrt(b)
        for (Blob blob: this.blobs) {
            totalSize += Math.sqrt(blob.size);
        }

        return totalSize;
    }

    /**
     * Calculates total score of all blobs.
     * 
     * @return total score.
     */
    public double calcTotalScore() {

        // Figure out why the total score changes after splitting
        // It changes since asteroid decreases the blob size, and then the blob splits.
        double totalScore = 0;

        for (Blob blob: this.blobs) {
            totalScore += blob.size;
        }

        return totalScore;
    }

    /**
     * A method that auto fills blobs with cells. Used for testing.
     */
    public void fill() {
        for (Blob blob: this.blobs) {
            blob.size += 500;
        }
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

        this.blobs.removeIf(blob -> blob.size <= 0);
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


    public boolean isDead() {
        return this.blobs.size() == 0;
    }
}
