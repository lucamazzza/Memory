package ch.mazluc.game;

/**
 * <p>
 * A card in a card game.
 * 
 * <p>
 * Every card has a symbol, an amount of points and a state.
 * The state represent whether the card is flipped (true),
 * so it shows its value, or not (false).
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public class Card {

    /**
     * The symbol of the card.
     */
    private final char symbol;

    /**
     * The amount of points.
     */
    private final int points;

    /**
     * The case of the bomb card.
     * Eliminates the player that flipped it.
     */
    private boolean bomb;

    /**
     * The case of the jolly card.
     * Adds extra points to the player that flipped it.
     */
    private boolean jolly;

    /**
     * The state of the card.
     */
    private boolean flipped;

    /**
     * Constructor.
     * Must provide a symbol and a points.
     * State is set to false by default given that
     * a card is flipped (the symbol is hidden) at
     * the start of every game.
     * 
     * @param symbol the symbol of the card
     * @param points the amount of points
     */
    public Card(char symbol, int points) {
        if (points < 1) {
            points = 1;
        }
        this.symbol = symbol;
        this.points = points;
        this.flipped = false;
    }

    /**
     * Constructor.
     * Case in which the card has to be a bomb.
     * 
     * @param symbol the symbol of the card
     * @param bomb   true if the card is a bomb
     */
    public Card(char symbol, boolean bomb) {
        this(symbol, 0);
        this.bomb = bomb;
    }

    /**
     * Constructor.
     * Case in which the card has to be a jolly.
     * 
     * @param symbol the symbol of the card
     * @param points the amount of points
     * @param jolly  true if the card is a jolly
     */
    public Card(char symbol, int points, boolean jolly) {
        this(symbol, points);
        this.jolly = jolly;
    }

    /**
     * Get the symbol of the card.
     * 
     * @return the symbol
     */
    public char getSymbol() {
        return this.symbol;
    }

    /**
     * Check if two cards are equal.
     * 
     * @param card the card to compare
     * @return true if the cards are equal
     */
    public boolean equals(Card card) {
        if (card == null) {
            return false;
        }
        if (this.bomb && card.bomb) {
            return true;
        }
        if (this.jolly && card.jolly) {
            return true;
        }
        return this.symbol == card.symbol && this != card;
    }

    /**
     * Get the string representation of the card.
     * 
     * @return the string
     */
    public String toString() {
        return this.symbol + "";
    }

    /**
     * Get the amount of points.
     * 
     * @return the amount of points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Flip the card.
     */
    public void flip() {
        this.flipped = !this.flipped;
    }

    /**
     * Flip the card to the specified side.
     * @param state the state
     */
    public void flip(boolean state) {
        this.flipped = state;
    }

    /**
     * Check if the card is flipped.
     * 
     * @return true if the card is flipped
     */
    public boolean isFlipped() {
        return this.flipped;
    }

    /**
     * Check if the card is a bomb.
     * 
     * @return true if the card is a bomb
     */
    public boolean isBomb() {
        return this.bomb;
    }

    /**
     * Check if the card is a jolly.
     * 
     * @return true if the card is a jolly
     */
    public boolean isJolly() {
        return this.jolly;
    }

    /**
     * Print the card.
     */
    public void print() {
        if (flipped) {
            System.out.print(this.symbol);
        } else {
            System.out.print("!");
        }
    }
}
