//Made by Theodore Russell
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Timer;

public class TicTacToeCPUMode implements ActionListener {

	TicTacToeGUI tttGUI; //Object for the TicTacToeGUI class
	TicTacToeLogic tttLogic; //Object for the Logic class
	TicTacToePauseScreen tttPause; //Object for the Pause Screen class
	TicTacToeOtherLogic tttOLogic; //Object for the Other Logic class
	Random rand = new Random(); //Object for the Random class
	int winRow = -1; //The integer that corresponds to the row where there is a near win
	int winCol = -1; //The integer that corresponds to the column where there is a near win
	String winDia = ""; //The String that corresponds to the diagonal where there is a near win
	int cpuLevel = 3; //Integer that determines how often the CPU player will make a calculated move or not
	int timerCount = 0; //Counter variable for the timer
	File cpuFile; //Variable that holds the data for the cpuFile
	String cpuFileName = "CPU Level.dat"; //String for the name of the file that holds the user CPU Level
	int cpuMinLevel = 0, cpuMaxLevel = 10; //* Integers for the minimum and maximum cpuLevel values
	
	Timer cpuTimer = new Timer(350, new ActionListener() { //Timer for the CPU so there is a delay between its move and the player's move
		
		public void actionPerformed(ActionEvent e) { //Implementation of the actionPerformed function from the ActionListener class
			
			if (tttPause.pause) //Checks to see if the game is paused (this will cause the timerCount to not increment so that the timer does not continue while the game is paused)
				return;
			
			if (tttPause.pauseDisabled) { //If pauseDisabled is true (meaning the user is on the title screen), resets timerCount to 0 and stops the timer (since the game is not being played anymore)
				
				timerCount = 0;
				cpuTimer.stop();
				
			}
			
			if (timerCount < 3) { //Checks if the timer has activated less than 3 times
				
				timerCount++; //Increments the timerCount variable
				tttGUI.titleLabel.setText(tttGUI.titleLabel.getText() + "."); //Adds a '.' to the end of the titleLabel
				
			}
			else {
				
				cpuContinue(); //Continues the CPU's turn
				
			}
			
		}
		
	});
	
	public void formatCPUFile() { //Formats the cpuFile (saves a little bit of space since the formatter is used twice here and is also needed for the pause screen, so I added it as a function)
		
		try {
			
			Formatter formatter = new Formatter(cpuFile); //Creates a formatter object
			formatter.format("%s", "CPU Level: " + cpuLevel); //Formats the CPU file to say "CPU Level: " with the CPU level at the end
			formatter.close(); //Closes the formatter object
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			tttGUI.createErrorMessage("Error writing to " + cpuFile.getName() + " file", "File I/O Error"); //Displays a message for the user about the error
			
		}
		
	}
	
	public void setupCPUFile() { //Function to setup the CPU Level from the CPU Level.dat file
		
		cpuFile = new File(cpuFileName); //File object to interact with the CPU Level.dat file
		
		if (cpuFile.exists()) { //Checks if CPU Level.dat exists
			
			try {
				
				Scanner scanner = new Scanner(cpuFile); //Creates a new scanner for the cpuFile
				cpuLevel = Integer.parseInt(scanner.nextLine().substring(11)); //Gets the value at the end of the line and sets cpuLevel equal to it
				
				if (cpuLevel < cpuMinLevel || cpuLevel > cpuMaxLevel) { //Checks if the value is not in the range of accepted values for cpuLevel and changes the value if it is (both in the file and in the program)
					
					cpuLevel = 3; //Sets the cpuLevel back to 3
					formatCPUFile(); //Formats cpuFile
					tttGUI.createErrorMessage("CPU Level has been set to its default value of " + cpuLevel + ". (Please use a value between " + cpuMinLevel + " and " + cpuMaxLevel + ")", "CPU Level Error");
					//Displays a message for the user about the error
				}
				
				scanner.close(); //Closes the scanner object
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
				tttGUI.createErrorMessage("Error reading " + cpuFile.getName() + " file", "File I/O Error"); //Displays a message for the user about the error
				
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
				tttGUI.createErrorMessage("Error with formatting of " + cpuFile.getName() + " file", "File I/O Error"); //Displays a message for the user about the error
				cpuLevel = 3; //Sets the cpuLevel back to 3
				formatCPUFile(); //Formats cpuFile
				
			} catch (StringIndexOutOfBoundsException e) {
				
				e.printStackTrace();
				tttGUI.createErrorMessage("Error with formatting of " + cpuFile.getName() + " file", "File I/O Error"); //Displays a message for the user about the error
				cpuLevel = 3; //Sets the cpuLevel back to 3
				formatCPUFile(); //Formats cpuFile
				
			}
			
		}
		else { //If the file doesn't exist, then it creates a file and writes the default value to it
			
			try {
				
				cpuFile.createNewFile(); //Creates cpuFile
				formatCPUFile();  //Formats cpuFile
				
			} 
			catch (IOException e) {
				
				e.printStackTrace();
				tttGUI.createErrorMessage("Error creating " + cpuFile.getName() + " file", "File I/O Error");
				//Displays a message for the user about the error
				
			}
			
		}
		
	}
	
