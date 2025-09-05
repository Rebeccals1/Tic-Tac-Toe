package tictactoe;

/** Pure game-state logic (no I/O, no mutation). */
public class GameResolver {

    public enum GameState { X_WINS, O_WINS, DRAW, IN_PROGRESS }

    private static final int[][] LINES = {
            {1,2,3},{4,5,6},{7,8,9},  // rows
            {1,4,7},{2,5,8},{3,6,9},  // cols
            {1,5,9},{3,5,7}           // diagonals
    };

    public static GameState resolve(Board board) {
        for (int[] line : LINES) {
            Mark a = board.getCell(line[0]);
            if (a == Mark.EMPTY) continue;
            if (a == board.getCell(line[1]) && a == board.getCell(line[2])) {
                return (a == Mark.X) ? GameState.X_WINS : GameState.O_WINS;
            }
        }
        return board.isFull() ? GameState.DRAW : GameState.IN_PROGRESS;
    }

    /** True if placing 'who' at position wins immediately. */
    public static boolean isWinningMove(Board b, int position, Mark who) {
        if (b.getCell(position) != Mark.EMPTY) return false;
        Board tmp = new Board(b);
        tmp.placeMove(position, who);
        GameState s = resolve(tmp);
        return (who == Mark.X && s == GameState.X_WINS) || (who == Mark.O && s == GameState.O_WINS);
    }
}
