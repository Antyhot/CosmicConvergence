package Managers;

import GameObjects.Vector2D;
import java.awt.event.*;

/**
 * InputHandler class for the game.
 */
public class InputHandler implements MouseListener, MouseMotionListener, KeyListener {

    private final Vector2D mousePosition = new Vector2D(0, 0);
    public GameManager gameManager;

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

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'p' -> this.gameManager.togglePause();
            case 'd' -> this.gameManager.toggleDebug();
            default -> {

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
