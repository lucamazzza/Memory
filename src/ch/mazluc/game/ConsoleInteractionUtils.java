package ch.mazluc.game;

import java.util.Scanner;

/**
 * <p>
 * Utility class for console interaction.
 * Reads and checks different kinds of user input using
 * the `java.util.Scanner` class.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * {@code
 * ConsoleInteractionUtils utils = new ConsoleInteractionUtils();
 * int value = utils.readInt(); // read an integer without checking a range
 * String value = utils.readNonBlankOrEmptyString(); // read a non-blank or empty string value
 * int valueInRange = utils.readIntInRange(1, 10); // read an integer in the range
 * }
 * </pre>
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public class ConsoleInteractionUtils {

    /**
     * The scanner used to read user input.
     */
    private final Scanner in;

    /**
     * Creates a new instance of the `ConsoleInteractionUtils` class.
     */
    public ConsoleInteractionUtils() {
        this.in = new Scanner(System.in);
        ANSIUtils.clearScreen();
    }

    /**
     * Reads an integer from the user.
     * Prints an error message if the input is not an integer.
     * Prints a prompt message before reading the input.
     * 
     * @param msg the prompt message
     * @return the integer
     */
    public int readInt(String msg) {
        System.out.print(msg + ": ");
        int value;
        while (!this.in.hasNextInt()) {
            this.in.nextLine();
            ANSIUtils.setForegroundColor(ANSIUtils.RED);
            System.out.println("Error, must be an integer");
            ANSIUtils.reset();
            System.out.print(msg + ": ");
        }
        value = this.in.nextInt();
        return value;
    }

    /**
     * Reads an integer from the user, verifying that it is in the range.
     * Prints an error message if the input is not an integer.
     * Prints a prompt message before reading the input.
     * 
     * @param min minimum value
     * @param max maximum value
     * @return the integer
     */
    public int readIntInRange(int min, int max) {
        int value = this.readInt("Insert a number [" + min + "-" + max + "]");
        while (value < min || value > max) {
            ANSIUtils.setForegroundColor(ANSIUtils.RED);
            System.out.println("Number out of range");
            ANSIUtils.reset();
            value = this.readInt("Insert a number [" + min + "-" + max + "]");
        }
        return value;
    }

    /**
     * Reads a non-blank or empty string from the user.
     * Prints an error message if the input is empty or contains only
     * white spaces.
     * Prints a prompt message before reading the input.
     * 
     * @param msg the prompt message
     * @return the string
     */
    public String readNonBlankOrEmptyString(String msg) {
        String value = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg + ": ");
            value = this.in.nextLine().strip().trim();
            if (value.isBlank() || value.isEmpty()) {
                ANSIUtils.setForegroundColor(ANSIUtils.RED);
                System.out.println("Error: string is empty or contains only white spaces.");
                ANSIUtils.reset();
            } else {
                correctInput = true;
            }
        }
        return value;
    }

    /**
     * Reads a string from user input with a minimum and maximum length.
     *
     * @param msg       the message to display before prompting for input
     * @param minLength the minimum length of the string
     * @param maxLength the maximum length of the string
     * @return the string entered by the user
     */
    public String readStringWithMinMaxLength(String msg, int minLength, int maxLength) {
        String value = "";
        boolean correctInput = false;
        while (!correctInput) {
            System.out.print(msg + ": ");
            value = this.in.nextLine().strip().trim();
            if ((value.length() < minLength || value.length() > maxLength) && !value.isBlank()) {
                ANSIUtils.setForegroundColor(ANSIUtils.RED);
                System.out.println("Error: string is shorter than " + minLength + " characters or longer than "
                        + maxLength + " characters.");
                ANSIUtils.reset();
            } else {
                correctInput = true;
            }
        }
        return value;
    }

    /**
     * Check if the grid size is valid.
     * 
     * @param height height of the grid
     * @param width  width of the grid
     * @return true if the grid size is valid, false otherwise
     */
    public boolean isValidGridSize(int height, int width) {
        return height > 0 && width > 0 && height * width <= Constant.MAX_CELLS && height * width % 2 == 0;
    }

    /**
     * Reads a valid grid size from the user.
     * The grid size is valid if the height and width are greater than 1,
     * the height * width is less than the maximum number of cells,
     * and the height * width is even.
     * 
     * @return the grid size
     */
    public int[] readValidGridSize() {
        int height;
        int width;
        boolean validGridSize;
        String message = """
                Insert height and width of the grid such that:
                - height > 1,
                - width > 1,
                - height * width < %d
                - height * width %% 2 == 0
                """.formatted(Constant.MAX_CELLS);
        System.out.println(message);
        do {
            height = this.readIntInRange(2, Constant.MAX_CELLS / 2);
            width = this.readIntInRange(2, Constant.MAX_CELLS / height);
            validGridSize = isValidGridSize(height, width);
        } while (!validGridSize);
        return new int[] { height, width };
    }

    /**
     * Check if the coordinate is in the bounds of the grid.
     * 
     * @param coord the coordinate
     * @param grid  the grid
     * @return true if the coordinate is in the bounds of the grid, false otherwise
     */
    public boolean isCoordinateInBounds(Coordinate coord, Grid grid) {
        return coord.trueX() >= 0 && coord.trueX() < grid.getRowSize() && coord.trueY() >= 0
                && coord.trueY() < grid.getColSize();
    }

    /**
     * Reads a valid coordinate from the user.
     * A valid coordinate is a coordinate that is in the bounds of the grid.
     * 
     * @param gridHeight the height of the grid
     * @param gridWidth  the width of the grid
     * @return the coordinate
     */
    public Coordinate readValidCoordinate(int gridHeight, int gridWidth) {
        String message = String.format("""
                Insert row and column of the coordinate such that:
                - row > 0,
                - row < %d,
                - col > 0,
                - col < %d
                """, gridHeight, gridWidth);

        System.out.println(message);
        System.out.println("Insert row: ");
        int x = this.readIntInRange(1, gridHeight);
        System.out.println("Insert column: ");
        int y = this.readIntInRange(1, gridWidth);
        return new Coordinate(x, y);
    }

    /**
     * Reads an integer from the user.
     */
    public void readEnterToContinue() {
        System.out.println("Press enter to continue...");
        this.in.nextLine();
    }

    /**
     * Clears the scanner.
     */
    public void clearScanner() {
        this.in.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        this.in.close();
    }
}