//Made by Theodore Russell and Jose Pena
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TicTacToeTitleScreen implements ActionListener {
	
	TicTacToeGUI tttGUI; //Object for the TicTacToeGUI class
	JPanel titleScreenPanel = new JPanel(); //Main panel for the title screen
	JPanel titleLabelPanel = new JPanel(); //Panel for the label on the title screen
	JLabel titleLabel = new JLabel("Tic-Tac-Toe"); //Label for the title screen
	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30)); //Panel for the buttons on the title screen (the reason for the FlowLayout after is so I could space out the buttons a bit so they aren't right next to each other and the other panels)
	JButton titleButtons[] = new JButton[2]; //Array that holds both of the buttons on the title screen
	JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 30)); //Panel for the components at the bottom (the reason for the FlowLayout is to space out the components)
	JTextArea creditText = new JTextArea("Made by Dylan Dunsheath, Theodore Russell, David Contey, Joan Estepan, and Jose Pena"); //Textarea that holds the credits for the program (the reason for making it a JTextArea and not a JLabel is because JLabel does not support text wrapping, so with a JTextArea I can have the credits wrap to the next line)
	JButton exitButton = new JButton("Exit"); //Button used to exit the game (the x button at the top of the window works too but this is a more formal way to close the game out)
	String buttonText[] = {"CPU Mode", "2 Player Mode", "Exit"}; //Array that holds the Strings used for each of the buttons
	String actionCommands[] = {"CPU", "2P", "Exit"}; //Array that holds the Strings used for each of the action commands for the buttons
	Color buttonColors[] = {Color.GREEN, Color.CYAN, Color.RED}; //Array that holds the Colors used for each of the buttons
	
	public void setObjects(TicTacToeGUI tgui) { //Used to set the objects to the ones initialized in the TicTacToeMain class
		
		tttGUI = tgui;
		
		setupTitleScreen(); //This function is called here instead of in a constructor since it needs the tttGUI object first
		
	}
	
	public void setupTitleScreen() {
		
		//Initializes titleLabel
		titleLabel.setFont(new Font("Lucida Blackletter", Font.BOLD, 100)); //Sets the font of titleLabel
		titleLabel.setForeground(Color.BLUE); //Sets the color of titleLabel to blue
		titleLabel.setOpaque(false); //Makes titleLabel not opaque
		titleLabel.setHorizontalAlignment(JLabel.CENTER);  //Sets the horizontal alignment of titleLabel
		titleLabel.setPreferredSize(new Dimension(tttGUI.WINDOW_WIDTH, 250));  //Sets the preferred size of titleLabel
		
		//Initializes titleLabelPanel
		titleLabelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10, true)); //Creates a border around titleLabelPanel (makes it back, makes the stroke 10, and makes it rounded)
		titleLabelPanel.setOpaque(false); //Makes titleLabelPanel not opaque
		titleLabelPanel.add(titleLabel); //Adds titleLabel to titleLabelPanel
		
		//Loop to initialize the two titleButtons JButtons
		for (int x = 0; x <= 1; x++) {
			
			titleButtons[x] = new JButton(); //Initializes the button
			titleButtons[x].setPreferredSize(new Dimension(370, 320)); //Sets the button's preferred size
			titleButtons[x].setFont(new Font("Lucida Blackletter", Font.BOLD, 42)); //Sets the button's font
			titleButtons[x].setFocusable(false); //Makes the button not focusable
			titleButtons[x].setActionCommand(actionCommands[x]); //Adds an action command to the button
			titleButtons[x].addActionListener(this); //Adds an action listener to the button
			titleButtons[x].setText(buttonText[x]); //Sets the text of the button
			titleButtons[x].setBackground(buttonColors[x]); //Sets the color of the button
			buttonPanel.add(titleButtons[x]); //Adds the button to buttonPanel
			
		}
		
		//Initializes buttonPanel
		buttonPanel.setBackground(Color.WHITE); //Makes buttonPanel's background white
		buttonPanel.setOpaque(true); //Makes buttonPanel opaque
		
		//Initializes creditText
		creditText.setFont(new Font("Lucida Blackletter", Font.PLAIN, 20)); //Sets the font of creditText
		creditText.setLineWrap(true); //Makes creditText have line wrap
		creditText.setWrapStyleWord(true); //Makes creditText have word wrap
		creditText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); //Creates a border around creditText
		creditText.setEditable(false); //Makes creditText not editable
		creditText.setFocusable(false); //Makes creditText not focusable
		creditText.setForeground(Color.BLACK); //Sets the color of creditText to black
		creditText.setOpaque(false); //Makes creditText not opaque
		creditText.setPreferredSize(new Dimension(420, 100)); //Sets the preferred size of creditText
		
		//Initializes exitButton
		exitButton.setOpaque(true); //Sets the exitButton to be opaque
		exitButton.setPreferredSize(new Dimension(200, 100)); //Sets the preferred size of exitButton
		exitButton.setFont(new Font("Lucida Blackletter", Font.BOLD, 60)); //Sets the font of exitButton
		exitButton.setBackground(buttonColors[2]); //Sets the color of exitButton to red
		exitButton.setFocusable(false); //Makes exitButton not focusable
		exitButton.setActionCommand(actionCommands[2]); //Adds an action command to exitButton
		exitButton.addActionListener(this); //Adds an action listener to exitButton
		
		//Initializes bottomPanel
		bottomPanel.setBackground(Color.WHITE); //Makes bottomPanel's background white
		bottomPanel.setOpaque(true); //Makes bottomPanel opaque
		bottomPanel.add(creditText); //Adds creditText to bottomPanel
		bottomPanel.add(exitButton); //Adds exitButton to bottomPanel
		
		//Initializes titleScreenPanel
		titleScreenPanel.setLayout(new BorderLayout()); //Sets the layout of titleScreenPanel
		titleScreenPanel.add(titleLabelPanel, BorderLayout.NORTH); //Adds titleLabelPanel to titleScreenPanel
		titleScreenPanel.add(buttonPanel, BorderLayout.CENTER); //Adds buttonPanel to titleScreenPanel
		titleScreenPanel.add(bottomPanel, BorderLayout.SOUTH); //Adds bottomPanel to titleScreenPanel
		
		tttGUI.changePanel("title"); //Changes the panel to the title panel (since now it's setup, we can actually display it)
		tttGUI.tictactoeFrame.setVisible(true); //Makes the frame visible (was originally in TicTacToeGUI but was moved here so that the game starts on the title screen)
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(actionCommands[2])) { //If the exit button is pressed, exit the game
			
			System.exit(0);
			
		}
		else {
			
			if (e.getActionCommand().equals(actionCommands[0])) { //If the CPU Mode button is pressed, reset the game (the true sets cpuMode to true in the function)
				
				tttGUI.resetGame(true);
				
			}
			else if (e.getActionCommand().equals(actionCommands[1])) { //If the 2 Player Mode button is pressed, reset the game (the false sets cpuMode to false in the function)
				
				tttGUI.resetGame(false);
				
			}
			
		}
		
	}
	
}
