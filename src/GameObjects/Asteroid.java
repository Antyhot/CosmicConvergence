package GameObjects;

import Managers.GameManager;

import java.awt.*;

public class Asteroid extends PhysicsObject<Asteroid> {



    /**
     * Constructor for the PhysicsObject class.
     *
     * @param gameManager
     */
    public Asteroid(GameManager gameManager) {
        super(gameManager);

    }

    @Override
    public void init(Asteroid parent) {
        super.init(parent);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void onCollision(PhysicsObject<?> other) {
        super.onCollision(other);

        if (other instanceof Blob blob) {


        }
    }


    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }
}
