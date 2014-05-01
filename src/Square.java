

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
	
	// Whether or not a team protects this square
	// Easier to work with when public
	public boolean protectedByWhite;
	public boolean protectedByBlack;
	
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
		try {
			return getIcon() != null;
		}  catch(NullPointerException e) {
			return false;
		}
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
	
	// Moves the piece to this square from the Square s
	public void movePiece(Square s) {
		setIcon(s.getPiece());
		s.removePiece();
		
		// Update the positions/potential positions of all pieces
		ChessBoard.updatePositions();
	}
	
	public void enPassant(int[] startPos, int[] targetPos) {
		// Not implemented
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
				
				// Set protected squares
				ChessBoard.setProtectedSquares();
				
				// Color/focus the clicked square/piece
				setTempBackgroundColor(Color.ORANGE);
				getPiece().setFocus(true);
				
				// Create ArrayList of legalMoves and color them
				ArrayList<int[]> legalMoves = getPiece().getLegalMoves(STORED_POSITION);
				String printPossibleMoves;
				StringBuilder sb = new StringBuilder();
				for(int[] move : legalMoves) {
					for(Square s : ChessBoard.BOARD_SQUARES) {
						if(Arrays.equals(s.getPosition(), move)) {
							s.setTempBackgroundColor(Color.ORANGE);
							
							// TESTING 
							// STORING POSSIBLE MOVES AS STRINGS
							
							String x = String.valueOf(move[0]);
							String y = String.valueOf(move[1]);
							
							sb.append(x);
							sb.append(", ");
							sb.append(y);
							sb.append("; ");
						}
					}
				}
				printPossibleMoves = sb.toString();
				
				
				// CLICKING ON A PIECE RETURNS IT POSITION PERFECTLY
				String piecePosition = String.valueOf((getPiece().getPosition())[0]) + ", " + 
						       String.valueOf((getPiece().getPosition())[1]);
				
				//GameInfoPanel.info.setText(printPossibleMoves + "  |||   " + piecePosition);
				
			}
			
		// If a square is clicked that IS colored...
		} else {
			for(Square s : ChessBoard.BOARD_SQUARES) {
				if(s.hasPiece() && (s.getPiece()).getFocus()) {
					
					// Check to make sure that the target square and current
					// square are not the same
					if(!this.equals(s)) {
						Piece savedPiece = null;
						if(hasPiece()) {
							savedPiece = getPiece();
						}
						
						// enPassant
						// Not implemented
						//if(getPiece().getPieceType().equals(PieceType.PAWN)) {
						//	enPassant(s.getPosition(), STORED_POSITION);
						//}
						
						
						// Castling check
						if(s.hasPiece() && s.getPiece().getPieceType().equals(PieceType.KING)) {
							((King)s.getPiece()).canNotCastle();
						}
						if(s.hasPiece() && s.getPiece().getPieceType().equals(PieceType.ROOK)) {
							((Rook)s.getPiece()).canNotCastle();
						}
						
						// Move rook when castling
						Piece castlingSave = null;
						
						if(s.hasPiece() && s.getPiece().getPieceType().equals(PieceType.KING) &&
						   (s.getPosition()[0] - getPosition()[0])==-2 &&
						   s.getPiece().getTeamColor().equals(TeamColor.WHITE)) {
							castlingSave = ChessBoard.getSquare(new int[]{6,8}).getPiece();
							ChessBoard.getSquare(new int[]{6,8}).setIcon(ChessBoard.getSquare(new int[]{8,8}).getPiece());
							ChessBoard.getSquare(new int[]{8,8}).removePiece();
						} 
						if(s.hasPiece() && s.getPiece().getPieceType().equals(PieceType.KING) &&
						   (s.getPosition()[0] - getPosition()[0])==2 &&
						   s.getPiece().getTeamColor().equals(TeamColor.WHITE)) {
							castlingSave = ChessBoard.getSquare(new int[]{4,8}).getPiece();
							ChessBoard.getSquare(new int[]{4,8}).setIcon(ChessBoard.getSquare(new int[]{1,8}).getPiece());
							ChessBoard.getSquare(new int[]{1,8}).removePiece();
						}
						if(s.hasPiece() && s.getPiece().getPieceType().equals(PieceType.KING) &&
						   (s.getPosition()[0] - getPosition()[0])==-2 &&
						   s.getPiece().getTeamColor().equals(TeamColor.BLACK)) {
							castlingSave = ChessBoard.getSquare(new int[]{6,1}).getPiece();
							ChessBoard.getSquare(new int[]{6,1}).setIcon(ChessBoard.getSquare(new int[]{8,1}).getPiece());
							ChessBoard.getSquare(new int[]{8,1}).removePiece();
						}
						if(s.hasPiece() && s.getPiece().getPieceType().equals(PieceType.KING) &&
						   (s.getPosition()[0] - getPosition()[0])==2 &&
						   s.getPiece().getTeamColor().equals(TeamColor.BLACK)) {
							castlingSave = ChessBoard.getSquare(new int[]{4,1}).getPiece();
							ChessBoard.getSquare(new int[]{4,1}).setIcon(ChessBoard.getSquare(new int[]{1,1}).getPiece());
							ChessBoard.getSquare(new int[]{1,1}).removePiece();
						}
						
						// Move piece FROM Square s
						if(castlingSave != null) {
							setIcon(castlingSave);
						} else {
							movePiece(s);
						}
						
						
						ChessBoard.setProtectedSquares();
						
						// Test for check
						if(ChessBoard.testCheckWhite() && ChessBoard.getTurn().equals(TeamColor.WHITE)) {
							// Undo move
							s.movePiece(ChessBoard.getSquare(STORED_POSITION));
							if(savedPiece != null) {
								setIcon(savedPiece);
							}
							GameInfoPanel.gameStatus.setText("Illegal move, white in check");
						} else if(ChessBoard.testCheckBlack() && ChessBoard.getTurn().equals(TeamColor.WHITE)) {
							
							// Test to see if there is CheckMate
							if(ChessBoard.testCheckMateBlack()) {
								GameInfoPanel.gameStatus.setText("Checkmate!");
							} else {
								// Move is okay, black is now in check
								GameInfoPanel.gameStatus.setText("Not checkmate");
							}
							// Switch players' turn
							ChessBoard.switchTurn();
						} else if(ChessBoard.testCheckBlack() && ChessBoard.getTurn().equals(TeamColor.BLACK)) {
							// Undo move
							s.movePiece(ChessBoard.getSquare(STORED_POSITION));
							if(savedPiece!=null) {
								setIcon(savedPiece);
							}
							GameInfoPanel.gameStatus.setText("Illegal move, black in check");
						} else if(ChessBoard.testCheckWhite() && ChessBoard.getTurn().equals(TeamColor.BLACK)) {
							
							// Test to see if there is CheckMate
							if(ChessBoard.testCheckMateWhite()) {
								GameInfoPanel.gameStatus.setText("Checkmate!");
							} else {
								// Move is okay, white is now in check
								GameInfoPanel.gameStatus.setText("Not checkmate");
							}
							
							// Switch players' turn
							ChessBoard.switchTurn();
						} else {
							// Move is okay, nothing happened
							GameInfoPanel.gameStatus.setText("No one in check");
							// Switch players' turn
							ChessBoard.switchTurn();
						}
					}
				}
			}
			
			// Clear all color and focus
			ChessBoard.clearFocus();
			
			ChessBoard.setProtectedSquares();
		}
	}
	
	public int[] getPosition() {
		return STORED_POSITION;
	}
	
	
}
