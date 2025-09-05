# Tic-Tac-Toe (Java, Console)

A clean, beginner-friendly Tic-Tac-Toe you can play in the terminal.
Human vs Computer with a readable board, safe move validation, and a simple AI (win → block → center → corner → random).

---

## Features
- Human (X) vs Computer (O)
- Clear, easy-to-read 3×3 board (blank squares for empty cells)
- Validates inputs (1–9 only, no overwriting)
- Game resolver detects wins (rows/cols/diagonals) and draws Simple but decent computer strategy


## Quick Run
```
cd src
javac tictactoe/*.java
java tictactoe.Main
```

## How to Play

- Run the commands above.
- Each turn you’ll see the current board and a list of Open cells like [1, 2, 4, 7].
- Type a number from that list and press Enter.
- The computer replies; repeat until someone wins or it’s a draw.


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


## UML / Diagram (ASCII)

```
+-----------------------------------+
| «enum» Mark                       |
|-----------------------------------|
| X | O | EMPTY                     |
+-----------------------------------+

+-----------------------------------+
| Board                             |
|-----------------------------------|
| - cells : Mark[9]                 |
|-----------------------------------|
| + Board()                         |
| + Board(other: Board)             |
| + placeMove(pos:int, mark:Mark)   |
| + getCell(pos:int): Mark          |
| + isFull(): boolean               |
| + legalMoves(): List<int>         |
| + snapshot(): Mark[]              |
| + toString(): String              |
+-----------------------------------+
               ^
               | uses
               |
+-----------------------------------+
| «abstract» Player                 |
|-----------------------------------|
| - mark : Mark                     |
|-----------------------------------|
| + Player(mark:Mark)               |
| + mark(): Mark                    |
| # nextMove(board:Board): int      |
+-----------------+-----------------+
                  | inherits
     +------------+------------+
     |                         |
+---------------------+  +----------------------+
| HumanPlayer         |  | ComputerPlayer       |
|---------------------|  |----------------------|
| + nextMove(...):int |  | + nextMove(...): int |
+---------------------+  +----------------------+

+-----------------------------------+
| GameResolver                      |
|-----------------------------------|
| enum GameState:                   |
|  X_WINS, O_WINS, DRAW, IN_PROGRESS|
|-----------------------------------|
| + resolve(board:Board): GameState |
| + isWinningMove(b:Board,          |
|   pos:int, who:Mark): boolean     |
+-----------------------------------+

+-----------------------------------+
| Game                              |
|-----------------------------------|
| - board : Board                   |
| - pX : Player                     |
| - pO : Player                     |
|-----------------------------------|
| + Game(x:Player, o:Player)        |
| + play(): void                    |
| - printBoard(): void              |
+-----------------------------------+
| uses Board, has Players,          |
| checks state via GameResolver     |
+-----------------------------------+

```

## License
MIT — Enjoy, use, and share.
