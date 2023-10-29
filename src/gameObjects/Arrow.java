package gameObjects;

import java.awt.*;

/**
 * Represents an arrow with a specified starting point, ending point, and length.
 * Used for debugging purposes.
 */
public class Arrow {
    public final Vector2D from = new Vector2D();
    public final Vector2D to = new Vector2D();

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
