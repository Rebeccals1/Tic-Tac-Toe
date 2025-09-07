package tictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tictactoe.Board;
import tictactoe.Mark;

class BoardTest {

    @Test
    void toString_showsPlacedMarks() {
        Board b = new Board();
        b.placeMove(1, Mark.X);
        b.placeMove(5, Mark.O);

        String boardString = b.toString();
        // Debug print (optional): System.out.println(boardString);

        assertTrue(boardString.contains("X"), "Board string should include X");
        assertTrue(boardString.contains("O"), "Board string should include O");
    }

    @Test
    void toString_showsGridLayout() {
        Board b = new Board();
        String boardString = b.toString();

        assertTrue(boardString.contains("|"), "Expected vertical separators in board string");
        assertTrue(boardString.contains("---+---+---"), "Expected horizontal separators in board string");
    }

}
