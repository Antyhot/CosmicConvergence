package GameObjects;

import Managers.GameManager;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

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

        this.size = Math.random() * (MAX_SIZE - MIN_SIZE) + MIN_SIZE;
        this.collider.radius = this.getRadius();

        this.velocity.set(
            Math.random(),
            Math.random()
        );

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
