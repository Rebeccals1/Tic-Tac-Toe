# Tic-Tac-Toe (Java, Console)

A clean, beginner-friendly Tic-Tac-Toe you can play in the terminal.
Human vs Computer with a readable board, safe move validation, and a simple AI (win → block → center → corner → random).

---


## Quick Run
```
cd src
javac tictactoe/*.java
java tictactoe.Main
```


## Features
- Human (X) vs Computer (O)
- Clear, easy-to-read 3×3 board (blank squares for empty cells)
- Validates inputs (1–9 only, no overwriting)
- Game resolver detects wins (rows/cols/diagonals) and draws Simple but decent computer strategy



## Project Structure
```
src/
└─ tictactoe/
   ├─ Mark.java            # enum { X, O, EMPTY }
   ├─ Board.java           # 3×3 grid, safe API (placeMove, legalMoves, toString)
   ├─ GameResolver.java    # rules: IN_PROGRESS, DRAW, X_WINS, O_WINS
   ├─ Player.java          # abstract: mark(), nextMove(Board)
   ├─ HumanPlayer.java     # asks user; validates input
   ├─ ComputerPlayer.java  # win → block → center → corner → random
   ├─ Game.java            # runs the match, prints board each turn
   └─ Main.java            # wiring (creates players, starts Game)
```

## How to Play

- Run the commands above.
- Each turn you’ll see the current board and a list of Open cells like [1, 2, 4, 7].
- Type a number from that list and press Enter.
- The computer replies; repeat until someone wins or it’s a draw.


## Design Snapshot (beginner-friendly)
- **Encapsulation:** The board’s 3×3 grid lives in a private array inside Board. Only placeMove(position, mark) can change it.
- **Board placeMove method checks:**
  - Is position 1–9?
  - Is the target cell empty?
  - Is the mark X or O (not EMPTY)?
  - Thanks to these checks, the board always stays valid.

- **Read-only helpers:** You can look at marks with getCell() and see legal moves with legalMoves() (safe list).
- **Players (inheritance + polymorphism):** Player is the generic type with mark() and nextMove(Board).
  - HumanPlayer asks you for input.
  - ComputerPlayer uses a small strategy.
  - The Game loop treats both the same—just calls nextMove()—no special cases.

- **Trade-off:** We picked a simple Player hierarchy (easy to understand) instead of a pluggable “strategy” object. If we add many AI types later, we can switch to strategies without changing the board.


## Code Excerpts
Pretty Board Printing (Board#toString)
```
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int r = 0; r < 3; r++) {
        int base = r * 3;
        sb.append(" ")
          .append(cells[base]).append(" │ ")
          .append(cells[base + 1]).append(" │ ")
          .append(cells[base + 2]).append("\n");
        if (r < 2) sb.append("───┼───┼───\n");
    }
    return sb.toString();
}

```
Why it helps: You get a clean, readable grid with blank squares for empty cells—great for gameplay and debugging.


## One JUnit Test You’ll Be Glad You Have
```

@Test
void preventsOverwrite_andDetectsDiagonalWin() {
    Board b = new Board();
    b.placeMove(1, Mark.X);
    b.placeMove(2, Mark.O);
    b.placeMove(5, Mark.X);

    assertThrows(IllegalArgumentException.class,
        () -> b.placeMove(5, Mark.O)); // cannot overwrite

    b.placeMove(3, Mark.O);
    b.placeMove(9, Mark.X); // X completes main diagonal

    assertEquals(GameResolver.GameState.X_WINS, GameResolver.resolve(b));
}

```
What it catches: illegal overwrites and missed diagonal wins.


## UML / Diagram (ASCII)
```

 ┌─────────────┐
 │    Game     │ → runs the loop, prints board
 └─────┬───────┘
       │ uses
       ▼
 ┌─────────────┐
 │   Board     │ → private 3×3 grid; placeMove, legalMoves
 └─────┬───────┘
       │ checked by
       ▼
 ┌─────────────┐
 │ GameResolver│ → X wins, O wins, draw, or in progress
 └─────────────┘

 ┌─────────────┐
 │   Player    │ (abstract)
 └─────┬───────┘
   ┌───┴───────────┐
   │               │
┌───────┐     ┌───────────────┐
│ Human │     │   Computer    │
│ asks  │     │ win/block/... │
└───────┘     └───────────────┘

```


## Future Ideas

- Multiple AI strategies (minimax, heuristics)
- Two-human mode
- GUI (Swing/JavaFX)

## License
MIT — Enjoy, use, and share.
