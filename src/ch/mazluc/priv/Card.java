package ch.mazluc.priv;

/**
 * <h1>
 * Card
 * </h1>
 * 
 * <p>
 * A card in a card game.
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
    private char symbol;

    /**
     * The amount of points.
     */
    private int points;

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
     * TODO: Check integrity of data
     * 
     * @param symbol the symbol of the card
     * @param points the amount of points
     */
    public Card(char symbol, int points) {
        // TODO: Check integrity of data
        this.symbol = symbol;
        this.points = 0;
        this.flipped = false;
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
        return this.symbol == card.symbol && this.points == card.points;
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
     * Check if the card is flipped.
     * 
     * @return true if the card is flipped
     */
    public boolean isFlipped() {
        return this.flipped;
    }
}
