package GameObjects;

import Managers.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Camera.
 */
public class Camera extends GameObject {
    Player player;
    public double zoom = 2;
    public Vector2D[] visibleArea = new Vector2D[4];


    public Camera(GameManager gameManager) {
        super(gameManager);
    }
    
    /**
     * Init method for the Camera class.
     */
    public void init(Player player) {
        super.init();
        this.player = player;
        this.calculateVisibleArea();
    }

    private void calculateVisibleArea() {
        System.out.println("Calculated visible area");
        this.visibleArea[0] = new Vector2D(
                this.position.getX() - (double) GameManager.SCREEN_WIDTH / 2 / this.zoom,
                this.position.getY() - (double) GameManager.SCREEN_HEIGHT / 2 / this.zoom
        );

        this.visibleArea[1] = new Vector2D(
                this.position.getX() + (double) GameManager.SCREEN_WIDTH / 2 / this.zoom,
                this.position.getY() - (double) GameManager.SCREEN_HEIGHT / 2 / this.zoom
        );

        this.visibleArea[2] = new Vector2D(
                this.position.getX() + (double) GameManager.SCREEN_WIDTH / 2 / this.zoom,
                this.position.getY() + (double) GameManager.SCREEN_HEIGHT / 2 / this.zoom
        );

        this.visibleArea[3] = new Vector2D(
                this.position.getX() - (double) GameManager.SCREEN_WIDTH / 2 / this.zoom,
                this.position.getY() + (double) GameManager.SCREEN_HEIGHT / 2 / this.zoom
        );
    }

    @Override
    public void update() {
        super.update();

        this.position.set(this.player.calcAverageCenter());

        double totalSize = this.player.calcTotalSize();
        double dzoom = 1 / Math.sqrt(totalSize / 5); 

        this.zoom -= (this.zoom - dzoom) * 0.1;

        calculateVisibleArea();
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
            (int) (this.screenPosition.getX() - 5),
            (int) (this.screenPosition.getY() - 5),
            5 * 2,
            5 * 2
        );
    }
}

