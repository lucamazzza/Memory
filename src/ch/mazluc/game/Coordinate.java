package ch.mazluc.game;

/**
 * <p>
 * Represents a coordinate on a 2D grid.
 * A coordinate is defined by its x and y components.
 * 
 * <p>
 * ⚠️ The coordinates are 1-based,
 * so to use them on a 2D grid and translate to array coordinates.
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public class Coordinate {

    /**
     * The x coordinate.
     */
    private final int x;

    /**
     * The y coordinate.
     */
    private final int y;

    /**
     * Creates a new coordinate.
     * Must define both components.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate.
     * 
     * @return the x coordinate
     */
    public int x() {
        return this.x;
    }

    /**
     * Returns the y coordinate.
     *
     * @return the y coordinate
     */
    public int y() {
        return this.y;
    }

    /**
     * Returns the true x coordinate.
     *
     * @return the true x coordinate
     */
    public int trueX() {
        return this.x - 1;
    }

    /**
     * Returns the true y coordinate.
     *
     * @return the true y coordinate
     */
    public int trueY() {
        return this.y - 1;
    }
}
