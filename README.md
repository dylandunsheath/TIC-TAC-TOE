# Tic-Tac-Toe (Final Draft Version)

**Authors:**  
- Dylan Dunsheath (GUI)  
- Theodore Russell (CPU Mode, Pause Menu, Logic, Title Screen*)  
- Joan Estepan (2 Player Mode)  
- David Contey (Other Logic)  
- Jose Pena (Title Screen*)  

\* The title screen was co-developed by Jose and Theodore.

---

## 📖 Overview

This project is a fully functioning **Tic-Tac-Toe game** with a graphical user interface (GUI).  
It includes:

- A working **title screen**  
- **CPU Mode** and **2 Player Mode**  
- **Pause screen** while in game  
- **Win checking and board reset** (with option to replay or return to title)  

The game can be played infinitely until the user decides to exit.

---

## 🎮 Gameplay Features

- **Players**  
  - X is controlled by the human player  
  - O is controlled by either the CPU or Player 2 (depending on chosen mode)  
- **Turn Order**: The game randomly decides who starts first.  
- **Moves**: Players click on an empty square to place their mark.  
- **Game End**:  
  - Win → prompts to play again  
  - Replay resets the board  
  - Quit returns to the title screen  
- **Exit Options**:  
  - From the title screen using the **Exit button**  
  - By closing the window (the “X” in the corner)  

---

## ⏸️ Pause Menu

- Accessed with the **Esc key** (only during gameplay).  
- Press **Esc** again to resume.  
- **CPU Mode Only**: Includes a difficulty slider.  

---

## 🤖 CPU Logic

The CPU uses a decision-making algorithm with adjustable difficulty:

- **Winning Moves**: If two of its symbols are in a line, it will complete the win.  
- **Blocking Moves**: If the opponent has two in a line, it will block.  
- **Random Moves**: Otherwise, it places in a random spot.  

Difficulty is controlled by the `CPU Level` (0–10):

- **0** → CPU only makes random moves.  
- **10** → CPU always makes optimal moves.  
- Intermediate levels represent percentages (e.g., Level 3 = 30% chance of making a calculated move).  

The CPU difficulty is saved in `CPU Level.dat` and persists between sessions.

---

## ⚙️ Installation & Running

### Option 1: Executable Version
1. Extract all files from the `.zip` archive.  
2. Run `Tic-Tac-Toe.jar`.  
3. A `CPU Level.dat` file will be created alongside the JAR.  

### Option 2: Source Code
1. Extract all files.  
2. Compile the code using your own Java compiler.  
   - Main file: `TicTacToeMain.java`  
3. Run the program.  
4. A `CPU Level.dat` file will be created in the source directory.  

---

## 📝 Changelog

### Final Draft Version (4/17/21)
- Implemented `TicTacToeTitleScreen`
- Implemented `TicTacToeOtherLogic`
- Removed stub code
- Added executable `.jar` version
- Code/comment cleanup

### Presentation Draft Version (4/28/21)
- Cross-platform `LookAndFeel`
- Fixed MacOS button issues
- Adjusted font size for compatibility
- Added unified error pop-up system
- Bug fixes (e.g., CPU Level file errors)

### 2 Player Implementation (4/11/21)
- Integrated Joan’s 2 Player Mode
- Minor code/comment cleanup

### Stub Draft Version (4/6/21)
- Created stubs for remaining parts
- Added pause screen & CardLayout for panel switching
- CPU Level adjusted (0–10 range)
- Bug fixes (e.g., CPU turn skip)

### First Draft Version (3/29/21)
- Added CPU timer
- Implemented CPU Level file input/output
- Improved clarity of code
- Bug fixes

### Integration Version (3/24/21)
- Integrated demo into Dylan’s GUI
- General logic file created
- Action listener refactored
- GUI cleanup and improvements

### Demo Version (3/1/21)
- CPU Mode demo with move calculation
- Temporary GUI

### GUI Version (2/21/21)
- Initial GUI created
- Player turn display
- Board buttons for interaction

---

## ⚠️ Notes

- Always extract the `.zip` before running. Running directly inside the archive may cause issues.  
- Editing `CPU Level.dat` manually only takes effect after restarting the program.  

---
