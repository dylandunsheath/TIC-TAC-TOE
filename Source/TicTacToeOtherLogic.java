//Made by David Contey
import javax.swing.JOptionPane;

public class TicTacToeOtherLogic {

	TicTacToeGUI tttGUI; //Object for the GUI class
	TicTacToeLogic tttLogic; //Object for the Logic class
	TicTacToeCPUMode tttCPU; //Object for the CPU class
	TicTacToePauseScreen tttPause; //Object for the Pause Screen class
	
	public void setObjects(TicTacToeGUI tgui, TicTacToeLogic tl, TicTacToeCPUMode tcpu, TicTacToePauseScreen tps) { //Used to set the objects to the ones initialized in the TicTacToeMain class
		
		tttGUI = tgui;
		tttLogic = tl;
		tttCPU = tcpu;
		tttPause = tps;
		
	}
	
	private boolean checkWinPlayer(String player) {
		
		int scoreCount=0;//counts score in a row, column, or diagonal
		
		for(int x=0;x<tttGUI.SIZE;x++) { //checks rows for a win
			for(int y=0;y<tttGUI.SIZE;y++) {
				if(tttGUI.buttons[x][y].getText().equals(player)) {	
					scoreCount++;
				}
			}
			
			if(scoreCount==tttGUI.SIZE) {
				tttGUI.titleLabel.setText("Player " + player + " Wins");
				return true;
			}
			scoreCount=0;
		}
		
		for(int y=0;y<tttGUI.SIZE;y++) { //checks columns for a win
			for(int x=0;x<tttGUI.SIZE;x++) {
				if(tttGUI.buttons[x][y].getText().equals(player)) {
					scoreCount++;
				}
			}
			if(scoreCount==tttGUI.SIZE) {
				tttGUI.titleLabel.setText("Player " + player + " Wins");
				return true;
			}
			scoreCount=0;
		}
		
		for(int x=0, y=0;x<tttGUI.SIZE;x++,y++) {//checks for a win the on the diagonal that descends from the top left
			if(tttGUI.buttons[x][y].getText().equals(player)) {
				scoreCount++;
			}
		}
		if(scoreCount==tttGUI.SIZE){
			tttGUI.titleLabel.setText("Player " + player + " Wins");
			return true;
		}
		scoreCount=0;
		
		for(int x=2, y=0;x>=0;x--,y++) {//checks for a win on the diagonal that ascends from the bottom left
			if(tttGUI.buttons[x][y].getText().equals(player)) {
				scoreCount++;
			}
		}
		if(scoreCount==tttGUI.SIZE){
			tttGUI.titleLabel.setText("Player " + player + " Wins");
			return true;
		}
		if(tttLogic.boardFull()) {//calls draw if there is no winner
			tttGUI.titleLabel.setText("Draw");
			tttLogic.draw=true;
		}
		return false; //Returns false if there is no winner
		
	}
	
	public boolean checkWin() {//uses checksWinPlayer to see if there, well, a winner
		boolean win=checkWinPlayer("X")||checkWinPlayer("O");
		if(tttLogic.gameOver()) {
			for(int x=0;x<tttGUI.SIZE;x++) {
				for(int y=0;y<tttGUI.SIZE;y++) {
					tttGUI.buttons[x][y].setEnabled(false);//disables buttons
				}
			}
		}
		return win;
	}
	
	public void promptPlayAgain() {//asks user if they would like to play again.
		int play=JOptionPane.showConfirmDialog(tttGUI.tictactoeFrame, "Do you want to play again?","Play again?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(play==JOptionPane.YES_OPTION) {//they say YES, the board is reset
			resetBoard();
		}
		else {//they say no, they go back to the title screen
			tttPause.exitToTitle();
		}
	}
	
	public void resetBoard() {//resets board if they player is to play again
		for(int x=0;x<tttGUI.SIZE;x++) {
			for(int y=0;y<tttGUI.SIZE;y++) {//fixes buttons by resetting them
				tttGUI.buttons[x][y].setText("");
				tttGUI.buttons[x][y].setEnabled(true);
			}
		}
		
		//resets variables, makes a new game with a new random starter
		tttLogic.hasWon=false;
		tttLogic.draw=false;
		tttLogic.playerLetter="X";
		tttGUI.titleLabel.setText("Player "+ tttLogic.playerLetter+" Turn");
		
		tttLogic.randomStart();
	}
	
}
