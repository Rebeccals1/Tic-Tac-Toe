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

## Design Snapshot
**For encapsulation**, I made the board’s cells private so nothing outside can change them directly. You have to use methods like placeMove(position, mark) to make a move. Those methods check the input first: the position must be 1–9 and the spot can’t already be taken. My basic “always true” rules (invariants) are: the board always has 9 spots, and each spot is either X, O, or empty. I never return the real array to the outside—if something needs to read it, it goes through a getter.

**For inheritance and polymorphism**, I created a Player base class (or interface) with a nextMove(Board) method. Then I made HumanPlayer and ComputerPlayer that both fill in that method differently. The game loop only talks to Player, so it doesn’t care which kind it is. I put win checking in a separate helper (GameResolver) so it’s not mixed with input or printing.

**A trade-off I made:** I used inheritance for players instead of composition (like Player having a Strategy). Inheritance was simpler for now and easier for me to wire up, but if I later want to switch strategies at runtime, composition would be more flexible.



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
