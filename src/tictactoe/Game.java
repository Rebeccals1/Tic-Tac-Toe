package tictactoe;

/** Runs a single match, handling turn order and display. */
public class Game {
    private final Board board = new Board();
    private final Player pX;
    private final Player pO;

    public Game(Player x, Player o) {
        this.pX = x;
        this.pO = o;
    }

    public void play() {
        GameResolver.GameState state = GameResolver.GameState.IN_PROGRESS;
        Player current = pX;

        System.out.println("\nTic-Tac-Toe\n");
        printBoard();

        while (state == GameResolver.GameState.IN_PROGRESS) {
            int pos = current.nextMove(board);
            try {
                board.placeMove(pos, current.mark());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid move: " + ex.getMessage());
                continue; // retry same player
            }

            System.out.println();
            printBoard();

            state = GameResolver.resolve(board);
            current = (current == pX) ? pO : pX;
        }

        System.out.println();
        switch (state) {
            case X_WINS -> System.out.println("X wins! 🎉");
            case O_WINS -> System.out.println("O wins! 🎉");
            case DRAW   -> System.out.println("It's a draw.");
        }
    }

    private void printBoard() {
        // Extra spacing for clarity in the console
        System.out.println(board);
    }
}
