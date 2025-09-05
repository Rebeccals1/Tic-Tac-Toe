import tictactoe.Board;
import tictactoe.GameResolver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Board board = new Board();
        board.instructionBoard();
        System.out.println("Please enter position: ");
        int position = scanner.nextInt();

        board.placePiece(position, "X");

        boolean isGameInProgress = true;
        while(isGameInProgress && !board.isMatrixFull()){
            board.placePieceRandomly("O");
            board.displayBoard();

            isGameInProgress = GameResolver.resolve(board.getBoard()) == GameResolver.GameState.IN_PROGRESS;

            if(!isGameInProgress){
                System.out.println("GAME OVER!");
                System.out.println(GameResolver.outcomeText(board.getBoard()));
                break;
            }else{
                System.out.println("Please enter another position: ");
                position = scanner.nextInt();
                board.placePiece(position, "X");
            }
        }

    }
}
