package ch.mazluc.game;

public class MemoryTest {
    public static void main(String[] args) {
        Game game = new Game();
        game.printStartScreen();
        game.initialize();
        try {
            game.start();
        } catch (InterruptedException e) {
            System.out.println("Game interrupted");
        }
        game.destroy();
    }
}
