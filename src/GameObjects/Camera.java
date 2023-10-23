package GameObjects;

import Managers.GameManager;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Camera.
 */
public class Camera extends GameObject {
    private static double MIN_ZOOM = 1;
    private static double MAX_ZOOM = 1;

    Player player;
    public double zoom;
    public double dzoom;
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

        this.position.set(this.player.calcAverageCenter());
        this.zoom = this.calcZoom();
        this.dzoom = this.zoom;
        
        this.calculateVisibleArea();
    }

    private double calcZoom() {
        double totalSize = this.player.calcTotalSize();

        double normSize = totalSize / Player.MAX_SIZE;

        zoom = MAX_ZOOM - normSize * (MAX_ZOOM - MIN_ZOOM);

        return zoom;
    }

    private void calculateVisibleArea() {
        this.visibleArea[0] = new Vector2D(
                this.position.getX() - (double) GameManager.SCREEN_WIDTH / 2 / this.dzoom,
                this.position.getY() - (double) GameManager.SCREEN_HEIGHT / 2 / this.dzoom
        );

        this.visibleArea[1] = new Vector2D(
                this.position.getX() + (double) GameManager.SCREEN_WIDTH / 2 / this.dzoom,
                this.position.getY() - (double) GameManager.SCREEN_HEIGHT / 2 / this.dzoom
        );

        this.visibleArea[2] = new Vector2D(
                this.position.getX() + (double) GameManager.SCREEN_WIDTH / 2 / this.dzoom,
                this.position.getY() + (double) GameManager.SCREEN_HEIGHT / 2 / this.dzoom
        );

        this.visibleArea[3] = new Vector2D(
                this.position.getX() - (double) GameManager.SCREEN_WIDTH / 2 / this.dzoom,
                this.position.getY() + (double) GameManager.SCREEN_HEIGHT / 2 / this.dzoom
        );
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        this.position.set(this.player.calcAverageCenter());

        this.zoom = this.calcZoom();
        this.dzoom += (this.zoom - this.dzoom) * 0.05;

        this.calculateVisibleArea();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.scale(this.dzoom, this.dzoom);

        if (gameManager.getDebug()) {
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

    @Override
    public String debugInfo() {
        return String.format(
            "CameraPosition XY: %.2f / %.2f\n"
            + "Camera zoom: %.2f\n",
            this.position.getX(), this.position.getY(),
            this.zoom
        );
    }
}

