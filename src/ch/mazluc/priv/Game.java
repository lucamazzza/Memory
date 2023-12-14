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
        System.out.println("GAME SETTINGS: ");
        // INIT PLAYER LIST
        int playerCount = this.console.readIntInRange(2, 6);
        this.console.clearScanner();
        this.players = new Player[playerCount];
        // INIT PLAYER NAMES
        for (int i = 0; i < this.players.length; i++) {
            String name = this.console.readNonBlankOrEmptyString("Insert player #" + (i + 1) + " name");
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
        System.out
                .println(players[currentPlayer].getName() + "'s turn (" + players[currentPlayer].getScore() + ")");
        this.grid.print();
    }

    private void printLeaderboard() {
        System.out.println("LEADERBOARD: ");
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName() + " - " + players[i].getScore());
        }
        this.console.readEnterToContinue();
    }

    public Card takeGuess() {
        Coordinate coord;
        do {
            coord = this.console.readValidCoordinate(this.grid.getRowSize(), this.grid.getColSize());
        } while (!this.console.isCoordinateInBounds(coord, grid) || this.grid.getCard(coord) == null);
        this.grid.getCard(coord).flip(true);
        return this.grid.getCard(coord);
    }

    public void start() throws InterruptedException {
        int currentPlayer = 0;
        while (!this.grid.isEmpty()) {
            this.printUI(currentPlayer);
            Card guess1 = this.takeGuess();
            ANSIUtils.clearScreen();
            this.printUI(currentPlayer);
            Card guess2 = this.takeGuess();
            ANSIUtils.clearScreen();
            this.printUI(currentPlayer);
            if (guess1.equals(guess2)) {
                System.out.println("MATCH!");
                Thread.sleep(2000);
                players[currentPlayer].incrementScore(guess1.getPoints());
                this.grid.popCard(guess1);
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

    public void destroy() {
        this.console.closeScanner();
    }

    // + startScreen() -> void
    // + endScreen() -> void
    // + takeGuesses(coord1: Coordinate, coord2: Coordinate) -> void
    // + nextTurn() -> void
    // + start() -> void
}
