package tictactoe;

import java.util.Scanner;

/** Wires players and starts the game. */
public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            Player human = new HumanPlayer(Mark.X, sc);
            Player cpu   = new ComputerPlayer(Mark.O);
            new Game(human, cpu).play();
        }

    }
}
