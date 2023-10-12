package GameObjects;

import java.awt.*;

/**
 * Arrow class for the game.
 */
public class Arrow extends GameObject {
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
        super();
        this.from.set(position);
        this.to.set(position.add(v.setMagnitude(length)));
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawLine(
            (int) this.from.x,
            (int) this.from.y,
            (int) this.to.x,
            (int) this.to.y
        );
    }
}
