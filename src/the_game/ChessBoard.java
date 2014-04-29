package the_game;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import static the_game.TeamColor.*;


public class ChessBoard extends JPanel {
	
	// Not sure if okay that this is public
	// Use this ArrayList to access the squares in the board
	public static final ArrayList<Square> BOARD_SQUARES = new ArrayList<Square>();
	
	private static final int NUM_ROW = 9;
	private static final int NUM_COL = 9;
	private static final GridLayout boardLayout = new GridLayout(NUM_ROW,NUM_COL);
	public static final JPanel boardPanel = new JPanel();
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
			    	Dimension boardLabelDimension = new Dimension((new Square(Color.WHITE, new int[]{0,0})).getSquareDimension());
			    	boardLabel.setMinimumSize(boardLabelDimension);
			    	boardLabel.setPreferredSize(boardLabelDimension);
			    	boardLabel.setMaximumSize(boardLabelDimension);
		    		boardPanel.add(boardLabel);
		    	} else if(shouldPaintSquareWhite) {
		    		Square square = new Square(Color.WHITE, new int[]{col,row});
		    		BOARD_SQUARES.add(square);
		    		boardPanel.add(square);
		    	} else {
		    		Square square = new Square(Color.GRAY, new int[]{col,row});
		    		BOARD_SQUARES.add(square);
		    		boardPanel.add(square);
		    	}
		    }
		}
		add(boardPanel);
	}
	
	public void initialize() throws IOException {
		int count = 0;
		for(Square s : BOARD_SQUARES) {
			if(count==0 || count==7) {
				s.setIcon(new Rook(BLACK));
			} else if(count==1 || count==6) {
				s.setIcon(new Knight(BLACK));
			} else if(count==2 || count==5) {
				s.setIcon(new Bishop(BLACK));
			} else if(count==3) {
				s.setIcon(new Queen(BLACK));
			} else if(count==4) {
				s.setIcon(new King(BLACK));
			} else if(count>=8 && count<=15) {
				s.setIcon(new Pawn(BLACK));
			} else if(count>=48 && count<=55) {
				s.setIcon(new Pawn(WHITE));
			} else if(count==56 || count==63) {
				s.setIcon(new Rook(WHITE));
			} else if(count==57 || count==62) {
				s.setIcon(new Knight(WHITE));
			} else if(count==58 || count==61) {
				s.setIcon(new Bishop(WHITE));
			} else if(count==59) {
				s.setIcon(new Queen(WHITE));
			} else if(count==60) {
				s.setIcon(new King(WHITE));
			} else {
				// Don't add a piece
			}
			count++;
		}
	}
	
	// Given the position of a square, return it
	// I hate returning null
	// Also not currently using
	public Square getSquare(int[] position) {
		for(Square s : BOARD_SQUARES) {
			if((s.getPosition()).equals(position)) {
				return s;
			}
		}
		return null;
	}
}
