package managers;

import gameObjects.Vector2D;
import java.awt.event.*;

/**
 * The InputHandler class implements MouseListener, MouseMotionListener, and KeyListener interfaces
 * to handle user input events such as mouse clicks, mouse movements, and key presses.
 */
public class InputHandler implements MouseListener, MouseMotionListener, KeyListener {

    // The mouse position is stored in a Vector2D object.
    private final Vector2D mousePosition = new Vector2D(0, 0);
    public final GameManager gameManager;

    /**
     * Constructor for the InputHandler class.
     */
    public InputHandler(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Vector2D getMousePosition() {
        return this.mousePosition.copy().multiply(1 / this.gameManager.camera.zoom);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mousePosition.set(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mousePosition.set(e.getX(), e.getY());
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

    /**
     * Handles the keyTyped event.
     *
     * @param e the keyTyped event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ESCAPE -> this.gameManager.togglePause();
            case 'd' -> this.gameManager.toggleDebug();
//            case 'f' -> this.gameManager.player.fill();
            default -> { }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
