import GameObjects.GameObject;
import GameObjects.Vector2D;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Player class for the game.
 */
public class Player extends GameObject {
    InputHandler inputHandler;
    public ArrayList<Blob> blobs = new ArrayList<Blob>();

    /**
     * Constructor for the Player class.
     * 
     * @param inputHandler The input handler for the game.
     */
    public Player(InputHandler inputHandler) {
        super();
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

    @Override
    public void init() {
        this.blobs.add(new Blob(this));
        this.blobs.add(new Blob(this));
        
        for (Blob blob: this.blobs) {
            blob.init();
        }
    }

    @Override
    public void update() {
        for (Blob blob: this.blobs) {
            blob.update();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (Blob blob: this.blobs) {
            blob.draw(g2d);
        }
    }
}
