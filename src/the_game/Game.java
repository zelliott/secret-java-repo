package the_game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game implements Runnable {

	@Override
	public void run() {
		final JFrame topLevelFrame = new JFrame("TOP LEVEL FRAME");
		
		topLevelFrame.add(new ChessBoard());
		
		// Put the frame on the screen
		topLevelFrame.pack();
		topLevelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topLevelFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}

}
