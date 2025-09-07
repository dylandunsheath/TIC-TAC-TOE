
public class TicTacToeMain {

	public static void main(String[] args) {
		
		TicTacToeLogic tttLogic = new TicTacToeLogic(); //Object for the Logic class
		TicTacToeCPUMode tttCPU = new TicTacToeCPUMode(); //Object for the CPU class
		TicTacToe2PlayerMode ttt2Player = new TicTacToe2PlayerMode(); //Object for the 2 Player class
		TicTacToePauseScreen tttPause = new TicTacToePauseScreen(); //Object for the Pause Screen class
		TicTacToeTitleScreen tttTitle = new TicTacToeTitleScreen(); //Object for the Title Screen class
		TicTacToeOtherLogic tttOLogic = new TicTacToeOtherLogic(); //Object for the Other Logic class
		TicTacToeGUI tttGUI = new TicTacToeGUI(tttCPU, tttLogic, ttt2Player, tttPause, tttTitle, tttOLogic); //Object for the TicTacToeGUI class
		tttCPU.setObjects(tttGUI, tttLogic, tttPause, tttOLogic); //Sets the objects in the CPU class to the ones initialized in this class
		tttLogic.setObjects(tttGUI, tttCPU, tttPause, tttOLogic); //Sets the objects in the Logic class to the ones initialized in this class
		tttPause.setObjects(tttGUI, tttCPU, tttLogic); //Sets the objects in the Pause Screen class to the ones initialized in this class
		ttt2Player.setObjects(tttGUI, tttLogic, tttOLogic); //Sets the objects in the 2 Player class to the ones initialized in this class
		tttTitle.setObjects(tttGUI); //Sets the objects in the Title Screen class to the ones initialized in this class
		tttOLogic.setObjects(tttGUI, tttLogic, tttCPU, tttPause); //Sets the objects in the Other Logic class to the ones initialized in this class

	}

}
