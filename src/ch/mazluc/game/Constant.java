package ch.mazluc.game;

/**
 * <p>
 * Constants used in the game.
 * 
 * <p>
 * Modify these constants to change the game's base settings.
 * 
 * <p>
 * ⚠️ These constants are not meant to be changed,
 * as they are defined for a stable game experience.
 * Modify at your own risk.
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public final class Constant {

    /**
     * Private constructor to prevent instantiation,
     * as the class is a utility class and its use is
     * only static.
     * @throws IllegalStateException if called
     */
    private Constant() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * The maximum number of cells in the grid ({@value})
     */
    public static final int MAX_CELLS = 186;

    /**
     * The minimum number of players ({@value})
     */
    public static final int MIN_PLAYERS = 2;

    /**
     * The maximum number of players ({@value})
     */
    public static final int MAX_PLAYERS = 6;

    /**
     * The minimum length of a player name ({@value})
     */
    public static final int MIN_PLAYER_NAME_LENGTH = 3;

    /**
     * The maximum length of a player name ({@value})
     */
    public static final int MAX_PLAYER_NAME_LENGTH = 15;

    /**
     * The points for a jolly card ({@value})
     */
    public static final int JOLLY_POINTS = 20;
}