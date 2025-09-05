package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Simple, readable strategy: win → block → center → corner → random. */
public class ComputerPlayer extends Player {
    private final Random rng = new Random();

    public ComputerPlayer(Mark mark) { super(mark); }

    @Override
    public int nextMove(Board board) {
        Mark me  = mark();
        Mark opp = (me == Mark.X) ? Mark.O : Mark.X;

        // 1) Win if possible
        for (int pos : board.legalMoves())
            if (GameResolver.isWinningMove(board, pos, me)) return pos;

        // 2) Block opponent
        for (int pos : board.legalMoves())
            if (GameResolver.isWinningMove(board, pos, opp)) return pos;

        // 3) Take center
        if (board.legalMoves().contains(5)) return 5;

        // 4) Take a corner
        int[] corners = {1, 3, 7, 9};
        for (int c : corners) if (board.legalMoves().contains(c)) return c;

        // 5) Otherwise, random
        List<Integer> moves = new ArrayList<>(board.legalMoves());
        return moves.get(rng.nextInt(moves.size()));
    }
}
