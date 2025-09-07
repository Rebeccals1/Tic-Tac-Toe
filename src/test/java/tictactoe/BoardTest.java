package tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tictactoe.Board;
import tictactoe.Mark;

class BoardTest {

    @Test
    void emptyBoard_hasAllEmptyCells() {
        Board b = new Board();
        for (int pos = 1; pos <= 9; pos++) {
            assertEquals(Mark.EMPTY, b.getCell(pos));
        }
    }

    @Test
    void placeMove_rejectsOutOfRange() {
        Board b = new Board();
        assertThrows(IllegalArgumentException.class, () -> b.placeMove(0, Mark.X));
        assertThrows(IllegalArgumentException.class, () -> b.placeMove(10, Mark.X));
    }

    @Test
    void placeMove_rejectsOccupied() {
        Board b = new Board();
        b.placeMove(1, Mark.X);
        // Match the code: should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> b.placeMove(1, Mark.O));
    }

}
