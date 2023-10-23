package GameObjects;

import Managers.GameManager;
import Managers.Utils;

import java.awt.*;
import java.util.Random;

/**
 * Asteroid class.
 */
public class Asteroid extends PhysicsObject<Asteroid> {
    public double size = 10;

    /**
     * Constructor for the PhysicsObject class.
     *
     * @param gameManager The game manager.
     */
    public Asteroid(GameManager gameManager) {

        super(gameManager);
        this.init();
    }

    public double getRadius() {
        return Math.sqrt(this.size / Math.PI);
    }

    @Override
    public void init() {
        super.init(this);
        this.size = Utils.randomBetween(10,500);
        this.collider.setRadius(this.getRadius());

        boolean up = new Random().nextBoolean();
        boolean left = new Random().nextBoolean();
        Vector2D[] visibleArea = this.gameManager.getCamera().visibleArea;

        double x = Utils.randomBetween(visibleArea[0].getX() - GameManager.SCREEN_WIDTH, visibleArea[1].getX() + GameManager.SCREEN_WIDTH);
        double y = Utils.randomBetween(visibleArea[0].getY() - GameManager.SCREEN_HEIGHT, visibleArea[2].getY() + GameManager.SCREEN_HEIGHT);

        if (x >= visibleArea[0].getX() + (double) GameManager.SCREEN_WIDTH /( 2)) {
            System.out.println("right");
            x += (double) GameManager.SCREEN_WIDTH / 2 + this.getRadius() * 2;
        } else {
            System.out.println("left");
            x -= (double) GameManager.SCREEN_WIDTH / 2 + this.getRadius() * 2;
        }

        if (y >= visibleArea[0].getY() + (double) GameManager.SCREEN_WIDTH /( 2)) {
            System.out.println("right");
            y += (double) GameManager.SCREEN_HEIGHT / 2 + this.getRadius() * 2;
        } else {
            System.out.println("left");
            y -= (double) GameManager.SCREEN_HEIGHT/ 2 + this.getRadius() * 2;
        }

//        System.out.println("x = " + x);
//        System.out.println("y = " + y);

        this.setPosition(new Vector2D(
                x,
                y
        ));

        Vector2D pointAtPlayer = new Vector2D(
                this.gameManager.getCamera().position.getX() - this.position.getX(),
                this.gameManager.getCamera().position.getY() - this.position.getY()
        );

        this.velocity.set(pointAtPlayer);
        this.velocity.normalize();
//        this.velocity.set(Math.random(), Math.random());
        this.velocity.multiply(5/ Math.sqrt(this.getRadius()));

    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void onCollision(PhysicsObject<?> other) {
        super.onCollision(other);

        if (other instanceof Blob blob) {
            blob.size -= this.size;

            if (blob.size <= Blob.MIN_SIZE) {
                blob.markObjectForRemoval();
            } else {
                blob.split();
            }

            this.markObjectForRemoval();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);

        double radius = this.getRadius();
        g2d.setColor(Color.WHITE);
        g2d.drawOval(
                (int) (this.screenPosition.getX() - radius),
                (int) (this.screenPosition.getY() - radius),
                (int) (radius * 2),
                (int) (radius * 2)
        );

    }
}