	public void setObjects(TicTacToeGUI tgui, TicTacToeLogic tl, TicTacToePauseScreen tps, TicTacToeOtherLogic tol) { //Used to set the objects to the ones initialized in the TicTacToeMain class
		
		tttGUI = tgui;
		tttLogic = tl;
		tttPause = tps;
		tttOLogic = tol;
		setupCPUFile(); //Sets up the CPU file (either reads from it or creates it for future use)
		
	}
	
	public void cpuMove() { //Function that executes the CPU's move
		
		if (tttLogic.boardFull()) //Checks to see if the board is full, and returns from the function if it is
			return;
		
		String otherPlayer; //The String that is used as the other player's letter
		int min = cpuMinLevel + 1, max = cpuMaxLevel, minValueToPass = cpuLevel; //Integers used for the statements below
		
		if (tttLogic.playerLetter.equals("X")) //Sets otherPlayer to the opposite of playerLetter
			otherPlayer = "O";
		else
			otherPlayer = "X";
		
		if (minValueToPass > 0) { //Checks to see if the cpuLevel is greater than 0 (0 means that it will always pick a random move and never go for a calculated one)
			
			if (checkNearWin(tttLogic.playerLetter)) { //Checks to see if the CPU can win (higher priority than blocking the other player)
				
				if (getRandomNumber(min, max) <= minValueToPass) { //Checks a random number to see if the CPU will decide to win or make another move instead
					
					calculatedMove(tttLogic.playerLetter); //Does a calculated move (win the game)
					return;
					
				}
				
			}
			
			if (checkNearWin(otherPlayer)) { //Checks to see if the CPU can block the player from winning (lower priority than winning, but higher priority than a random move
				
				if (getRandomNumber(min, max) <= minValueToPass) { //Checks a random number to see if it will decide to block or make another move instead
					
					calculatedMove(tttLogic.playerLetter); //Does a calculated move (block the opponent)
					return;
					
				}
				
			}
			
		}
		
		while (!randomChoice(tttLogic.playerLetter)); //Lets the CPU make a random move until it can find a valid move (a button that is not disabled)
		
		
	}
	
	public void cpuStart() { //Switches the the player to the CPU and starts the CPU timer (which will eventually call cpuContinue to execute the rest of the turn)
		
		tttLogic.playerSwitch();
		cpuTimer.start();
		
	}
	
	public void cpuContinue() { //Stops the cpuTimer and lets the CPU make its move, then switches the player back and checks if the game is won (if the game is over it prompts to play again)
		
		cpuTimer.stop(); //Stops the timer
		timerCount = 0; //Resets timerCount
		cpuMove(); //Lets the CPU make its move
		tttLogic.playerSwitch(); //Switches the player back
		tttLogic.hasWon = tttOLogic.checkWin(); //Checks if the game is won
		
		if (tttLogic.gameOver()) { //If the game is won or there is a draw, prompts the player if the want to play again
			
			tttOLogic.promptPlayAgain();
			
		}

	}
	
