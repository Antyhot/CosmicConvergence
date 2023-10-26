package Managers;

import java.awt.Graphics2D;

/**
 * Utils class.
 */
public class Utils {
    
    /**
     * Draws text on the screen.
     * @param g2d Graphics2D object
     * @param text Text to be drawn
     * @param x x coordinate
     * @param y y coordinate
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

        return a + Math.random() * (b - a);
    }
}
