package GameObjects;

import java.awt.*;

/**
 * Arrow class for the game.
 */
public class Arrow {
    public Vector2D from = new Vector2D();
    public Vector2D to = new Vector2D();

    /**
     * Constructor for the Arrow class.
     * 
     * @param position The starting point of the arrow.
     * @param v The ending point of the arrow.
     * @param length The length of the arrow.
     */
    public Arrow(Vector2D position, Vector2D v, double length) {
        this.from.set(position);
        this.to.set(position.add(v.setMagnitude(length)));
    }

    /**
     * Draws the arrow.
     * 
     * @param g2d The graphics object.
     */
    public void draw(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.drawLine(
            (int) this.from.getX(),
            (int) this.from.getY(),
            (int) this.to.getX(),
            (int) this.to.getY()
        );
    }
}
