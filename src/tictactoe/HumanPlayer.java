package tictactoe;

import java.util.Scanner;

/** Robust, readable input loop with clear prompts. */
public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(Mark mark, Scanner scanner) {
        super(mark);
        this.scanner = scanner;
    }

    @Override
    public int nextMove(Board board) {
        while (true) {
            System.out.print("Enter a position (1–9) for " + mark() + "  ");
            System.out.println("Open cells: " + board.legalMoves());
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            try {
                int pos = Integer.parseInt(line);
                if (!board.legalMoves().contains(pos)) {
                    System.out.println("That cell is not available. Try again.\n");
                    continue;
                }
                return pos;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from the open cells list.\n");
            }
        }
    }
}