	public int getRandomNumber(int min, int max) { //Function that returns a random int between the two given values
		
		return rand.nextInt((max - min) + 1) + min;
		
	}
	
	private boolean randomChoice(String playerLetter) { //Function that generates 2 random integers to determine a button that the CPU will "press"
		
		int min = 0, max = 2; //The minimum and maximum values for the random integers
		int randomRow = getRandomNumber(min, max); //The random integer that will correspond to the row
		int randomCol = getRandomNumber(min, max); //The random integer that will correspond to the column
		
		if (tttGUI.buttons[randomRow][randomCol].isEnabled()) { //Checks to see if the randomly selected button is enabled, and sets the text to the CPU's letter and disables it
			tttGUI.buttons[randomRow][randomCol].setText(playerLetter);
			tttGUI.buttons[randomRow][randomCol].setEnabled(false);
			return true; //Returns true if the button is enabled and has been set
		
		}
		
		return false; //Returns false if the button is disabled
			
	}
	
	private boolean checkNearWin(String player) { //Function that checks if the specified play is near a win (2 in a row/column/diagonal)
		
		int numOfPlayer = 0; //This int is used to tally the number of player letters in a given row, column, or diagonal
		int nearWinNum = 2; //This int represents the number needed for the CPU to flag the given row, column, or diagonal
		boolean hasEmpty = false; //This boolean is used to determine if the given row, column, or diagonal has an empty string in it
		winRow = -1; //Sets winRow to -1 (its default value which is out of bounds of the calculatedMove function)
		winCol = -1; //Sets winCol to -1 (its default value which is out of bounds of the calculatedMove function)
		winDia = ""; //Sets winDia to "" (its default value which is out of bounds of the calculatedMove function)
		
		for (int x = 0; x < tttGUI.SIZE; x++) { //This for loop checks the rows to see if a win is achievable
			
			for (int y = 0; y < tttGUI.SIZE; y++) {
				
				if (tttGUI.buttons[x][y].getText().equals(player)) { //Adds to the tally
					
					numOfPlayer++;
					
				}
				
				else if (tttGUI.buttons[x][y].getText().equals("")) { //Recognizes that there is an empty button
					
					hasEmpty = true;
					
				}
				
				
			}
			
			if (numOfPlayer == nearWinNum && hasEmpty) { //Checks to see if the row has 2 of the same player letters in it with an empty button
				
				winRow = x; //Sets winRow to the row the loop is currently in
				return true;
				
			}
			
			numOfPlayer = 0; //Resets the values
			hasEmpty = false;
			
		}
		
		for (int y = 0; y < tttGUI.SIZE; y++) { //This for loop checks the columns to see if a win is achievable
			
			for (int x = 0; x < tttGUI.SIZE; x++) {
				
				if (tttGUI.buttons[x][y].getText().equals(player)) {
					
					numOfPlayer++;
					
				}
				
				else if (tttGUI.buttons[x][y].getText().equals("")) {
					
					hasEmpty = true;
					
				}
				
				
			}
			
			if (numOfPlayer == nearWinNum && hasEmpty) { //Checks to see if the column has 2 player letters in it with an empty button
				
				winCol = y; //Sets winCol to the column the loop is currently in
				return true;
				
			}
			
			numOfPlayer = 0; //Resets the values
			hasEmpty = false;
			
		}
		
		for (int x = 0, y = 0; x < tttGUI.SIZE; x++, y++) { //This for loop checks the left to right diagonal to see if a win is achievable
			
			if (tttGUI.buttons[x][y].getText().equals(player)) {
				
				numOfPlayer++;
				
			}
			
			else if (tttGUI.buttons[x][y].getText().equals("")) {
				
				hasEmpty = true;
				
			}
			
			
		}
		
		if (numOfPlayer == nearWinNum && hasEmpty) { //Checks to see if the diagonal has 2 player letters in it with an empty button
			
			winDia = "L"; //Sets winDia to the diagonal the loop is currently in
			return true;
			
		}
		
		numOfPlayer = 0; //Resets the values
		hasEmpty = false;
		
		for (int x = tttGUI.SIZE - 1, y = 0; x >= 0; x--, y++) { //This for loop checks the right to left diagonal to see if a win is achievable
			
			if (tttGUI.buttons[x][y].getText().equals(player)) {
				
				numOfPlayer++;
				
			}
			
			else if (tttGUI.buttons[x][y].getText().equals("")) {
				
				hasEmpty = true;
				
			}
			
			
		}
		
		if (numOfPlayer == nearWinNum && hasEmpty) { //Checks to see if the diagonal has 2 player letters in it with an empty button
			
			winDia = "R"; //Sets winDia to the diagonal the loop is currently in
			return true;
			
		}
		
		return false; //Returns false if none of the previous for loops return a true
		
	}
	
