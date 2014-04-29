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
	private Color tempBackgroundColor;
	private final int[] STORED_POSITION;
	
	public Square(Color c, int[] position) {
		// Set visual stuff
		setPreferredSize(new Dimension(MIN_SQUARE_WIDTH, MIN_SQUARE_HEIGHT));
        setOpaque(true);
        setBorderPainted(false);
        setBackground(c);
        
        // Store the background-color of the square
        BACKGROUND_COLOR = c;
        
        // Store the temporary background-color of the square
        tempBackgroundColor = c;
        
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
		return (getIcon() != null);
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
	
	public void removePiece() {
		setIcon(null);
	}
	
	// Moves the piece to this square from the target Square s
	public void movePiece(Square s) {
		setIcon(s.getIcon());
		s.removePiece();
	}
	
	
	public Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}
	
	public void setTempBackgroundColor(Color tempBackgroundColor) {
		setBackground(tempBackgroundColor);
		this.tempBackgroundColor = tempBackgroundColor; 
	}
	
	public void squareClicked() {
		// If a square is clicked that is not colored, 
		// then change focus if there is a piece,
		// or lose focus if there isn't
		if(!(this.tempBackgroundColor.equals(Color.ORANGE))) {
			// Un-color/focus all other squares/pieces
			ChessBoard.clearFocus();
			
			// Tests if the square has a piece, and if so,
			// that that piece is of the same color as the player's
			// whose turn it is
			boolean correctTurn = hasPiece() && (getPiece().getTeamColor()).equals(ChessBoard.getTurn());
			if(hasPiece() && correctTurn) {
				// Color/focus the clicked square/piece
				setTempBackgroundColor(Color.ORANGE);
				getPiece().setFocus(true);
				
				// Create ArrayList of legalMoves and color them
				ArrayList<int[]> legalMoves = getPiece().getLegalMoves(STORED_POSITION);
				for(int[] move : legalMoves) {
					for(Square s : ChessBoard.BOARD_SQUARES) {
						if(Arrays.equals(s.getPosition(), move)) {
							// Remove move if you would capture your own piece
							if(s.hasPiece()) {
								boolean notCapturingYourOwnPiece = !((s.getPiece()).getTeamColor()).equals(ChessBoard.getTurn());
								if(notCapturingYourOwnPiece) {
									s.setTempBackgroundColor(Color.ORANGE);
								}
							} else {
								s.setTempBackgroundColor(Color.ORANGE);
							}
						}
					}
				}
			}
		// If a square is clicked that IS colored...
		} else {
			for(Square s : ChessBoard.BOARD_SQUARES) {
				if(s.hasPiece() && (s.getPiece()).getFocus()) {
					// Check to make sure that the target square and current
					// square are not the same
					if(!this.equals(s)) {
						movePiece(s);
						
						// Switch players' turn
						ChessBoard.switchTurn();
					}
				}
			}
			
			// Clear all color and focus
			ChessBoard.clearFocus();
		}
	}
	
	public int[] getPosition() {
		return STORED_POSITION;
	}
	
	
}
