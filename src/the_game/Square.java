package the_game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Square extends JButton {
	
	private static final int MIN_SQUARE_HEIGHT = 70;
	private static final int MIN_SQUARE_WIDTH = 70;
	private final Color BACKGROUND_COLOR;
	private final int[] STORED_POSITION;
	
	public Square(Color c, int[] position) {
		// Set visual stuff
		setPreferredSize(new Dimension(MIN_SQUARE_WIDTH, MIN_SQUARE_HEIGHT));
        setOpaque(true);
        setBorderPainted(false);
        setBackground(c);
        
        // Store the background-color of the square
        BACKGROUND_COLOR = c;
        
        // Store the position of the square
        // This is not perfect, later on change this from array to a better data type
        STORED_POSITION = position;
        
        addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		squareClicked();
        	}
        });
	}
	
	public Dimension getSquareDimension() {
		return new Dimension(MIN_SQUARE_HEIGHT, MIN_SQUARE_WIDTH);
	}
	
	public boolean hasPiece() {
		return getIcon() != null;
	}
	
	public Piece getPiece() {
		if(hasPiece()) {
			// Is this okay to cast to Piece?
			return (Piece)getIcon();
		} else {
			// There is no piece, throw an error or something
			return null;
		}
	}
	
	
	public Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}
	
	public void squareClicked() {
		// Un-color/focus all other squares/pieces
		for(Square s : ChessBoard.BOARD_SQUARES) {
			s.setBackground((s).getBackgroundColor());
			if(s.hasPiece()) {
				s.getPiece().setFocus(false);
			}
		}
		if(hasPiece()) {
			// Color/focus the clicked square/piece
			setBackground(Color.ORANGE);
			getPiece().setFocus(true);
			
			// Create ArrayList of legalMoves and color them
			ArrayList<int[]> legalMoves = getPiece().getLegalMoves(STORED_POSITION);
			for(int[] move : legalMoves) {
				for(Square s : ChessBoard.BOARD_SQUARES) {
					if(Arrays.equals(s.getPosition(), move)) {
						s.setBackground(Color.ORANGE);
					}
				}
			}
			
		}
	}
	
	public int[] getPosition() {
		return STORED_POSITION;
	}
	
	
}
