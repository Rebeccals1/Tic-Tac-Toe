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
# 1) Clone
git clone https://github.com/Rebeccals1/Tic-Tac-Toe.git
cd Tic-Tac-Toe

# 2) Compile (from the src folder, since classes are in package tictactoe)
cd src
javac tictactoe/*.java

# 3) Run
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

+-------------------------------------+
| Board                               |
|-------------------------------------|
| - cells : Mark[9]                   |
|-------------------------------------|
| + Board()                           |
| + Board(other: Board)               |
| + placeMove(pos:int, mark:Mark):void|
| + getCell(pos:int): Mark            |
| + isFull(): boolean                 |
| + legalMoves(): List<Integer>       |
| + snapshot(): Mark[]                |
| + toString(): String                |
+-------------------------------------+
         (stores 9 Marks)

+-----------------------------------+
| «abstract» Player                 |
|-----------------------------------|
| - mark : Mark                     |
|-----------------------------------|
| + Player(mark:Mark)               |
| + getMark(): Mark                 |
| {abstract} + nextMove(b:Board):int|
+-----------------^-----------------+
                  |
        +---------+---------+
        |                   |
+--------------------+  +--------------------+
| HumanPlayer        |  | ComputerPlayer     |
|--------------------|  |--------------------|
| + nextMove(...):int|  | + nextMove(...):int|
+--------------------+  +--------------------+

+--------------------------------------+
| «enum» GameState                     |
|--------------------------------------|
| IN_PROGRESS | DRAW | X_WINS | O_WINS |
+--------------------------------------+

+-----------------------------------+
| GameResolver                      |
|-----------------------------------|
| + resolve(board:Board): GameState |
+-----------------------------------+

+-----------------------------------+
| Game                              |
|-----------------------------------|
| - board : Board                   |
| - x : Player                      |
| - o : Player                      |
|-----------------------------------|
| + Game(x:Player, o:Player,        |
|        board:Board)               |
| + run(): void                     |
+-----------------------------------+
(uses Board; calls GameResolver; depends on Player)


```

## License
MIT — Enjoy, use, and share.
