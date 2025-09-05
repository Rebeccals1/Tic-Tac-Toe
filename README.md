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
- Each turn you’ll see the current board and a list of Open cells like [1, 2, 4, 7]
- Enter the number (1–9) of the cell where you want to place your mark
```
1 | 2 | 3
---------
4 | 5 | 6
---------
7 | 8 | 9
```
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
- **Encapsulation:** The board’s 3×3 grid is private inside Board. Moves are only applied through placeMove(), which checks for valid range, emptiness, and correct mark.
- **Inheritance/Polymorphism:** Player is abstract and defines the shared API (nextMove(Board), mark()). HumanPlayer and ComputerPlayer implement it differently, but Game treats them the same.
- **Trade-off:** Inheritance makes the design simple for now. If more AI types are added later, the design could switch to strategies (composition) for more flexibility.


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
