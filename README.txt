Tic-Tac-Toe (Final Draft Version)
Made by Dylan Dunsheath, Theodore Russell, David Contey, Joan Estepan, and Jose Pena

This program is fully completed and working as intended. The game has a fully functioning GUI with a working title screen, a CPU Mode and 2 Player Mode, a pause screen while in game, and a way to check for a win and reset the board after or return to the title screen.

The game is basic Tic-Tac-Toe, with the X player being controlled by the human player and the O player being controlled by either a computer player or a second player depending on the mode that is chosen on the title screen. The game will start off on a random player's turn, and then the game will proceed with letting each player have their turn. The human players just have to click on an empty spot when it is their turn, and if the game ends, they will be prompted to play again (clicking yes will reset the board, clicking no will return to the title screen). The game can be played as many times as the player wants. The game can be exited using either the "Exit" button on the title screen or the "x" at the top right corner of the window. The game also features a pause function, which can be used by pressing the escape key ("Esc" on the top left of the keyboard). To unpause the game, press the escape key once again and the game will resume. However, the pause menu is only available on the game screen, so pressing escape on the title screen will not pause the game (since there is no game currently going on). If the game is in CPU Mode, pausing will allow the player to change the difficulty of the CPU using a slider (0 means that the CPU will only make random moves, 10 means that the CPU will always make a calculated move if there is one to be made). This value will be saved into a file named "CPU Level.dat", and that value will be loaded when the program is run in the future as long as a valid number is in the file (Otherwise the value will be reset to 3).

The CPU has an algorithm that decides which move it makes based on the state of the board. If it finds a row, column, or diagonal with two of it's own letters, it will fill the last spot with it's letter to win the game. If it does not find two of it's own letters in a row, column, or diagonal, it will then check the human player's letter. If it finds two of the other player's letters in a row, column, or diagonal, it will then put it's own letter in that row, column, or diagonal to block them from winning. These two choices are both randomized, so based on the CPU Level, it will have a greater or lesser chance of making these calculated moves (0 means that the CPU will only make random moves, 10 means the CPU will always make a calculated move if there is one to be made). For example, if the CPU is on level 1, then it will have a 10% or 1/10 chance to make a calculated move. The formula for the CPU random chance to make a move is essentially (10 * cpuLevel)%, so it goes from 0% to 100%, with each value in between being a multiple of 10. If it does not find two of the same letters in any row, column, or diagonal, then it will make a random move on one of the remaining spots on the board. This program uses a slider on the pause screen to change the CPU Level, so the value can be changed there if the player desires. The value can also be changed by editing the number in the "CPU Level.dat" file, but the value will not change unless the game is restarted (since it only reads from the file once), so the slider tends to be the simpler and better option for changing the level.

Most of the errors that are possible or have been discovered have been fixed, but there may be errors due to operating system, display size, screen resolution, or so on. For the best results, make sure that your screen resolution is 1920x1080 and your operating system is preferrably Windows 10 (although this should work on Mac OS and hopefully Linux, but Linux has not been tested).

Please extract all the files from the .zip file before running the program (as there is no guarantee it will work properly otherwise).

If running the "Executable" version, simply run Tic-Tac-Toe.jar and the game will start shortly (note that a CPU Level.dat file will be created in the same location as the Tic-Tac-Toe.jar file).

If running the "Source" version, compile the code with your own compiler and run it from there. Note that you must compile the file with "TicTacToeMain.java" being the main file, as it is the one which has a main method. The CPU Level.dat file will be created wherever the project/source files are located.

Each part of the project was made by the following team members below:
Dylan Dunsheath (GUI)
Theodore Russell (CPU Mode, Pause Menu, Logic, Title Screen*)
Joan Estepan (2 Player Mode)
David Contey (Other Logic)
Jose Pena (Title Screen*)

*The title screen was made by both Jose and Theodore, so that is why they are both credited for it.

Changelog:

Presentation Draft Version (4/28/21):
- Set the LookAndFeel of the program to a cross platform one (since there were issues with coloring buttons on Mac OS)
- Made the "Exit" buttons opaque (since they also had additional issues with colors on Mac OS)
- Changed the text size of the title buttons so the 2 player text can fit on other computers/displays (lowered the font size from 50 to 42)
- Added a function in TicTacToeGUI to create an error pop-up if an error occurs instead of using JOptionPanes every time (makes the program look cleaner)
- Some minor cleanup of code and comments
- Some bugfixing (such as the program crashing when the CPU Level.dat file has too few characters)

Final Draft Version (4/17/21):
- Implemented TicTacToeTitleScreen
- Implemented TicTacToeOtherLogic
- Removed any remaining stubs from the stub draft version
- Included an executable version in the form of a .jar file
- Some minor cleanup of code and comments

2 Player Implementation Version (4/11/21):
- Implemented Joan's 2 Player Mode into the program
- Some minor cleanup of code and comments

Stub Draft Version (4/6/21):
- Stubs for the remaining parts yet to be implemented
- Java files for the remaining parts of the program
- A pause screen and pause functionality
- A CardLayout to use with a main panel that allows us to switch from one panel to another easily
- Restructuring of some of the code due to the previously mentioned CardLayout change
- Some changes to the way that CPU Level works (now has a range from 0 to 10)
- Removed some of the temporary values used for the game, since we can now start on the title screen
- Adding a few new functions (these functions aren't necessarily new, but they helped to clean up the code instead of writing the same lines multiple times)
- Cleanup of some of the code and comments
- Some bug fixes (such as a bug that allowed you to take the CPU's turn while it was "waiting")

First Draft Version (3/29/21):
- Added a timer to the CPU
- Added file input and output to change the CPU level
- Cleaned up some of the code for the sake of clarity and readability
- Some bugfixes

Integration Version (3/24/21):
- Integrated the demo program into the GUI created by Dylan
- Created a general logic file for logic functions
- Moved the action listener from the GUI class to the CPU class (to have multiple action listeners later)
- Cleaned up the GUI a bit and added some extra functionality to it

Demo Version (3/1/21):
- Created a CPU Mode demo with a complete CPU algorithm to calculate moves
- Has a different GUI until it will be implemented into Dylan's GUI Draft later

GUI Version (2/21/21):
- Created a GUI
- Created a label at the top to display which player's turn it is
- Created buttons to interact with the board in the future