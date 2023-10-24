package GameObjects;

import Managers.GameManager;
import Managers.Utils;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Asteroid class.
 */
public class Asteroid extends PhysicsObject<Asteroid> {
    private static final int MIN_SIDES = 5;
    private static final int MAX_SIDES = 8;

    private static final int MIN_SIZE = 100;
    private static final int MAX_SIZE = 500;

    public double size;

    protected int sides; // make it random. 
    protected ArrayList<Vector2D> polygonPoints = new ArrayList<>();

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

    private ArrayList<Vector2D> generatePolygonPoints(int sides, double size) {
        ArrayList<Vector2D> polygonPoints = new ArrayList<>();
    
        double angle = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = size * Math.cos(angle * i);
            double y = size * Math.sin(angle * i);
            polygonPoints.add(new Vector2D(x, y));
        }
    
        return polygonPoints;
    }

    private int generateRandomPolygonSides() {
        return (int) (Math.random() * (MAX_SIDES - MIN_SIDES) + MIN_SIDES);
    }


    @Override
    public void init() {
        super.init(this);
        this.size = Utils.randomBetween(MIN_SIZE, MAX_SIZE);
        this.collider.setRadius(this.getRadius());

        Vector2D[] visibleArea = this.gameManager.getCamera().visibleArea;

        double x = Utils.randomBetween(visibleArea[0].getX() - GameManager.SCREEN_WIDTH, visibleArea[1].getX() + GameManager.SCREEN_WIDTH);
        double y = Utils.randomBetween(visibleArea[0].getY() - GameManager.SCREEN_HEIGHT, visibleArea[2].getY() + GameManager.SCREEN_HEIGHT);

        if (x >= visibleArea[0].getX() + (double) GameManager.SCREEN_WIDTH / 2) {
            System.out.println("right");
            x += (double) GameManager.SCREEN_WIDTH / 2 + this.getRadius() * 2;
        } else {
            System.out.println("left");
            x -= (double) GameManager.SCREEN_WIDTH / 2 + this.getRadius() * 2;
        }

        if (y >= visibleArea[0].getY() + (double) GameManager.SCREEN_WIDTH / 2) {
            System.out.println("right");
            y += (double) GameManager.SCREEN_HEIGHT / 2 + this.getRadius() * 2;
        } else {
            System.out.println("left");
            y -= (double) GameManager.SCREEN_HEIGHT / 2 + this.getRadius() * 2;
        }

        // System.out.println("x = " + x);
        // System.out.println("y = " + y);

        this.setPosition(new Vector2D(x, y));

        Vector2D pointAtPlayer = new Vector2D(
                this.gameManager.getCamera().position.getX() - this.position.getX(),
                this.gameManager.getCamera().position.getY() - this.position.getY()
        );

        this.velocity.set(pointAtPlayer);
        this.velocity.normalize();
        // this.velocity.set(Math.random(), Math.random());
        this.velocity.multiply(5 / Math.sqrt(this.getRadius()));

        this.sides = this.generateRandomPolygonSides();
        this.polygonPoints = this.generatePolygonPoints(this.sides, this.getRadius());

        double offset = this.getRadius() / 2;
        for (Vector2D point : this.polygonPoints) {
            point.setMagnitude(
                point.magnitude() + (Math.random() * offset - offset / 2)
            );
        }
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

        g2d.setColor(Color.WHITE);
        g2d.drawPolygon(
            new Polygon(
                this.polygonPoints.stream()
                    .mapToInt(point -> (int) (point.getX() + this.screenPosition.getX()))
                    .toArray(),
                this.polygonPoints.stream()
                    .mapToInt(point -> (int) (point.getY() + this.screenPosition.getY()))
                    .toArray(),
                this.sides
            )
        );

    }
}
