package tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 3x3 Tic-Tac-Toe board with simple, safe operations.
 * Uses 1–9 positions for the public API (left-to-right, top-to-bottom).
 */
public class Board {
    private final Mark[] cells = new Mark[9];

    public Board() {
        for (int i = 0; i < 9; i++) cells[i] = Mark.EMPTY;
    }

    /** Copy constructor for simulation (e.g., lookahead). */
    public Board(Board other) {
        System.arraycopy(other.cells, 0, this.cells, 0, 9);
    }

    /** Place a move at 1–9. Throws if position invalid or occupied. */
    public void placeMove(int position, Mark mark) {
        validatePosition(position);
        if (mark == Mark.EMPTY) throw new IllegalArgumentException("Cannot place EMPTY.");
        int idx = position - 1;
        if (cells[idx] != Mark.EMPTY) throw new IllegalArgumentException("Cell already taken.");
        cells[idx] = mark;
    }

    /** Mark at 1–9. */
    public Mark getCell(int position) {
        validatePosition(position);
        return cells[position - 1];
    }

    public boolean isFull() {
        for (Mark m : cells) if (m == Mark.EMPTY) return false;
        return true;
    }

    /** Immutable list of legal 1–9 positions. */
    public List<Integer> legalMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) if (cells[i] == Mark.EMPTY) moves.add(i + 1);
        return Collections.unmodifiableList(moves);
    }

    /** Defensive copy for tests/AI. */
    public Mark[] snapshot() { return cells.clone(); }

    private void validatePosition(int position) {
        if (position < 1 || position > 9)
            throw new IllegalArgumentException("Position must be 1–9.");
    }

    /** Pretty print: blank squares for empties; clear separators. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 3; r++) {
            int base = r * 3;
            sb.append(" ")
                    .append(cells[base].toString()).append(" │ ")
                    .append(cells[base + 1].toString()).append(" │ ")
                    .append(cells[base + 2].toString()).append("\n");
            if (r < 2) sb.append("───┼───┼───\n");
        }
        return sb.toString();
    }
}
