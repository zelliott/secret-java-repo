package the_game;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class ChessBoard extends JPanel {
	
	private static final int NUM_ROW = 9;
	private static final int NUM_COL = 9;
	private static final GridLayout boardLayout = new GridLayout(NUM_ROW,NUM_COL);
	private static final JPanel boardPanel = new JPanel();
	private static final String[] boardLabels = {" ", "a", "b", "c", "d", "e", "f", "g", "h", 
												 "8", "7", "6", "5", "4", "3", "2", "1"};
	
	public ChessBoard() {
		boardPanel.setLayout(boardLayout);
		
		int count = 0; // Counter that iterates through boardLabels[] and assigns the correct label text
		for (int row = 0; row < NUM_ROW; row++){
		    for (int col = 0; col < NUM_COL; col++){
		    	
		    	boolean edgeOfBoard = (row==0 || col==0); // Condition for being on the top/left edge of the ChessBoard
		    	boolean shouldPaintSquareWhite = ((row + col) % 2 != 1); // Condition for coloring a Square black or white
		    	
		    	if (edgeOfBoard) {
		    		JLabel boardLabel = new JLabel(boardLabels[count], SwingConstants.CENTER);
		    		count++; // Increments variable that counts through boardLabels[]
			    	Dimension boardLabelDimension = new Dimension((new Square()).getSquareDimension());
			    	boardLabel.setMinimumSize(boardLabelDimension);
			    	boardLabel.setPreferredSize(boardLabelDimension);
			    	boardLabel.setMaximumSize(boardLabelDimension);
		    		boardPanel.add(boardLabel);
		    	} else if(shouldPaintSquareWhite) {
		    		JButton square = new Square();
		    		square.setBackground(Color.WHITE);
		    		boardPanel.add(square);
		    	} else {
		    		JButton square = new Square();
		    		square.setBackground(Color.GRAY);
		    		boardPanel.add(square);
		    	}
		    }
		}
		add(boardPanel);
	}
	
	public void initialize() throws IOException {
		int count = 0;
		for(Component c : boardPanel.getComponents()) {
			if(c instanceof Square) {
				if(count==0 || count==7) {
					((Square)c).setIcon(new Rook("black"));
				} else if(count==1 || count==6) {
					((Square)c).setIcon(new Knight("black"));
				} else if(count==2 || count==5) {
					((Square)c).setIcon(new Bishop("black"));
				} else if(count==3) {
					((Square)c).setIcon(new Queen("black"));
				} else if(count==4) {
					((Square)c).setIcon(new King("black"));
				} else if(count>=8 && count<=15) {
					((Square)c).setIcon(new Pawn("black"));
				} else {
					// Don't add a piece
				}
				count++;
			}
		}
	}
}
