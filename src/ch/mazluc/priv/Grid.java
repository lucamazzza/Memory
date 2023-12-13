package ch.mazluc.priv;

/**
 * <h1>
 * Grid
 * </h1>
 *
 * <p>
 * A grid of cards.
 * It is implemented as a 2D array of cards.
 * 
 * <p>
 * ⚠️ The grids coordinates are not the same as
 * defined in the class `Coordinate`, as they are
 * 0-based, instead of 1-based in `Coordinate`.
 *
 * <p>
 * The grid is filled with random cards.
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public class Grid {

    /**
     * The grid of cards.
     * It is implemented as a 2D array of cards.
     * 
     * <p>
     * ⚠️ The grids coordinates are not the same as
     * defined in the class `Coordinate`, as they are
     * 0-based, instead of 1-based in `Coordinate`.
     */
    private Card[][] grid;

    /**
     * Constructor.
     * Must be called with the number of rows and columns.
     * 
     * @param rows row size of the grid
     * @param cols column size of the grid
     */
    public Grid(int rows, int cols) {
        this.grid = new Card[rows][cols];
    }

    /**
     * Get the card at the specified coordinate.
     * 
     * @param coord the coordinate
     * @return the card
     */
    public Card getCard(Coordinate coord) {
        return this.grid[coord.trueY()][coord.trueX()];
    }

    /**
     * Set the card at the specified coordinate.
     * 
     * <p>
     * ⚠️ The coordinate is 1-based.
     *
     * @param coord the coordinate
     * @param card  the card
     */
    public void setCard(Coordinate coord, Card card) {
        this.grid[coord.trueY()][coord.trueX()] = card;
    }

    /**
     * Get the row size of the grid.
     *
     * @return the row size
     */
    public int getRowSize() {
        return this.grid.length;
    }

    /**
     * Get the column size of the grid.
     * 
     * @return the column size
     */
    public int getColSize() {
        return this.grid[0].length;
    }

    /**
     * Fill the grid with random cards.
     */
    public void fill() {
        // TODO: Fill the grid with random cards
    }
}