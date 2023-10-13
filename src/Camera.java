import java.awt.Graphics2D;

/**
 * Camera.
 */
public class Camera extends GameObject {
    Player player;
    public double zoom = 1;

    public Camera(GameManager gameManager) {
        super(gameManager);
    }
    
    /**
     * Init method for the Camera class.
     */
    public void init(Player player) {
        super.init();
        this.player = player;
    }

    @Override
    public void update() {
        super.update();

        this.position.set(this.player.calcAverageCenter());

        // double totalSize = this.player.calcTotalSize();
        // this.zoom = 1 / (Math.sqrt(totalSize) / Math.log(totalSize));
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.scale(this.zoom, this.zoom);
    }
}