	private void calculatedMove(String player){ //The function that will make a calculated move based on the checkNearWin function's results
		
		if (winRow != -1) { //Checks to see if winRow is not a default value
			
			for (int x = winRow, y = 0; y < tttGUI.SIZE; y++) { //Checks the winRow to see where the empty button is and set it to the CPU's letter
				
				if (tttGUI.buttons[x][y].getText().equals("")) {
					
					tttGUI.buttons[x][y].setText(player);
					tttGUI.buttons[x][y].setEnabled(false);
					return;
					
				}
				
			}
			
		}
		
		else if (winCol != -1) { //Checks to see if winCol is not a default value
			
			for (int x = 0, y = winCol; x < tttGUI.SIZE; x++) { 
				
				if (tttGUI.buttons[x][y].getText().equals("")) { //Checks the winCol to see where the empty button is and set it to the CPU's letter
					
					tttGUI.buttons[x][y].setText(player);
					tttGUI.buttons[x][y].setEnabled(false);
					return;
					
				}
				
			}
			
		}
		
		else if (winDia.equals("L")) { //Checks to see if winDia is the value for left to right
			
			for (int x = 0, y = 0; x < tttGUI.SIZE; x++, y++) {
				
				if (tttGUI.buttons[x][y].getText().equals("")) { //Checks the winDia to see where the empty button is and set it to the CPU's letter
					
					tttGUI.buttons[x][y].setText(player);
					tttGUI.buttons[x][y].setEnabled(false);
					return;
					
				}
				
			}
			
		}
		
		else if (winDia.equals("R")) { //Checks to see if winDia is the value for right to left
			
			for (int x = tttGUI.SIZE - 1, y = 0; x >= 0; x--, y++) {
				
				if (tttGUI.buttons[x][y].getText().equals("")) { //Checks the winDia to see where the empty button is and set it to the CPU's letter
					
					tttGUI.buttons[x][y].setText(player);
					tttGUI.buttons[x][y].setEnabled(false);
					return;
					
				}
				
			}
			
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (!tttLogic.player1Turn) //This makes sure the player cannot interact with the buttons while it is the CPU's turn
			return;
		
		tttLogic.hasWon = tttOLogic.checkWin(); //Checks to see if a player has won, which will cause the next if statements to be false if a player has won
			
		if (!tttLogic.gameOver()) {
			
			int row = Character.getNumericValue(e.getActionCommand().charAt(0)), col = Character.getNumericValue(e.getActionCommand().charAt(1)); //Gets the integers from the action command
			tttGUI.buttons[row][col].setText(tttLogic.playerLetter); //Sets the button to the player's letter
			tttGUI.buttons[row][col].setEnabled(false); //Sets the button to be disabled
			
		}
		
		tttLogic.hasWon = tttOLogic.checkWin(); //Checks again to see if a player has won, which will cause the next if statements to be false if a player has won

		
		if (!tttLogic.gameOver()) { //If the player did not win with their last move, allows the CPU to make its turn
			
			cpuStart();
			
		}
		
		else {
			
			tttOLogic.promptPlayAgain(); //If the game is over, prompt the player to play again
			
		}
		
	}
	
}
