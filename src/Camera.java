import java.awt.Graphics2D;

/**
 * Camera.
 */
public class Camera extends GameObject {
    Player player;
    public double zoom = 1;

    /**
     * Constructor for the Camera class.
     * 
     * @param player The player object.
     */
    public Camera(Player player) {
        super();

        this.player = player;
    }   
    
    @Override
    public void update() {
        this.position.set(this.player.calcAverageCenter());
        
        // double totalSize = this.player.calcTotalSize();
        // this.zoom = 1 / (Math.sqrt(totalSize) / Math.log(totalSize));
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.scale(this.zoom, this.zoom);
        g2d.translate(
            -this.position.x + GameManager.SCREEN_WIDTH / 2, 
            -this.position.y + GameManager.SCREEN_HEIGHT / 2
        );
    }
}

