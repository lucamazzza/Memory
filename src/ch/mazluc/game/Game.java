package ch.mazluc.game;

import java.util.Random;

/**
 * <p>
 * Manages the game cycle.
 * 
 * <p>
 * Every game has a list of players,
 * a grid on which cards are layed upon
 * and a console interaction utility that manages
 * user input.
 * 
 * @author Luca Mazza
 * @version 1.0
 */
public class Game {

    /**
     * The players in the game.
     */
    private Player[] players;

    /**
     * The grid in the game.
     */
    private Grid grid;

    /**
     * The console interaction util.
     */
    private final ConsoleInteractionUtils console;

    /**
     * The random number generator.
     */
    private static final Random random = new Random();

    /**
     * Constructor.
     * Initializes the console interaction util.
     */
    public Game() {
        this.console = new ConsoleInteractionUtils();
    }

    /**
     * Print the start screen.
     * Prints the title of the game.
     * Reads the user input to continue.
     */
    public void printStartScreen() {
        String title = """



                                    .--------.-----.--------.-----.----.--.--.
                                    |        |  -__|        |  _  |   _|  |  |
                                    |__|__|__|_____|__|__|__|_____|__| |___  |
                                     Created by Luca Mazza © 2023      |_____|
                """;
        ANSIUtils.setForegroundColor(ANSIUtils.BLUE);
        System.out.println(title);
        ANSIUtils.reset();
        ANSIUtils.setColor(ANSIUtils.BRIGHT_BLUE, ANSIUtils.WHITE);
        System.out.print("\t\t\t");
        this.console.readEnterToContinue();
        ANSIUtils.reset();
        ANSIUtils.clearScreen();
    }

