# 🎮 Tic-Tac-Toe  

A simple **console-based Tic-Tac-Toe game** written in **Java**. Play against a friend or let the computer make moves for you! This project demonstrates clean object-oriented design with classes for the game board, resolver logic, and main gameplay loop.  

---

## ✨ Features  
- Two-player Tic-Tac-Toe (Player vs Computer supported).  
- Winner detection across rows, columns, and diagonals.  
- Automatic draw detection when the board is full.  
- Console input for interactive gameplay.  
- Random move placement for the computer opponent.  



## 📂 Project Structure 
```

src/
├── tictactoe/
│ ├── Board.java # Represents the game board
│ ├── GameResolver.java # Checks win/draw conditions
│ └── Main.java # Runs the game loop

```

## 🚀 Getting Started  

### Prerequisites  
- Java 8 or later installed on your machine.  
- A terminal or IDE (IntelliJ, Eclipse, VS Code, etc.)  

### Compile & Run  

```
# Navigate to src directory
cd src

## Compile
javac tictactoe/*.java

## Run
java tictactoe.Main

```
## How to Play

1. Run the game from the terminal.
2. Enter the position (1–9) where you’d like to place your X.

```

1 | 2 | 3
---------
4 | 5 | 6
---------
7 | 8 | 9

```

3. The computer (O) will automatically place its piece.
4. Play alternates until someone wins or the board is full.


## License
This project is licensed under the MIT License – feel free to use, modify, and share.

