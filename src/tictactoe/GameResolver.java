package tictactoe;

public class GameResolver {

    public enum GameState { IN_PROGRESS, X_WON, O_WON, DRAW }

    public static GameState resolve(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            throw new IllegalArgumentException("Board must be non-empty");
        }

        int rows = board.length;
        int cols = board[0].length;

        // Check every cell as a potential starting point in four directions:
        // right (0,1), down (1,0), down-right (1,1), down-left (1,-1).
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int piece = board[r][c];
                if (piece == 0) continue;

                if (lineOf3(board, r, c, 0, 1)   // →
                        || lineOf3(board, r, c, 1, 0)   // ↓
                        || lineOf3(board, r, c, 1, 1)   // ↘
                        || lineOf3(board, r, c, 1, -1)) // ↙
                {
                    return piece == 1 ? GameState.X_WON : GameState.O_WON;
                }
            }
        }

        // No winner found; decide between DRAW and IN_PROGRESS
        return isBoardFull(board) ? GameState.DRAW : GameState.IN_PROGRESS;
    }

    private static boolean lineOf3(int[][] b, int r, int c, int dr, int dc) {
        int rows = b.length;
        int cols = b[0].length;
        int piece = b[r][c];

        // Ensure the next two steps stay within bounds.
        int r2 = r + 2 * dr;
        int c2 = c + 2 * dc;
        if (r2 < 0 || r2 >= rows || c2 < 0 || c2 >= cols) return false;

        int r1 = r + dr;
        int c1 = c + dc;

        return b[r1][c1] == piece && b[r2][c2] == piece;
    }

    private static boolean isBoardFull(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    public static String outcomeText(int[][] board) {
        GameState state = resolve(board);
        switch (state) {
            case X_WON: return "X wins!";
            case O_WON: return "O wins!";
            case DRAW:  return "It's a draw.";
            default:    return "Game in progress.";
        }
    }

}
