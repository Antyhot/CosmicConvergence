// Create a class InputHandler that gets position of the mouse.

// Path: src/InputHandler.java
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * InputHandler class for the game.
 */
public class InputHandler implements MouseListener, MouseMotionListener {
    private Vector2D mousePosition = new Vector2D(0, 0);

    public Vector2D getMousePosition() {
        return this.mousePosition.copy();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mousePosition.x = e.getX();
        this.mousePosition.y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mousePosition.x = e.getX();
        this.mousePosition.y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Override this method
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Override this method
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Override this method
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Override this method
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Override this method
    }
}
