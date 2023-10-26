package GameObjects;

import Managers.GameManager;
import java.awt.*;

/**
 * Cell class.
 */
public class Cell extends PhysicsObject<Cell> {
    public double size;
    private Color hue;

    /**
     * Constructor for the Cell class.
     * 
     */
    public Cell(GameManager gameManager, double size) {
        super(gameManager);
        this.size = size;
    }

    /**
     * Initializes the cell.
     */
    public void init() {
        super.init(this);

        Vector2D[] visibleArea = this.gameManager.getCamera().visibleArea;

        double x = 2 * Math.random() * this.gameManager.getCamera().calcVisibleAreaWidth() 
                   + visibleArea[0].getX();
        double y = 2 * Math.random() * this.gameManager.getCamera().calcVisibleAreaHeight() 
                   + visibleArea[0].getY();

        this.position.set(x, y);

        this.collider.setRadius(this.getRadius());
        this.collider.setActive(false);

        this.isStatic = true;

        this.hue = Color.getHSBColor(
            (float) Math.random(), 
            (float) (0.5 + Math.random() * 0.5), 
            (float) (0.5 + Math.random() * 0.5)
        );
    }

    public double getRadius() {
        return Math.sqrt(this.size / Math.PI);
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        this.checkForRemoval();
    }

    @Override
    public void draw(Graphics2D g2d) {
        double radius = this.getRadius();

        // Draw the cell
        g2d.setColor(this.hue);
        g2d.fillOval(
            (int) (this.screenPosition.getX() - radius),
            (int) (this.screenPosition.getY() - radius),
            (int) (radius * 2),
            (int) (radius * 2)
        );

        // Draw outline of the cell
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawOval(
            (int) (this.screenPosition.getX() - radius),
            (int) (this.screenPosition.getY() - radius),
            (int) (radius * 2),
            (int) (radius * 2)
        );
        g2d.setStroke(new BasicStroke(1));
    }

    private void checkForRemoval() {
        Vector2D[] visibleArea = this.gameManager.getCamera().visibleArea;
        double visibleAreaWidth = this.gameManager.getCamera().calcVisibleAreaWidth();
        double visibleAreaHeight = this.gameManager.getCamera().calcVisibleAreaHeight();

        if (this.position.getX() < visibleArea[0].getX() - visibleAreaWidth 
            || this.position.getX() > visibleArea[1].getX() + visibleAreaWidth 
            || this.position.getY() < visibleArea[0].getY() - visibleAreaHeight 
            || this.position.getY() > visibleArea[2].getY() + visibleAreaHeight) {

            this.markObjectForRemoval();
        }
    }
}
