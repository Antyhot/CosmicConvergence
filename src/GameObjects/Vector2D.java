package GameObjects;

/**
 * Vector2D class. Represents a 2D vector.
 */
public class Vector2D {
    private double x;
    private double y;

    /**
     * Default constructor for Vector2D. Sets x and y to 0.
     */
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructor for Vector2D. Sets x and y to the given values.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor for Vector2D. From a given Vector2D, sets x and y to the given.
     * 
     * @param v Vector2D to copy
     */
    public Vector2D(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Adds the given Vector2D to this Vector2D.
     * 
     * @param v Vector2D to add
     * @return v resulting arrow
     */
    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;

        return this;
    }

    /**
     * Adds the given x and y to this Vector2D.
     * 
     * @param x x-coordinate to add
     * @param y y-coordinate to add
     */
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;

        return this;
    }

    /**
     * Subtracts the given Vector2D from this Vector2D.
     * 
     * @param v Vector2D to subtract
     */
    public Vector2D subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;

        return this;
    }

    /**
     * Subtracts the given x and y from this Vector2D.
     * 
     * @param x x-coordinate to subtract
     * @param y y-coordinate to subtract
     */
    public Vector2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;

        return this;
    }

    /**
     * Multiplies this Vector2D by the given scalar.
     * 
     * @param scalar scalar to multiply by
     */
    public Vector2D multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;

        return this;
    }

    /**
     * Divides this Vector2D by the given scalar.
     * 
     * @param scalar scalar to divide by
     */
    public Vector2D divide(double scalar) {
        this.x /= scalar;
        this.y /= scalar;

        return this;
    }

    /**
     * Returns the magnitude of this Vector2D.
     * 
     * @return magnitude of this Vector2D
     */
    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Normalizes this Vector2D.
     */
    public Vector2D normalize() {
        double magnitude = this.magnitude();
        if (magnitude != 0) {
            this.divide(magnitude);
        }

        return this;
    }

    /**
     * Limits this Vector2D to the given maximum.
     * 
     * @param max maximum magnitude
     */
    public Vector2D limit(double max) {
        if (this.magnitude() > max) {
            this.normalize();
            this.multiply(max);
        }

        return this;
    }

    /**
     * Returns the distance between this Vector2D and the given Vector2D.
     * 
     * @param v Vector2D to find distance to
     * @return distance between this Vector2D and the given Vector2D
     */
    public double distance(Vector2D v) {
        return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
    }

    /**
     * Returns the dot product of this Vector2D and the given Vector2D.
     * 
     * @param v Vector2D to find dot product with
     * @return dot product of this Vector2D and the given Vector2D
     */
    public double dot(Vector2D v) {
        return this.x * v.x + this.y * v.y;
    }

    public static double dot(Vector2D v1, Vector2D v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    /**
     * Set the magnitude of this Vector2D to the given magnitude.
     * 
     * @param magnitude magnitude to set
     */
    public Vector2D setMagnitude(double magnitude) {
        this.normalize();
        this.multiply(magnitude);

        return this;
    }

    /**
     * Set the direction of this Vector2D to the given direction.
     * 
     * @param direction direction to set in radians
     */
    public Vector2D setDirection(double direction) {
        double magnitude = this.magnitude();
        this.x = Math.cos(direction) * magnitude;
        this.y = Math.sin(direction) * magnitude;

        return this;
    }

    /**
     * Set the direction of this Vector2D to the given Vector2D.
     * 
     * @param v Vector2D to set direction to
     */
    public Vector2D setDirection(Vector2D v) {
        double magnitude = this.magnitude();
        this.x = v.x / magnitude;
        this.y = v.y / magnitude;

        return this;
    }

    /**
     * Set the direction of this Vector2D to the given x and y.
     * 
     * @param x x-coordinate to set direction to
     * @param y y-coordinate to set direction to
     */
    public Vector2D setDirection(double x, double y) {
        double magnitude = this.magnitude();
        this.x = x / magnitude;
        this.y = y / magnitude;

        return this;
    }

    /**
     * Set the x and y of this Vector2D to the given x and y.
     * 
     * @param x x-coordinate to set
     * @param y y-coordinate to set
     */
    public Vector2D set(double x, double y) {
        this.x = x;
        this.y = y;

        return this;
    }

    /**
     * Set the x and y of this Vector2D to the given Vector2D.
     * 
     * @param v Vector2D to set
     */
    public Vector2D set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;

        return this;
    }

    /**
     * Returns a Vector2D representing the normal of this Vector2D and the given
     * Vector2D.
     * 
     * @param v Vector2D to find normal with
     * @return Vector2D representing the normal of this Vector2D and the given
     *         Vector2D
     */
    public Vector2D getNormal(Vector2D v) {
        return new Vector2D(
            this.y - v.y,
            v.x - this.x
        );
    }

    /**
     * Returns a Vector2D representing the normal of this Vector2D.
     * 
     * @return Vector2D representing the normal of this Vector2D
     */
    public Vector2D getNormal() {
        return new Vector2D(
            this.y,
            -this.x
        );
    }

    /**
     * Returns a copy of this Vector2D.
     * 
     * @return copy of this Vector2D
     */
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    /**
     * Returns a Vector2D representing the lerp of this Vector2D and the given.
     * @param v Vector2D to lerp with
     * @param amount amount to lerp
     * @return Vector2D representing the lerp of this Vector2D and the given
     */
    public Vector2D lerp(Vector2D v, double amount) {
        this.x += (v.x - this.x) * amount;
        this.y += (v.y - this.y) * amount;

        return this;
    }

    /**
     * Returns the angle in radians between the positive x-axis and the vector represented by this Vector2D.
     *
     * @return the angle in radians
     */
    public double angle() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Returns a String representation of this Vector2D.
     * 
     * @return String representation of this Vector2D
     */
    public String toString() {
        return "Vector2D(" + this.x + ", " + this.y + ")";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}