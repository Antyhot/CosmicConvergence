package managers;

import java.awt.Graphics2D;

/**
 * Utils class.
 */
public class Utils {

    /**
     * Draws multi-line text on the Graphics2D object.
     *
     * @param g2d The Graphics2D object where the text will be drawn.
     * @param text The text string to be drawn.
     * @param x The x-coordinate of the text.
     * @param y The y-coordinate of the text.
     */
    public static void drawText(Graphics2D g2d, String text, int x, int y) {
        String[] lines = text.split("\n");
        int lineHeight = g2d.getFontMetrics().getHeight();

        for (int i = 0; i < lines.length; i++) {
            g2d.drawString(lines[i], x, y + lineHeight * i);
        }
    }

    /**
     * Returns a random number between a and b.
     * 
     * @param a minimum value
     * @param b maximum value
     * @return random number between a and b
     */
    public static double randomBetween(double a, double b) {

        if (a <= b) {
            return a + Math.random() * (b - a);
        } else {
            return b + Math.random() * (a - b);
        }
    }

}
