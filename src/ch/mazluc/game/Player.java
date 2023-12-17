package ch.mazluc.game;

import java.util.Random;

/**
 * <h1>
 * Player
 * </h1>
 * 
 * <p>
 * A player in a card game.
 * A player has a name, collects a score during the game
 * and is either dead (`true`) or alive (`false`).
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public class Player {

    /**
     * The name of the player.
     */
    private String name;

    /**
     * The score of the player.
     */
    private int score;

    /**
     * Whether the player is dead (`true`) or alive (`false`).
     */
    private boolean dead;

    /**
     * The color of the player in the UI.
     */
    private int color;

    /**
     * Constructor.
     * Must provide a name for each player
     * and the name must be at least 3 characters long
     * and maximum 15 characters long
     * 
     * @param name
     */
    public Player(String name) {
        if (name.length() < 3 || name.length() > 15) {
            // TODO: Manage case where name is too short
            // THIS IS TMP
            throw new IllegalArgumentException("Player name must be at least 3 and 15characters long");
        }
        this.name = name;
        this.color = randomColor();
        this.score = 0;
        this.dead = false;
    }

    /**
     * Generate a random color number.
     * 
     * @return the color
     */
    private int randomColor() {
        Random random = new Random();
        return random.nextInt(7) + 31;
    }

    /**
     * Get the color of the player in the UI.
     * 
     * @return the color
     */
    public int getColor() {
        return this.color;
    }

    /**
     * Get the name of the player.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the score of the player.
     * 
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Increment the score of the player.
     * 
     * @param amount the amount to increment
     */
    public void incrementScore(int amount) {
        this.score += amount;
    }

    /**
     * Check if the player is dead.
     *
     * @return true if the player is dead
     */
    public boolean isDead() {
        return this.dead;
    }

    /**
     * Kill the player.
     */
    public void kill() {
        this.dead = true;
    }
}
