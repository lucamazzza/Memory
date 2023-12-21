package ch.mazluc.game;

/**
 * <p>
 * Game execution.
 * 
 * <p>
 * Rules of the game "Memory":
 * <ul>
 * <li>The game starts and displays the start screen;
 * <li>Then the game is initialized:
 * <ul>
 * <li>The user inputs how many players will play;
 * <li>The user inputs the names of each player (a String between 3 and 15
 * characters);
 * <li>The user inputs the size of the grid (the number of cards must be even);
 * </ul>
 * <li>The game starts:
 * <ul>
 * <li>Every user has to guess two cards:
 * <li>If the guess is correct the user gets the points of the card and
 * continues;
 * <li>If the guess is incorrect the user loses the points of the card and the
 * next player plays;
 * <li>The game ends when all cards are matched.
 * </ul>
 * </ul>
 * 
 * <p>
 * <b>Extra features</b>:
 * 
 * <p>
 * Special cards:
 * <ul>
 * <li>Bomb: when the user flips it, they are disqualified;
 * <li>Jolly: when the user flips it, they get 20 points without matching
 * any card;
 * </ul>
 * 
 * <p>
 * Random player names:
 * <ul>
 * <li>When defining players' names the user can generate random names (uuid
 * based for now);
 * </ul>
 * 
 * <p>
 * Better graphical output:
 * <ul>
 * <li>The graphics use ANSI Escapes to refresh the screen and display a colored
 * output;
 * </ul>
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public class MemoryTest {

    /**
     * Executes the main function of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.printStartScreen();
        game.initialize();
        game.start();
        game.destroy();
    }
}
