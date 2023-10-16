package GameObjects;

import Managers.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Camera.
 */
public class Camera extends GameObject {
    private static double MIN_ZOOM = 1;
    private static double MAX_ZOOM = 5;

    Player player;
    public double zoom = 1;
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
//        this.calculateVisibleArea();
    }

    private double calcZoom() {
        double totalSize = this.player.calcTotalSize();

        double normSize = 1 / (1 + Math.exp(totalSize / MAX_ZOOM));

        zoom = MIN_ZOOM + normSize * (MAX_ZOOM - MIN_ZOOM);

        return zoom;
    }

    public Vector2D[] calculateVisibleArea() {
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

        return this.visibleArea;
    }

    @Override
    public void update() {
        super.update();

        this.position.set(this.player.calcAverageCenter());

        this.zoom = this.calcZoom();

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

