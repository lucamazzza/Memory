package ch.mazluc.priv;

public class MemoryTest {
    public static void main(String[] args) {
        // INIT GAME
        Game game = new Game();
        game.initialize();
        try {
            game.start();
        } catch (InterruptedException e) {
            System.out.println("Game interrupted");
        }
        game.destroy();
    }
}
