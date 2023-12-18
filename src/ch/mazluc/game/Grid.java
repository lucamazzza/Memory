package ch.mazluc.game;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * <p>
 * A grid of cards.
 * 
 * <p>
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
    private final Card[][] cards;

    /**
     * The random number generator.
     * It is used to generate random cards.
     */
    private static final Random random = new Random();

    /**
     * Constructor.
     * Must be called with the number of rows and columns.
     * 
     * @param rows row size of the grid
     * @param cols column size of the grid
     */
    public Grid(int rows, int cols) {
        this.cards = new Card[rows][cols];
    }

    /**
     * Check if the grid is empty.
     * 
     * @return true if the grid is empty, false otherwise
     */
    public boolean isEmpty() {
        for (int i = 1; i <= getRowSize(); i++) {
            for (int j = 1; j <= getColSize(); j++) {
                if (this.getCard(new Coordinate(i, j)) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get the card at the specified coordinate.
     * 
     * @param coord the coordinate
     * @return the card
     */
    public Card getCard(Coordinate coord) {
        return this.cards[coord.trueX()][coord.trueY()];
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
        this.cards[coord.trueX()][coord.trueY()] = card;
    }

    /**
     * Get the row size of the grid.
     *
     * @return the row size
     */
    public int getRowSize() {
        return this.cards.length;
    }

    /**
     * Get the column size of the grid.
     * 
     * @return the column size
     */
    public int getColSize() {
        return this.cards[0].length;
    }

    /**
     * Pop the card from the grid.
     * Ignore the card if it is not in the grid and if it is null.
     * 
     * @param card the card
     */
    public void popCard(Card card) {
        for (int i = 1; i <= getRowSize(); i++) {
            for (int j = 1; j <= getColSize(); j++) {
                if (this.getCard(new Coordinate(i, j)) != null && this.getCard(new Coordinate(i, j)).equals(card)) {
                    this.setCard(new Coordinate(i, j), null);
                }
            }
        }
    }

    /**
     * Flip all the cards in the grid on the backside.
     */
    public void flipAllCards() {
        for (int i = 1; i <= getRowSize(); i++) {
            for (int j = 1; j <= getColSize(); j++) {
                if (this.getCard(new Coordinate(i, j)) != null) {
                    this.getCard(new Coordinate(i, j)).flip(false);
                }
            }
        }
    }

    /**
     * Push a card in a random free cell.
     * 
     * @param card the card
     */
    public void pushInRandomFreeCell(Card card) {
        Coordinate coord;
        do {
            coord = new Coordinate(random.nextInt(getRowSize()) + 1, random.nextInt(getColSize()) + 1);
        } while (this.getCard(coord) != null);
        this.setCard(coord, card);
    }

    /**
     * Check if the grid contains a card.
     * 
     * @param card the card
     * @return true if the grid contains the card, false otherwise
     */
    public boolean containsCard(char card) {
        for (int i = 1; i <= getRowSize(); i++) {
            for (int j = 1; j <= getColSize(); j++) {
                boolean isSet = this.getCard(new Coordinate(i, j)) != null;
                if (isSet && this.getCard(new Coordinate(i, j)).getSymbol() == card) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if the character is displayable.
     * 
     * @param ch the character
     * @return true if the character is displayable, false otherwise
     */
    public static boolean isCharacterDisplayable(char ch) {
        String regex = "\\P{Print}";
        String str = Character.toString(ch);
        return !Pattern.matches(regex, str);
    }

    /**
     * Get a random unique character.
     * 
     * @return the random unique character
     */
    public char getRandomUniqueChar() {
        char randomChar;
        do {
            randomChar = (char) (random.nextInt(256));
        } while (!isCharacterDisplayable(randomChar) || this.containsCard(randomChar) || randomChar == '!');
        return randomChar;
    }

    /**
     * Fill the grid with random cards.
     */
    public void fill() {
        for (int i = 0; i < (this.getRowSize() * this.getColSize()) - 2; i += 2) {
            char randomChar = getRandomUniqueChar();
            int score = random.nextInt(9) + 1;

            Card card1 = new Card(randomChar, score);
            Card card2 = new Card(randomChar, score);

            this.pushInRandomFreeCell(card1);
            this.pushInRandomFreeCell(card2);
        }
        Card bomb = new Card('ﬁ', true);
        Card jolly = new Card('§', Constant.JOLLY_POINTS, true);
        this.pushInRandomFreeCell(jolly);
        this.pushInRandomFreeCell(bomb);
    }

    /**
     * Print the grid.
     */
    public void print() {
        // Print indices for columns
        System.out.print("    "); // initial space for row indices
        for (int i = 1; i <= cards[0].length; i++) {
            System.out.printf(" %2d ", i);
        }
        System.out.println();
        for (int i = 0; i < cards.length; i++) {
            // Print top border for row
            System.out.print("    "); // initial space for row indices
            for (int j = 0; j < cards[i].length * 4 + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("┌");
                } else if (i == 0 && j < cards[i].length * 4 && j % 4 == 0) {
                    System.out.print("┬");
                } else if (i == 0 && j == cards[i].length * 4) {
                    System.out.print("┐");
                } else if (j == 0) {
                    System.out.print("├");
                } else if (j == cards[i].length * 4) {
                    System.out.print("┤");
                } else if (j < cards[i].length * 4 && j % 4 == 0) {
                    System.out.print("┼");
                } else {
                    System.out.print("─");
                }
            }
            System.out.println();
            System.out.printf("  %2d|", i + 1);
            for (int j = 0; j < cards[i].length; j++) {
                if (cards[i][j] != null) {
                    System.out.print(" ");
                    if (cards[i][j].isFlipped()) {
                        if (cards[i][j].isBomb()) {
                            ANSIUtils.setForegroundColor(ANSIUtils.RED);
                        } else if (cards[i][j].isJolly()) {
                            ANSIUtils.setForegroundColor(ANSIUtils.GREEN);
                        } else {
                            ANSIUtils.setForegroundColor(ANSIUtils.BRIGHT_YELLOW);
                        }
                        ANSIUtils.setBold();
                    }
                    cards[i][j].print();
                    ANSIUtils.reset();
                    System.out.print(" ");
                } else {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
        // Print bottom border for last row
        System.out.print("    "); // initial space for row indices
        for (

                int i = 0; i < cards[0].length * 4 + 1; i++) {
            if (i == 0) {
                System.out.print("└");
            } else if (i < cards[0].length * 4 && i % 4 == 0) {
                System.out.print("┴");
            } else if (i == cards[0].length * 4) {
                System.out.print("┘");
            } else {
                System.out.print("─");
            }
        }
        System.out.println();
    }
}