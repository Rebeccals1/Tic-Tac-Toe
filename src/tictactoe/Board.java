package tictactoe;

import java.util.Random;

public class Board {
    // 0 means empty
    // 1 means X
    // 2 means O
    private int[][] board = new int[3][3];

    public void instructionBoard() {
        System.out.println("| - | - | - |");
        System.out.println("| 1 | 2 | 3 |");
        System.out.println("| - | - | - |");
        System.out.println("| 4 | 5 | 6 |");
        System.out.println("| - | - | - |");
        System.out.println("| 7 | 8 | 9 |");
        System.out.println("| - | - | - |");
    }

    public void displayBoard() {
        System.out.println("| - | - | - |");
        System.out.println(printRow(0));
        System.out.println("| - | - | - |");
        System.out.println(printRow(1));
        System.out.println("| - | - | - |");
        System.out.println(printRow(2));
        System.out.println("| - | - | - |");
    }

    private String printRow(int row) {
        StringBuilder rowToDisplay = new StringBuilder("| ");
        for (int i = 0; i < 3; i++) {
            if (board[row][i] == 0) rowToDisplay.append(" ");
            if (board[row][i] == 1) rowToDisplay.append("X");
            if (board[row][i] == 2) rowToDisplay.append("O");
            rowToDisplay.append(" | ");
        }
        return rowToDisplay.toString();
    }

    public boolean placePiece(int position, String pieceType) {
        // row and col based on position
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        // update board
        if(board[row][col] == 0){
            if(pieceType.equals("X") ) board[row][col] = 1;
            if(pieceType.equals("O") ) board[row][col] = 2;
            return true;
        }
        return false;
    }

    public boolean placePieceRandomly(String pieceType) {
        boolean wasPiecePlaced = false;
        Random rand = new Random();
        int row, col;

        while (!wasPiecePlaced && !isMatrixFull()) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            if (board[row][col] == 0) {
                board[row][col] = pieceType.equals("X") ? 1 : 2;
                wasPiecePlaced = true;
            }
        }
        return wasPiecePlaced;
    }

    public boolean isMatrixFull(){
        for(int row = 0; row < 3; row++){
            for(int col=0; col < 3; col++){
                if (board[row][col] == 0 ) return false;
            }
        }
        return true;
    }

    public int[][] getBoard() {
        return board;
    }
}
