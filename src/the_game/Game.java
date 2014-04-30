package the_game;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Game implements Runnable {

	private static final int WINDOW_MIN_WIDTH = 900;
	private static final int WINDOW_MIN_HEIGHT = 700;
	public static ChessBoard board;
	
	@Override
	public void run() {
		final JFrame topLevelFrame = new JFrame("TOP LEVEL FRAME");
		
		// Panel containing ChessBoard and GameInfoPanel
		final JPanel topLevelContentPanel = new JPanel();
		board = new ChessBoard();
		topLevelContentPanel.add(board);
		topLevelContentPanel.add(new GameInfoPanel());
		
		
		// Initialize ChessBoard
		try {
			board.initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Layout organizing GameHeaderPanel and topLevelContentPanel
		topLevelFrame.setLayout(new BoxLayout(topLevelFrame.getContentPane(), BoxLayout.Y_AXIS));	
		topLevelFrame.add(new GameHeaderPanel());
		topLevelFrame.add(topLevelContentPanel);
		
		// Set minimum frame size
		topLevelFrame.setMinimumSize(new Dimension(WINDOW_MIN_WIDTH,WINDOW_MIN_HEIGHT));
		
		// Put the frame on the screen
		topLevelFrame.pack();
		topLevelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topLevelFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}

}
