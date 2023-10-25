package GameObjects;

import Managers.GameManager;
import Managers.Utils;
import java.awt.*;
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

    //Maximum offset when pointing at the player in radians (15 degrees by default)
    private static final double MAX_RAD_OFFSET = Math.toRadians(15);

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

        Random random = new Random();
        this.size = Utils.randomBetween(MIN_SIZE, MAX_SIZE);
        this.collider.setRadius(this.getRadius());


        //Spawn asteroid just outside of visible area
        Camera camera = this.gameManager.getCamera();
        Vector2D[] visibleArea = camera.visibleArea;

        double x;
        double y;
        if (random.nextBoolean()) {
            x = Utils.randomBetween(visibleArea[0].getX(), camera.visibleArea[1].getX());
            y = random.nextBoolean() ? (visibleArea[0].getY() - (this.getRadius() * 2)) : (visibleArea[2].getY() + (this.getRadius() * 2));
        } else {
            x = random.nextBoolean() ? visibleArea[0].getX() - this.getRadius() * 2 : visibleArea[1].getX() + this.getRadius() * 2;
            y = Utils.randomBetween(visibleArea[0].getY() , camera.visibleArea[2].getY());
        }

        this.setPosition(new Vector2D(x, y));


        //Calculate the velocity of the asteroid to point in the area of the player
        Vector2D pointAtPlayer = new Vector2D(
                camera.position.getX() - this.position.getX(),
                camera.position.getY() - this.position.getY()
        );
        pointAtPlayer.normalize();

        //Add offset to the angle of the velocity vector
        double magnitude = pointAtPlayer.magnitude();
        pointAtPlayer.setX(Math.cos(pointAtPlayer.angle() + Utils.randomBetween(-1 * MAX_RAD_OFFSET, MAX_RAD_OFFSET)) * magnitude);
        this.velocity.set(pointAtPlayer);

        this.velocity.multiply(5 / Math.sqrt(this.getRadius()));

        //Generate random polygon to represent the asteroid
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

        this.checkForRemoval();
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
        g2d.fillPolygon(
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

    private void checkForRemoval() {
        if (this.position.getX() < this.gameManager.getCamera().visibleArea[0].getX() - this.gameManager.screenWidth ||
            this.position.getX() > this.gameManager.getCamera().visibleArea[1].getX() + this.gameManager.screenWidth ||
            this.position.getY() < this.gameManager.getCamera().visibleArea[0].getY() - this.gameManager.screenHeight ||
            this.position.getY() > this.gameManager.getCamera().visibleArea[2].getY() + this.gameManager.screenHeight) {
            this.markObjectForRemoval();
        }
    }
}