    /**
     * Sort the players by score.
     * Sorts the players list in ascending order by score.
     */
    private void sortPlayersByScore() {
        for (int i = 0; i < this.players.length - 1; i++) {
            for (int j = 0; j < this.players.length - i - 1; j++) {
                if (this.players[j].getScore() < this.players[j + 1].getScore()) {
                    Player temp = this.players[j];
                    this.players[j] = this.players[j + 1];
                    this.players[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Generate a random color number.
     * 
     * @return the color
     */
    private int randomColor() {
        return random.nextInt(7) + 31;
    }

    /**
     * Initializes the game.
     * Reads the game settings from the user.
     * Initializes the players and the grid.
     * Fills the grid with cards.
     */
    public void initialize() {
        ANSIUtils.setBold();
        ANSIUtils.setForegroundColor(ANSIUtils.BLUE);
        System.out.println("GAME SETTINGS: ");
        ANSIUtils.reset();
        // INIT PLAYER LIST
        int playerCount = this.console.readIntInRange(Constant.MIN_PLAYERS, Constant.MAX_PLAYERS);
        this.console.clearScanner();
        this.players = new Player[playerCount];
        // INIT PLAYER NAMES
        for (int i = 0; i < this.players.length; i++) {
            String name = this.console.readStringWithMinMaxLength("Insert player #" + (i + 1) + " name [↵ for random]",
                    Constant.MIN_PLAYER_NAME_LENGTH, Constant.MAX_PLAYER_NAME_LENGTH);
            this.players[i] = new Player(name, randomColor());
        }
        // INIT GRID
        int[] gridSize = this.console.readValidGridSize();
        this.grid = new Grid(gridSize[0], gridSize[1]);
        // FILL GRID
        this.grid.fill();
    }

    /**
     * Print the UI of the game.
     * 
     * @param currentPlayer the index of the current player
     */
    private void printUI(int currentPlayer) {
        ANSIUtils.clearScreen();
        ANSIUtils.setBackgroundColor(players[currentPlayer].getColor());
        System.out
                .println(players[currentPlayer].getName() + "'s turn (" + players[currentPlayer].getScore() + ")");
        ANSIUtils.reset();
        this.grid.print();
    }

    /**
     * Print the leaderboard.
     */
    private void printLeaderboard() {
        sortPlayersByScore();
        ANSIUtils.setBold();
        ANSIUtils.setForegroundColor(ANSIUtils.BLUE);
        System.out.println("LEADERBOARD: ");
        ANSIUtils.reset();
        ANSIUtils.setBackgroundColor(this.players[0].getColor());
        System.out.printf("%-15s %2d", this.players[0].getName(), this.players[0].getScore());
        ANSIUtils.reset();
        ANSIUtils.setForegroundColor(ANSIUtils.YELLOW);
        System.out.println(" ♛");
        ANSIUtils.reset();
        for (int i = 1; i < this.players.length; i++) {
            ANSIUtils.setBackgroundColor(this.players[i].getColor());
            System.out.printf("%-15s %2d\n", this.players[i].getName(), this.players[i].getScore());
            ANSIUtils.reset();
        }
        this.console.readEnterToContinue();
    }

    /**
     * Take a guess.
     * Asks the user for a coordinate, which is checked if it is in the bounds and
     * if it is not null.
     * 
     * @param player the index of the player
     * @return the card
     */
    public Card takeGuess(int player) {
        Coordinate coord;
        do {
            ANSIUtils.clearScreen();
            this.printUI(player);
            System.out.println(players[player].getName() + " guess: ");
            coord = this.console.readValidCoordinate(this.grid.getRowSize(), this.grid.getColSize());
        } while (!this.console.isCoordinateInBounds(coord, this.grid) || this.grid.getCard(coord) == null);
        this.grid.getCard(coord).flip(true);
        return this.grid.getCard(coord);
    }

    /**
     * Start the game.
     * 
     * <p>
     * Game cycle:
     * <ul>
     * <li>take 2 guesses
     * <li>if the guesses are the same, the player gets 2 points and plays again
     * <li>if the guesses are different, the player gets 0 points and the next
     * player plays
     * </ul>
     * When the grid is empty the game is over
     * 
     */
    public void start() {
        int currentPlayer = -1;
        boolean lastPlayerHasGuessed = false;
        while (!this.grid.isEmpty()) {
            // PLAYERS ROTATION
            if (!lastPlayerHasGuessed) {
                do {
                    currentPlayer = (currentPlayer + 1) % this.players.length;
                } while (this.players[currentPlayer].isDead());
            }

            lastPlayerHasGuessed = false;
            this.printUI(currentPlayer);
            Card guess1 = this.takeGuess(currentPlayer);
            ANSIUtils.clearScreen();
            this.printUI(currentPlayer);

            // CASE BOMB OR JOLLY FOR FIRST GUESS
            if (guess1.isBomb()) {
                players[currentPlayer].kill();
                System.out.println("BOOM!");
                this.grid.popCard(guess1);
                this.console.readEnterToContinue();
                this.console.clearScanner();
                this.grid.flipAllCards();
                continue;
            }
            if (guess1.isJolly()) {
                players[currentPlayer].incrementScore(guess1.getPoints());
                System.out.println("JOLLY!");
                this.grid.popCard(guess1);
                this.console.readEnterToContinue();
                this.console.clearScanner();
                this.grid.flipAllCards();
                lastPlayerHasGuessed = true;
                continue;
            }

            Card guess2 = this.takeGuess(currentPlayer);
            ANSIUtils.clearScreen();
            this.printUI(currentPlayer);

            // CASE BOMB OR JOLLY FOR SECOND GUESS
            if (guess2.isBomb()) {
                players[currentPlayer].kill();
                System.out.println("BOOM!");
                this.grid.popCard(guess2);
                this.console.readEnterToContinue();
                this.console.clearScanner();
                this.grid.flipAllCards();
                continue;
            }
            if (guess2.isJolly()) {
                players[currentPlayer].incrementScore(guess2.getPoints());
                System.out.println("JOLLY!");
                this.grid.popCard(guess2);
                this.console.readEnterToContinue();
                this.console.clearScanner();
                this.grid.flipAllCards();
                lastPlayerHasGuessed = true;
                continue;
            }
            this.printUI(currentPlayer);

            // CASE MATCH OR WRONG
            if (guess1.equals(guess2)) {
                System.out.println("MATCH!");
                this.console.readEnterToContinue();
                players[currentPlayer].incrementScore(guess1.getPoints());
                this.grid.popCard(guess1);
                this.grid.popCard(guess2);
                lastPlayerHasGuessed = true;
            } else {
                System.out.println("WRONG!");
                this.console.readEnterToContinue();
            }
            // RESTORE GRID
            this.console.clearScanner();
            this.grid.flipAllCards();
        }
        ANSIUtils.clearScreen();
        ANSIUtils.setForegroundColor(ANSIUtils.BLUE);
        ANSIUtils.setBold();
        System.out.println("GAME OVER");
        ANSIUtils.reset();
        System.out.println();
        this.printLeaderboard();
    }

    /**
     * Destroy the game.
     * Closes the scanner.
     */
    public void destroy() {
        this.console.closeScanner();
    }
}
