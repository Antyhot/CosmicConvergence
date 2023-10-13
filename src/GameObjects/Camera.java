package GameObjects;

import Managers.GameManager;

import java.awt.*;

/**
 * Camera.
 */
public class Camera extends GameObject {
    Player player;
    public double zoom = 0.75;

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

        g2d.setColor(Color.RED);
        g2d.drawRect(
            (int) (this.screenPosition.getX() - GameManager.SCREEN_WIDTH / 2),
            (int) (this.screenPosition.getY() - GameManager.SCREEN_HEIGHT / 2),
                GameManager.SCREEN_WIDTH,
                GameManager.SCREEN_HEIGHT
        );

        g2d.setColor(Color.GREEN);
        g2d.fillOval(
            (int) (this.screenPosition.getX() - 10),
            (int) (this.screenPosition.getY() - 10),
                10 * 2,
                10 * 2
        );
    }
}

