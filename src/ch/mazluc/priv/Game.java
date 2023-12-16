package ch.mazluc.priv;

/**
 * <h1>
 * Game
 * </h1>
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
    private ConsoleInteractionUtils console;

    /**
     * Constructor.
     * Initializes the console interaction util.
     */
    public Game() {
        this.console = new ConsoleInteractionUtils();
    }

    /**
     * Initializes the game.
     * Reads the game settings from the user.
     * Initializes the players and the grid.
     * Fills the grid with cards.
     */
    public void initialize() {
        ANSIUtils.setBold();
        System.out.println("GAME SETTINGS: ");
        ANSIUtils.reset();
        // INIT PLAYER LIST
        int playerCount = this.console.readIntInRange(Constant.MIN_PLAYERS, Constant.MAX_PLAYERS);
        this.console.clearScanner();
        this.players = new Player[playerCount];
        // INIT PLAYER NAMES
        for (int i = 0; i < this.players.length; i++) {
            String name = this.console.readStringWithMinMaxLength("Insert player #" + (i + 1) + " name",
                    Constant.MIN_PLAYER_NAME_LENGTH, Constant.MAX_PLAYER_NAME_LENGTH);
            this.players[i] = new Player(name);
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
        ANSIUtils.setBold();
        System.out.println("LEADERBOARD: ");
        ANSIUtils.reset();
        for (int i = 0; i < players.length; i++) {
            ANSIUtils.setBackgroundColor(players[i].getColor());
            System.out.printf("%-15s %2d\n", players[i].getName(), players[i].getScore());
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
     * @throws InterruptedException when the game is interrupted
     */
    public void start() throws InterruptedException {
        int currentPlayer = 0;
        while (!this.grid.isEmpty()) {
            this.printUI(currentPlayer);
            Card guess1 = this.takeGuess(currentPlayer);
            ANSIUtils.clearScreen();
            this.printUI(currentPlayer);
            Card guess2 = this.takeGuess(currentPlayer);
            ANSIUtils.clearScreen();
            this.printUI(currentPlayer);
            if (guess1.equals(guess2)) {
                System.out.println("MATCH!");
                Thread.sleep(2000);
                players[currentPlayer].incrementScore(guess1.getPoints());
                this.grid.popCard(guess1);
                this.grid.popCard(guess2);
            } else {
                System.out.println("WRONG!");
                Thread.sleep(2000);
                currentPlayer = (currentPlayer + 1) % this.players.length;
            }
            this.console.clearScanner();
            this.grid.flipAllCards();
        }
        ANSIUtils.clearScreen();
        System.out.println("GAME OVER");
        this.printLeaderboard();
    }

    /**
     * Destroy the game.
     * Closes the scanner.
     */
    public void destroy() {
        this.console.closeScanner();
    }

    // + startScreen() -> void
    // + endScreen() -> void
}
