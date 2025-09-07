//Made by Theodore Russell

import java.util.Random;

public class TicTacToeLogic { //Moved some of the functions from here to the TicTacToeOtherLogic class (since those are the ones David will be implementing)
	
	TicTacToeGUI tttGUI; //Object for the TicTacToeGUI (GUI) class
	TicTacToeCPUMode tttCPU; //Object for the CPU class
	TicTacToePauseScreen tttPause; //Object for the Pause Screen class
	TicTacToeOtherLogic tttOLogic; //Object for the Other Logic class
	boolean cpuMode; //Boolean that determines whether the game is in CPU or 2 Player mode (true = CPU Mode, false = 2 Player Mode)
	String playerLetter = "X"; // Variable that stores the letter of the current player
	boolean player1Turn = true; // Boolean that determines if it is player 1 or player 2's turn
	boolean hasWon = false; //Boolean that determines if the game is won or not
	boolean draw = false; //Boolean that determines if the game is a draw
	Random rand = new Random(); //Object for the Random class

	public void setObjects(TicTacToeGUI tgui, TicTacToeCPUMode tcpu, TicTacToePauseScreen tps, TicTacToeOtherLogic tol) { //Used to set the objects to the ones initialized in the TicTacToeMain class
		
		tttGUI = tgui;
		tttCPU = tcpu;
		tttPause = tps;
		tttOLogic = tol;
		
	}
	
	public void randomStart() { //Function that randomly picks which player will move first
		
		int min = 0, max = 1; //Variables used for randomly choosing which player will start first
		int randomPlayerStart = tttCPU.getRandomNumber(min, max); //Variable that is randomized to decide who will start first
		
		if (randomPlayerStart == 1) { //Checks which player is starting first, will start with the CPU or player "O" if randomPlayerStart = 1
			
			if (cpuMode) {
				
				tttCPU.cpuStart();
				
			}
			else {
				
				playerSwitch();
				
			}
			
		}
		
	}
	
	public boolean boardFull() { //Function which checks if the board is full and returns the boolean. It loops through each button and looks to see if any are enabled.
		
		for (int x = 0; x < tttGUI.SIZE; x++) { 
			
			for (int y = 0; y < tttGUI.SIZE; y++) {
				
				if (tttGUI.buttons[x][y].isEnabled()) {
					
					return false; //If the function finds a button that is enabled, it returns false, showing that the board is not full
					
				}
				
			}
			
		}
		
		return true; //If it is unable to find an enabled button, it returns true, showing that the board is full
		
	}
	
	public void playerSwitch() { //Function that handles switching between the players
		
		if (playerLetter.equals("X")) { //Checks to see if the player's letter is X, and if it isn't X, it can only be O, so it will be set to the other letter in both cases.
			
			playerLetter = "O";
			
		}
		else {
			
			playerLetter = "X";
			
		}
		
		if (!boardFull()) { //This is only used in the event of a draw, because otherwise it will not display draw on the playerTurnLabel in the event of a draw
			
			tttGUI.titleLabel.setText("Player " + playerLetter + " Turn");
			
		}
		
		player1Turn = !player1Turn; //Sets the player1Turn to the opposite value
		
	}
	

	
	public boolean gameOver() { //Function that checks if the game is over (both through someone winning and a draw)
		
		return hasWon || draw;
		
	}
	
}
