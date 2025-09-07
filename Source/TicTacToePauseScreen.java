//Made by Theodore Russell
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TicTacToePauseScreen implements ChangeListener, ActionListener, KeyListener {
	
	TicTacToeGUI tttGUI; //Object for the GUI class
	TicTacToeCPUMode tttCPU; //Object for the CPU class
	TicTacToeLogic tttLogic; //Object for the Logic class
	JPanel pausePanel = new JPanel(); //Main panel for the pause screen
	JLabel pauseLabel = new JLabel("PAUSE"); //Label that is displayed at the top of the pause screen
	JPanel sliderPanel = new JPanel(); //Panel that contains the slider (only visible in CPU Mode)
	JSlider cpuLevelSlider; //Slider that controls the CPU level
	JLabel sliderLabel = new JLabel("CPU Level"); //Label that displays above the slider
	JPanel exitPanel = new JPanel(); //Panel that contains the exit button
	JButton exitButton = new JButton("Exit to Title"); //Button that exits to the title screen
	boolean pause = false; //Boolean that keeps track of if the game is paused or not
	boolean pauseDisabled = true; //Boolean that controls the pause function (it is disabled on the title screen)
	
	public void setObjects(TicTacToeGUI tgui, TicTacToeCPUMode tcpu, TicTacToeLogic tl) { //Used to set the objects to the ones initialized in the TicTacToeMain class
		
		tttGUI = tgui;
		tttCPU = tcpu;
		tttLogic = tl;
		
		setupPauseScreen(); //Needs tttGUI and tttCPU to be setup, so this function has to occur after those objects are set
		
	}
	
	public void setupPauseScreen() { //Function that sets up the pause screen (adding components, changing properties, etc.)
		
		pausePanel.setBackground(Color.WHITE); //Sets the background of pausePanel to white
		pausePanel.setLayout(new BorderLayout()); //Sets the layout of pausePanel to a BorderLayout
		
		pauseLabel.setFont(new Font("Lucida Blackletter", Font.BOLD, 100)); //Sets the font of pauseLabel
		pauseLabel.setHorizontalAlignment(JLabel.CENTER); //Sets the horizontal alignment of pauseLabel
		pauseLabel.setVerticalAlignment(JLabel.NORTH); //Sets the vertical alignment of pauseLabel
		pauseLabel.setPreferredSize(new Dimension(tttGUI.WINDOW_WIDTH, 250)); //Sets the preferred size of pauseLabel
		
		cpuLevelSlider = new JSlider(tttCPU.cpuMinLevel, tttCPU.cpuMaxLevel, tttCPU.cpuLevel); // Initializes cpuLevelSlider (gives it the minimum value, maximum value, and starting value)
		cpuLevelSlider.setPreferredSize(new Dimension(700, 300)); //Sets the preferred size of cpuLevelSlider
		cpuLevelSlider.setMinorTickSpacing(1); //Sets the minor tick spacing of cpuLevelSlider
		cpuLevelSlider.setMajorTickSpacing(1); //Sets the major tick spacing of cpuLevelSlider
		cpuLevelSlider.addChangeListener(this); //Adds a change listener to the slider
		cpuLevelSlider.setPaintTicks(true); //Makes the slider have ticks
		cpuLevelSlider.setPaintLabels(true); //Makes the slider have labels
		cpuLevelSlider.setOpaque(false); //Makes the slider not opaque
		cpuLevelSlider.setFocusable(false); //Makes the slider not focusable
		cpuLevelSlider.setFont(new Font("Lucida Blackletter", Font.BOLD, 16)); //Sets the font of cpuLevelSlider
		
		sliderLabel.setPreferredSize(new Dimension(700, 50)); //* Sets the preferred size of sliderLabel
		sliderLabel.setFont(new Font("Lucida Blackletter", Font.PLAIN, 60)); //Sets the font of sliderLabel
		sliderLabel.setHorizontalAlignment(JLabel.CENTER); //Sets the horizontal alignment of sliderLabel
		
		sliderPanel.setOpaque(false); //Makes sliderPanel not opaque
		sliderPanel.add(sliderLabel); //Adds sliderLabel to sliderPanel
		sliderPanel.add(cpuLevelSlider); //Adds cpuLevelSlider to sliderPanel
		
		exitButton.setOpaque(true); //Sets the exitButton to be opaque
		exitButton.setBackground(Color.RED); //Sets the color of exitButton to red
		exitButton.setFont(new Font("Lucida Blackletter", Font.PLAIN, 30)); //Sets the font of exitButton
		exitButton.setFocusable(false); //Makes exitButton not focusable
		exitButton.setPreferredSize(new Dimension(300, 100)); //Sets the preferred size of exitButton
		exitButton.addActionListener(this); //Adds an action listener to exitButton
		
		exitPanel.setOpaque(false); //Makes exitPanel not opaque
		exitPanel.add(exitButton); //Adds exitButton to exitPanel
		
		
		pausePanel.add(pauseLabel, BorderLayout.NORTH); //Adds pauseLabel to pausePanel
		pausePanel.add(sliderPanel, BorderLayout.CENTER); //Adds sliderPanel to pausePanel
		pausePanel.add(exitPanel, BorderLayout.SOUTH); //Adds exitPanel to pausePanel
		
	}
	
	public void togglePause() { //Function that will toggle between pausing and unpausing (changing back and forth between the pause screen and the game screen)
		
		pause = !pause; //Makes pause the opposite of what it was before
		
		if (pause) { //If the game is now paused, change the panel to the pause panel
			
			tttGUI.changePanel("pause");
			
			//Checks to see if the game is in CPU Mode or not (will not show the slider if in 2 Player Mode)
			if (tttLogic.cpuMode) {
				
				sliderPanel.setVisible(true);
				
			}
			else {
				
				sliderPanel.setVisible(false);
				
			}
			
		}
		
		//If the game is no longer paused, switch back to the game
		else {
			
			tttGUI.changePanel("game");
			
		}
		
	}
	
	public void exitToTitle() { //Function that exits back to the title screen and enables pauseDisabled (so the game isn't paused on the title screen) while making pause false so that the game is no longer paused
		
		tttGUI.changePanel("title");
		pauseDisabled = true;
		pause = false;
		
	}
	
	public void keyPressed(KeyEvent e) { //Implementation of the keyPressed function from keyListener (will trigger if a key is pressed, but only responds to the escape key)
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { //If the escape key is pressed this will trigger
			
			if (!pauseDisabled) { //If pausing is not disabled, then toggle the pause (either pause or unpause depending on the value of pause)
				
				togglePause();
				
			}
			
		}
		
	}

	public void keyReleased(KeyEvent e) {} //Useless function for this class, only here because it is one of the functions from keyListener that must be implemented

	public void keyTyped(KeyEvent e) {} //Useless function for this class, only here because it is one of the functions from keyListener that must be implemented

	public void actionPerformed(ActionEvent e) { //ActionListener for the exitButton (so if the game is on the pause screen, it will exit to title if exitButton is pressed
		
		if (pause) {
			
			exitToTitle();
			
		}
		
	}

	public void stateChanged(ChangeEvent e) { //State listener for the slider (listens to when the slider changes)
		
		JSlider source = (JSlider) e.getSource(); //Gets the source from the ChangeEvent
		
	    if (!source.getValueIsAdjusting()) { //If the value is not still adjusting, then set the cpuLevel to the value and format cpuFile
	    	
	        tttCPU.cpuLevel = (int)source.getValue();
	        tttCPU.formatCPUFile();
	        
	    }
		
	}

}
