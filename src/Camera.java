import java.awt.Color;
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

        double totalSize = this.player.calcTotalSize();
        double dzoom = 1 / Math.sqrt(totalSize / 100); 

        this.zoom -= (this.zoom - dzoom) * 0.1;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.scale(this.zoom, this.zoom);

        // g2d.setColor(Color.RED);
        // g2d.drawRect(
        //     (int) (this.screenPosition.x - GameManager.SCREEN_WIDTH / 2),
        //     (int) (this.screenPosition.y - GameManager.SCREEN_HEIGHT / 2),
        //     (int) (GameManager.SCREEN_WIDTH),
        //     (int) (GameManager.SCREEN_HEIGHT)
        // );

        g2d.setColor(Color.GREEN);
        g2d.fillOval(
            (int) (this.screenPosition.x - 5),
            (int) (this.screenPosition.y - 5),
            (int) (5 * 2),
            (int) (5 * 2)
        );
    }
}

