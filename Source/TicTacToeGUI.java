// This GUI class was created by Dylan, and was edited by Theodore to integrate other classes into the program.
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TicTacToeGUI {
	
	//Integer variable for the size of the board (3x3)
	final int SIZE = 3;
	//Integer variable for the width of the window
	final int WINDOW_WIDTH = 850;
	//Integer variable for the height of the window
	final int WINDOW_HEIGHT = 850;
	//Object for the CPU class
	TicTacToeCPUMode tttCPU;
	//Object for the Logic class
	TicTacToeLogic tttLogic;
	//Object for the 2 Player class
	TicTacToe2PlayerMode ttt2Player;
	//Object for the Pause Screen class
	TicTacToePauseScreen tttPause;
	//Object for the Title Screen class
	TicTacToeTitleScreen tttTitle;
	//Object for the Other Logic class
	TicTacToeOtherLogic tttOLogic;
	//Creates frame for GUI
	JFrame tictactoeFrame = new JFrame(); 
	//Label for the top of the frame
	JLabel titleLabel = new JLabel();
	//Creates a panel to hold the label
	JPanel titlePanel = new JPanel();
	//2D Array of JButtons to be used in the game
	JButton[][] buttons = new JButton[SIZE][SIZE];
	//Panel to hold the buttons
	JPanel buttonPanel = new JPanel();
	//Panel that will hold each of the main screens (the game screen, the pause screen, and the title screen)
	JPanel mainPanel = new JPanel();
	//Panel that will hold all the components related to the game (titlePanel and buttonPanel)
	JPanel gamePanel = new JPanel();
	//CardLayout object to use the CardLayout for mainPanel
	CardLayout cardLayout;
	//Array of strings that holds the name of each of the main panels
	String panelNames[] = {"game", "pause", "title"};
	
	//The constructor and setObjects function have been updated to include the new classes
	TicTacToeGUI(TicTacToeCPUMode tcpu, TicTacToeLogic tl, TicTacToe2PlayerMode t2p, TicTacToePauseScreen tps, TicTacToeTitleScreen tts, TicTacToeOtherLogic tol) //Constructor for this class
	{
		//Sets the objects from the main class so this class can interact with them
		setObjects(tcpu, tl, t2p, tps, tts, tol);
		
		//Sets up the LookAndFeel to be cross platform (will mean that the LookAndFeel across Mac and Windows is the same)
		try {
			
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			
		} catch (Exception e) { //If there is an error setting the LookAndFeel, informs the user about the error
			
			e.printStackTrace();
			createErrorMessage("Error trying to set cross platform LookAndFeel", "LookAndFeel Error");
			
		}
		
		//Allows the user to close the program
		tictactoeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Makes frame has a width and height of 850
		tictactoeFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		//Sets the preferred size of the frame (needed for the pack() function used further below)
		tictactoeFrame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		//Makes the frame not resizable
		tictactoeFrame.setResizable(false);
		//Puts the frame in the middle of the screen
		tictactoeFrame.setLocationRelativeTo(null);
		//Sets background color for the frame to white
		tictactoeFrame.getContentPane().setBackground(Color.WHITE);
		//Sets layout for the frame
		tictactoeFrame.setLayout(new BorderLayout());
		//Titles the frame "Tic-Tac-Toe by Coding Comrades"
		tictactoeFrame.setTitle("Tic-Tac-Toe by Coding Comrades");
		//Sets background color of titleLabel to white
		titleLabel.setBackground(Color.WHITE);
		//Sets titleLabel's text color to blue
		titleLabel.setForeground(Color.BLUE);
		//Sets a bolded font to style: "Lucida Blackletter" at a size of 80
		titleLabel.setFont(new Font("Lucida Blackletter", Font.BOLD, 80));
		//Sets horizonal alignment for titleLabel to center
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		//Sets titleLabel's text to "Player X Turn"
		titleLabel.setText("Player " + tttLogic.playerLetter + " Turn");
		//Makes the background of titleLabel visible
		titleLabel.setOpaque(true);
		//Sets layout of titlePanel
		titlePanel.setLayout(new BorderLayout());
		//Sets a 3x3 grid layout for buttonPanel
		buttonPanel.setLayout(new GridLayout(3, 3));
		//Sets gamePanel's layout to a BorderLayout (essentially the layout we had for the frame before
		gamePanel.setLayout(new BorderLayout());
		//Sets mainPanel's layout to a CardLayout (will allow us to transition from one panel to another)
		mainPanel.setLayout(new CardLayout());
		//Adds a key listener to the frame (this is used to activate the pause menu when pressing escape on the keyboard)
		tictactoeFrame.addKeyListener(tttPause);
		
		// For loop to initialize the game buttons and add them to buttonPanel
		for (int x = 0; x < SIZE; x++)
		{
			
			for (int y = 0; y < SIZE; y++) {
				
				//Declares buttons
				buttons[x][y] = new JButton();
				//Set font of buttons to Times New Roman and bolds them and at a font size of 120
				buttons[x][y].setFont(new Font("Times New Roman", Font.BOLD, 120));
				//Sets buttons to not be focusable
				buttons[x][y].setFocusable(false);
				//Makes buttons not look like default java buttons (helps it look like a tic-tac-toe board instead of a bunch of buttons)
				buttons[x][y].setContentAreaFilled(false);
				//Adds tttCPU as an action listener, but will only be used for the resetGame function so it can see if it needs to be changed
				buttons[x][y].addActionListener(tttCPU);
				//Sets an action command for the buttons so the buttons can be differentiated from each other in the action listener
				buttons[x][y].setActionCommand("" + x + y);
				
				// Adds the buttons to to buttonPanel
				buttonPanel.add(buttons[x][y]);
				
			}
			
		}
		
		//Adds titleLabel to titlePanel
		titlePanel.add(titleLabel);
		// Adds buttonPanel to frame
		gamePanel.add(buttonPanel);
		// Adds titlePanel to the top of the GUI
		gamePanel.add(titlePanel, BorderLayout.NORTH);
		
		//Adds each of the three main panels to mainPanel (the one that will be added to the frame) and sets their names to the names used in panelNames
		mainPanel.add(gamePanel, panelNames[0]);
		mainPanel.add(tttPause.pausePanel, panelNames[1]);
		mainPanel.add(tttTitle.titleScreenPanel, panelNames[2]);
		
		//Adds the mainPanel to the frame
		tictactoeFrame.add(mainPanel);
		
		//Packs the frame (this basically means that everything we put into the frame will be "packed" so that nothing will be outside of the frame).
		tictactoeFrame.pack();
		
		//Sets the CardLayout object for mainPanel's layout (this allows this class to transition between the three main panels in the GUI)
		cardLayout = (CardLayout) mainPanel.getLayout();
		
		
		
	}
	
	//Used to set the objects to the ones initialized in the TicTacToeMain class
	public void setObjects(TicTacToeCPUMode tcpu, TicTacToeLogic tl, TicTacToe2PlayerMode t2p, TicTacToePauseScreen tps, TicTacToeTitleScreen tts, TicTacToeOtherLogic tol) {
		
		tttCPU = tcpu;
		tttLogic = tl;
		ttt2Player = t2p;
		tttPause = tps;
		tttTitle = tts;
		tttOLogic = tol;
		
	}
	
	//Function that resets the game after transitioning from the title screen to the game
	public void resetGame(boolean isCPU) { 
		
		//Sets cpuMode to what the function was given
		tttLogic.cpuMode = isCPU;
		//Resets all of the important variables to their default states
		tttLogic.playerLetter = "X";
		tttLogic.player1Turn = true;
		titleLabel.setText("Player " + tttLogic.playerLetter + " Turn");
		tttLogic.hasWon = false;
		tttLogic.draw = false;
		//Resets the board (will also choose a random player to start in that function)
		tttOLogic.resetBoard();
		//Allows the player to pause while in-game
		tttPause.pauseDisabled = false;
		
		//If the buttons have action listeners, it checks to see if the action listeners are incorrect based on the game mode, and adds the correct one after removing the incorrect one if there is an incorrect action listener
		if (tttLogic.cpuMode) {
			
			for (int x = 0; x < SIZE; x++) {
				
				for (int y = 0; y < SIZE; y++) {
					
					if (buttons[x][y].getActionListeners()[0].equals(ttt2Player)) {
						
						buttons[x][y].removeActionListener(ttt2Player);
						buttons[x][y].addActionListener(tttCPU);
						
					}
					
				}
				
			}
			
		}
		else {
			
			for (int x = 0; x < SIZE; x++) {
				
				for (int y = 0; y < SIZE; y++) {
					
					if (buttons[x][y].getActionListeners()[0].equals(tttCPU)) {
						
						buttons[x][y].removeActionListener(tttCPU);
						buttons[x][y].addActionListener(ttt2Player);
						
					}
					
				}
				
			}
			
		}
		
		//Changes the panel to the game panel
		changePanel("game");
		
	}
	
	//Function that changes the panel that mainPanel is currently displaying
	public void changePanel(String panelName) {
		
		//Loops through each of the panel names until it finds out which panel name the function is given and changes the panel to the named panel
		for (int x = 0; x < panelNames.length; x++) {
			
			if (panelName.equals(panelNames[x])) { //If the given panel is found, set the layout to show the given panel
				
				cardLayout.show(mainPanel, panelName);
				return;
				
			}
			
		}
		
		//If the loop has not resulted in a return by this point, there is an error, so the user is notified by the error (this should never occur though)
		createErrorMessage("Error changing panels, the string when calling the changePanel function may be invalid.", "CardLayout Error");
		
	}
	
	//Function that creates a JOptionPane which displays an error message
	public void createErrorMessage(String errorText, String errorName) {
		
		JOptionPane.showMessageDialog(tictactoeFrame, errorText, errorName, JOptionPane.WARNING_MESSAGE);
		
	}

}
