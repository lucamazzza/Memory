package ch.mazluc.game;

/*
 * MIT License
 * 
 * Copyright (c) 2023 Luca Mazza
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/**
 * <p>
 * Utility class for ANSI escape sequences. Implements methods for setting
 * background and foreground colors, moving the cursor, clearing the screen,
 * and resetting the output format. Color codes are defined as constants and
 * color codes with 16 or 256 bit format are not supported.
 * 
 * <ul>
 * <li>Find out more at https://en.wikipedia.org/wiki/ANSI_escape_code;
 * <li>GitHub Repo: https://github.com/lucamazzza/ANSIUtility;
 * </ul>
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * {@code
 * ANSIUtils.clearScreen(); // clear the screen
 * ANSIUtils.moveTo(10, 10); // move the cursor to row 10 and column 10
 * ANSIUtils.setForegroundColor(ANSIUtils.RED); // set the foreground color to red
 * ANSIUtils.setBackgroundColor(ANSIUtils.YELLOW); // set the background color to yellow
 * System.out.println("Hello, World!"); // print "Hello, World!"
 * ANSIUtils.reset(); // reset the output format
 * }
 * </pre>
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public class ANSIUtils {

    /**
     * ANSI black color code ({@value})
     */
    public static final int BLACK = 30;

    /**
     * ANSI red color code ({@value})
     */
    public static final int RED = 31;

    /**
     * ANSI green color code ({@value})
     */
    public static final int GREEN = 32;

    /**
     * ANSI yellow color code ({@value})
     */
    public static final int YELLOW = 33;

    /**
     * ANSI blue color code ({@value})
     */
    public static final int BLUE = 34;

    /**
     * ANSI magenta color code ({@value})
     */
    public static final int MAGENTA = 35;

    /**
     * ANSI cyan color code ({@value})
     */
    public static final int CYAN = 36;

    /**
     * ANSI white color code ({@value})
     */
    public static final int WHITE = 37;

    /**
     * ANSI bright black color code ({@value})
     */
    public static final int BRIGHT_BLACK = 90;

    /**
     * ANSI bright red color code ({@value})
     */
    public static final int BRIGHT_RED = 91;

    /**
     * ANSI bright green color code ({@value})
     */
    public static final int BRIGHT_GREEN = 92;

    /**
     * ANSI bright yellow color code ({@value})
     */
    public static final int BRIGHT_YELLOW = 93;

    /**
     * ANSI bright blue color code ({@value})
     */
    public static final int BRIGHT_BLUE = 94;

    /**
     * ANSI bright magenta color code ({@value})
     */
    public static final int BRIGHT_MAGENTA = 95;

    /**
     * ANSI bright cyan color code ({@value})
     */
    public static final int BRIGHT_CYAN = 96;

    /**
     * ANSI bright white color code ({@value})
     */
    public static final int BRIGHT_WHITE = 97;

    /**
     * ANSI reset escape ({@value})
     */
    public static final String RESET = "\u001B[0m";

    /**
     * ANSI clear screen escape ({@value})
     */
    public static final String CLS = "\u001B[2J";

    /**
     * ANSI backspace escape ({@value})
     */
    public static final String BS = "\u001B[1D";

    /**
     * ANSI delete escape ({@value})
     */
    public static final String DEL = "\u001B[1P";

    /**
     * ANSI bell escape ({@value})
     */
    public static final String BEL = "\u0007";

    /**
     * ANSI home escape ({@value})
     */
    public static final String HOME = "\u001B[H";

    /**
     * ANSI bold escape ({@value})
     */
    public static final String BOLD = "\u001B[1m";

    /**
     * ANSI normal escape ({@value})
     */
    public static final String NORMAL = "\u001B[0m";

    /**
     * ANSI reverse on escape ({@value})
     */
    public static final String REVERSE_ON = "\u001B[7m";

    /**
     * ANSI reverse off escape ({@value})
     */
    public static final String REVERSE_OFF = "\u001B[27m";

    /**
     * ANSI Standard Escape Sequence ({@value})
     */
    private static final String ESC = "\u001B[";

    /**
     * Private constructor to prevent instantiation,
     * as the class is a utility class and its use is
     * only static.
     * 
     * @throws IllegalStateException if called
     */
    private ANSIUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Resets the output format to the default.
     */
    public static void reset() {
        System.out.print(RESET);
    }

    /**
     * Clears the screen
     */
    public static void clearScreen() {
        System.out.print(CLS);
        System.out.println(HOME);
    }

    /**
     * Sets the output format to bold.
     */
    public static void setBold() {
        System.out.print(BOLD);
    }

    /**
     * Sets the output format to normal.
     */
    public static void setNormal() {
        System.out.print(NORMAL);
    }

    /**
     * Moves the cursor to the specified position, based on row and column.
     * Coordinates are specified starting from 1, where 1,1 is the top left
     * corner of the screen. If the cursor is already at the specified position,
     * this method does nothing.
     *
     * @param row the vertical coordinate
     * @param col the horizontal coordinate
     */
    public static void moveTo(int row, int col) {
        if (row > 0 && col > 0) {
            System.out.print(ESC + row + ";" + col + "H");
        }
    }

    /**
     * Define the foreground color at the next prints.
     * The parameter code must be a valid color code (i.e. one of the constants
     * defined in this class). If the color code is invalid, this method does
     * nothing.
     * 
     * @param code the color code
     */
    public static void setForegroundColor(int code) {
        if (isColorCodeValid(code)) {
            System.out.print(ESC + code + "m");
        }
    }

    /**
     * Sets the background color.
     * 
     * @param code the color
     */
    public static void setBackgroundColor(int code) {
        if (isColorCodeValid(code)) {
            System.out.print(ESC + (code + 10) + "m");
        }
    }

    /**
     * Sets the foreground and background color.
     * The parameters fg and bg must be valid color codes (i.e. one of the
     * constants defined in this class). If the color codes are invalid, this
     * method does nothing.
     * 
     * @param bg the background color
     * @param fg the foreground color
     */
    public static void setColor(int bg, int fg) {
        if (isColorCodeValid(bg) && isColorCodeValid(fg)) {
            System.out.print(ESC + fg + ";" + (bg + 10) + "m");
        }
    }

    /**
     * Check if the color code (3/4 bits) is valid.
     *
     * @param code the color code
     * @return true if the color code is valid, false otherwise
     */
    private static boolean isColorCodeValid(int code) {
        return code >= 30 && code <= 37 || code >= 90 && code <= 97;
    }
}