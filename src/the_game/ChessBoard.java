package the_game;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	private static boolean inCheck = false;
	
	// Controls the turns of the game
	private static TeamColor turn = WHITE;
	
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
				Rook rook = new Rook(BLACK);
				s.setIcon(rook);
				rook.setPosition(s.getPosition());
			} else if(count==1 || count==6) {
				Knight knight = new Knight(BLACK);
				s.setIcon(knight);
				knight.setPosition(s.getPosition());
			} else if(count==2 || count==5) {
				Bishop bishop = new Bishop(BLACK);
				s.setIcon(bishop);
				bishop.setPosition(s.getPosition());
			} else if(count==3) {
				Queen queen = new Queen(BLACK);
				s.setIcon(queen);
				queen.setPosition(s.getPosition());
			} else if(count==4) {
				King king = new King(BLACK);
				s.setIcon(king);
				king.setPosition(s.getPosition());
			} else if(count>=8 && count<=15) {
				Pawn pawn = new Pawn(BLACK);
				s.setIcon(pawn);
				pawn.setPosition(s.getPosition());
			} else if(count>=48 && count<=55) {
				Pawn pawn = new Pawn(WHITE);
				s.setIcon(pawn);
				pawn.setPosition(s.getPosition());
			} else if(count==56 || count==63) {
				Rook rook = new Rook(WHITE);
				s.setIcon(rook);
				rook.setPosition(s.getPosition());
			} else if(count==57 || count==62) {
				Knight knight = new Knight(WHITE);
				s.setIcon(knight);
				knight.setPosition(s.getPosition());
			} else if(count==58 || count==61) {
				Bishop bishop = new Bishop(WHITE);
				s.setIcon(bishop);
				bishop.setPosition(s.getPosition());
			} else if(count==59) {
				Queen queen = new Queen(WHITE);
				s.setIcon(queen);
				queen.setPosition(s.getPosition());
			} else if(count==60) {
				King king = new King(WHITE);
				s.setIcon(king);
				king.setPosition(s.getPosition());
			} else {
				// Don't add a piece
			}
			count++;
		}
	}
	
	public static boolean hasSquare(int[] position) {
		for(Square s: BOARD_SQUARES) {
			if(Arrays.equals(s.getPosition(), position)) {
				return true;
			}
		}
		return false;
	}
	
	// Given the position of a square, return it
	// I hate returning null
	public static Square getSquare(int[] position) {
		for(Square s : BOARD_SQUARES) {
			if(Arrays.equals(s.getPosition(), position)) {
				return s;
			}
		}
		return null;
	}
	
	// Clears the focus/coloring of the entire board
	public static void clearFocus() {
		for(Square s : BOARD_SQUARES) {
			s.setTempBackgroundColor((s).getBackgroundColor());
			if(s.hasPiece()) {
				s.getPiece().setFocus(false);
			}
		}
	}
	
	public static TeamColor getTurn() {
		return turn;
	}
	
	public static void switchTurn() {
		if(turn.equals(WHITE)) {
			turn = BLACK;
			GameInfoPanel.whoseTurn.setText("Black's Turn");
		} else {
			turn = WHITE;
			GameInfoPanel.whoseTurn.setText("White's Turn");
		}
	}
	
	public static void testCheck() {
		
	}
	
}
