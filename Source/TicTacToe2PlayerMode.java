//Made by Joan Estepan (edits by Theodore)
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe2PlayerMode implements ActionListener {

	TicTacToeGUI tttGUI; //Object for the TicTacToeGUI class
	TicTacToeLogic tttLogic; //Object for the Logic class
	TicTacToeOtherLogic tttOLogic; //Object for the Other Logic class

	public void setObjects(TicTacToeGUI tgui, TicTacToeLogic tl, TicTacToeOtherLogic tol) { //Used to set the objects to the ones initialized in the TicTacToeMain class

		tttGUI = tgui;
		tttLogic = tl;
		tttOLogic = tol;

	}

	public void actionPerformed(ActionEvent e) {

		tttLogic.hasWon = tttOLogic.checkWin(); //Checks to see if a player has won, which will cause the next if statements to be false if a player has won

		if (!tttLogic.gameOver()) { //If the game is not over (which should be true since nothing has been changed yet), sets the button to the letter of the current player and disables it

			int row = Character.getNumericValue(e.getActionCommand().charAt(0)), col = Character.getNumericValue(e.getActionCommand().charAt(1)); //Gets the integers from the action command
			tttGUI.buttons[row][col].setText(tttLogic.playerLetter); //Sets the button to the player's letter
			tttGUI.buttons[row][col].setEnabled(false); //Sets the button to be disabled

		}

		tttLogic.hasWon = tttOLogic.checkWin(); //Checks again to see if a player has won, which will cause the next if statements to be false if a player has won

		if (!tttLogic.gameOver()) { //If the current player did not win with their last move, switches to the other player

			tttLogic.playerSwitch();

		} else { //If the current player has won, prompt the players to play again

			tttOLogic.promptPlayAgain();

		}

	}
}