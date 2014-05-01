

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


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
	
	// Controls the turns of the game
	private static TeamColor turn = TeamColor.WHITE;
	
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
	
	public static void initialize() throws IOException {
		int count = 0;
		for(Square s : BOARD_SQUARES) {
			if(count==0 || count==7) {
				Rook rook = new Rook(TeamColor.BLACK);
				s.setIcon(rook);
				rook.setPosition(s.getPosition());
			} else if(count==1 || count==6) {
				Knight knight = new Knight(TeamColor.BLACK);
				s.setIcon(knight);
				knight.setPosition(s.getPosition());
			} else if(count==2 || count==5) {
				Bishop bishop = new Bishop(TeamColor.BLACK);
				s.setIcon(bishop);
				bishop.setPosition(s.getPosition());
			} else if(count==3) {
				Queen queen = new Queen(TeamColor.BLACK);
				s.setIcon(queen);
				queen.setPosition(s.getPosition());
			} else if(count==4) {
				King king = new King(TeamColor.BLACK);
				s.setIcon(king);
				king.setPosition(s.getPosition());
			} else if(count>=8 && count<=15) {
				Pawn pawn = new Pawn(TeamColor.BLACK);
				s.setIcon(pawn);
				pawn.setPosition(s.getPosition());
			} else if(count>=48 && count<=55) {
				Pawn pawn = new Pawn(TeamColor.WHITE);
				s.setIcon(pawn);
				pawn.setPosition(s.getPosition());
			} else if(count==56 || count==63) {
				Rook rook = new Rook(TeamColor.WHITE);
				s.setIcon(rook);
				rook.setPosition(s.getPosition());
			} else if(count==57 || count==62) {
				Knight knight = new Knight(TeamColor.WHITE);
				s.setIcon(knight);
				knight.setPosition(s.getPosition());
			} else if(count==58 || count==61) {
				Bishop bishop = new Bishop(TeamColor.WHITE);
				s.setIcon(bishop);
				bishop.setPosition(s.getPosition());
			} else if(count==59) {
				Queen queen = new Queen(TeamColor.WHITE);
				s.setIcon(queen);
				queen.setPosition(s.getPosition());
			} else if(count==60) {
				King king = new King(TeamColor.WHITE);
				s.setIcon(king);
				king.setPosition(s.getPosition());
			} else {
				// Don't add a piece
			}
			count++;
		}
		updatePositions();
		setProtectedSquares();
	}
	
	public static void setProtectedSquares() {
		// Reset
		for(Square s : BOARD_SQUARES) {
			s.protectedByWhite = false;
			s.protectedByBlack = false;
		}
		
		// Now set protections
		for(Square s : BOARD_SQUARES) {
			if(s.hasPiece() && s.getPiece().getTeamColor().equals(TeamColor.WHITE)) {
				Piece p = s.getPiece();
				for(int[] position : p.getPossibleMoves()) {
					if(hasSquare(position)) {
						getSquare(position).protectedByWhite = true;
					}
				}
			}
		}
		for(Square s : BOARD_SQUARES) {
			if(s.hasPiece() && s.getPiece().getTeamColor().equals(TeamColor.BLACK)) {
				Piece p = s.getPiece();
				for(int[] position : p.getPossibleMoves()) {
					if(hasSquare(position)) {
						getSquare(position).protectedByBlack = true;
					}
				}
			}
		}
		
		// Now set colors
		/*for(Square s : BOARD_SQUARES) {
			if(s.protectedByBlack && s.protectedByWhite) {
				s.setBackground(Color.RED);
			} else if(s.protectedByWhite) {
				s.setBackground(Color.GREEN);
			} else if(s.protectedByBlack) {
				s.setBackground(Color.BLUE);
			} else {
				s.setBackground(Color.PINK);
			}
		}*/
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
	
	// Updates every piece's position and possible
	// moves
	public static void updatePositions() {
		for(Square s1 : ChessBoard.BOARD_SQUARES) {
			if(s1.hasPiece()) {
				(s1.getPiece()).setPosition(s1.getPosition());
				(s1.getPiece()).updatePossibleMoves();
			}
		}
	}
	
	public static TeamColor getTurn() {
		return turn;
	}
	
	public static void switchTurn() {
		if(turn.equals(TeamColor.WHITE)) {
			turn = TeamColor.BLACK;
			GameInfoPanel.whoseTurn.setText("Black's Turn");
		} else {
			turn = TeamColor.WHITE;
			GameInfoPanel.whoseTurn.setText("White's Turn");
		}
	}
	
	public static boolean testCheckWhite() {
		// Get king position
		int[] whiteKingPosition = new int[]{};
		for(Square s : BOARD_SQUARES) {
			Piece p = s.getPiece();
			if(s.hasPiece() && (p.getPieceType()).equals(PieceType.KING)) {
				if((p.getTeamColor()).equals(TeamColor.WHITE)) {
					whiteKingPosition = p.getPosition();
				}
			}
		}
		
		if(getSquare(whiteKingPosition).protectedByBlack) {
			GameInfoPanel.inCheckWhite.setText("White is in check");
			return true;
		} else {
			GameInfoPanel.inCheckWhite.setText("White is not in check");
			return false;
		}
	}
	
	public static boolean testCheckBlack() {
		// Get king position
		int[] blackKingPosition = new int[]{};
		for(Square s : BOARD_SQUARES) {
			Piece p = s.getPiece();
			if(s.hasPiece() && (p.getPieceType()).equals(PieceType.KING)) {
				if((p.getTeamColor()).equals(TeamColor.BLACK)) {
					blackKingPosition = p.getPosition();
				}
			}
		}
		if(getSquare(blackKingPosition).protectedByWhite) {
			GameInfoPanel.inCheckBlack.setText("Black is in check");
			return true;
		} else {
			GameInfoPanel.inCheckBlack.setText("Black is not in check");
			return false;
		}
	}
	
	public static boolean testCheckMateWhite() {
		// Try every possible move
				int i = 0;
				for(Square s : BOARD_SQUARES) {
					Piece p = s.getPiece();
					if(s.hasPiece() && (p.getTeamColor()).equals(TeamColor.WHITE)) {
						if(!p.getPossibleMoves().isEmpty()) {
							for(int[] potentialMove : p.getPossibleMoves()) {
								if(hasSquare(potentialMove)) {
									
									// Completely move piece
									Square targetSquare = getSquare(potentialMove);
									Piece savedPiece = targetSquare.getPiece();
									targetSquare.setIcon(p);
									s.removePiece();
									
									// Update positions/square protections
									updatePositions();
									setProtectedSquares();
									
									// Test if still in check
									if(!testCheckWhite()) {
										i++;
									}
									
									// Undo move
									targetSquare.setIcon(savedPiece);
									s.setIcon(p);
								}
							}
						}
					}
				}
				return (i==0);
	}
	
	public static boolean testCheckMateBlack() {
		// Try every possible move
		int i = 0;
		for(Square s : BOARD_SQUARES) {
			Piece p = s.getPiece();
			if(s.hasPiece() && (p.getTeamColor()).equals(TeamColor.BLACK)) {
				if(!p.getPossibleMoves().isEmpty()) {
					for(int[] potentialMove : p.getPossibleMoves()) {
						if(hasSquare(potentialMove)) {
							
							// Completely move piece
							Square targetSquare = getSquare(potentialMove);
							Piece savedPiece = targetSquare.getPiece();
							targetSquare.setIcon(p);
							s.removePiece();
							
							// Update positions/square protections
							updatePositions();
							setProtectedSquares();
							
							// Test if still in check
							if(!testCheckBlack()) {
								i++;
							}
							
							// Undo move
							targetSquare.setIcon(savedPiece);
							s.setIcon(p);
						}
					}
				}
			}
		}
		return (i==0);
	}
	
	public static void reset() throws IOException {
		for(Square s : BOARD_SQUARES) {
			s.removePiece();
		}
		turn = TeamColor.WHITE;
		initialize();
		clearFocus();
		setProtectedSquares();
		
		GameInfoPanel.whoseTurn.setText("White's Turn");
		GameInfoPanel.inCheckWhite.setText("White is not in check");
		GameInfoPanel.inCheckBlack.setText("Black is not in check");
		GameInfoPanel.gameStatus.setText("Running normally");
	}
	
	public void showInstructions() {
		final JDialog instructions = new JDialog();
		instructions.setMinimumSize(new Dimension(900, 700));
		instructions.setPreferredSize(new Dimension(900, 700));
		instructions.setVisible(true);
		
		// Adding panel for button
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		// Adding instructions
		JLabel paragraph = new JLabel();
		paragraph.setAlignmentY(TOP_ALIGNMENT);
		paragraph.setText("<html><body><h1>GAME INSTRUCTIONS:</h1>"
				+ "<h2>Intro</h2>"
				+ "<p>For my CIS 20 final project, I implemented a basic version of Chess.</p>"
				+ "<h2>Gameplay</h2>"
				+ "<ul>"
				+ "<li>White always moves first (of course)</li>"
				+ "<li>Click on a piece to select it, and then click on one of the 'orange' squares of possible moves of that piece in order to move it there.</li>"
				+ "<li>There is no undo... so you're basically playing with touch move!  :)</li>"
				+ "</ul>"
				+ "<h2>Features</h2>"
				+ "<ul>"
				+ "<li>When you click a piece, potential squares that you may move that piece to turn orange.  Note:  Just because a square is orange doesn't ensure that you can move there.  If you place yourself in check because of a move, the program will recognize that and 'take back' the move.</li>"
				+ "<li>The game keeps track of basic statuses such as 'Check' and 'Checkmate' on the side, as well as whose turn it is.</li>"
				+ "<li>There is a 'New Game' button to quickly create a new game.</li>"
				+ "</ul>"
				+ "<h2>Known Bugs</h2>"
				+ "<ul>"
				+ "<li>When you checkmate the opponent with a piece that is protected by another piece, and the King has the opportunity to capture it, the game won't 'detect' a checkmate, but it won't let you move anywhere.</li>"
				+ "<li>No stalemate detection</li>"
				+ "<li>No en passant</li>"
				+ "<li>No threefold repetition</li>"
				+ "</ul>"
				+ "</body></html>");
		
		
		// Adding a close button
		JButton button = new JButton("Okay");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				instructions.dispose();
			}
		});
		
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel.add(paragraph);
		panel.add(button);
		instructions.add(panel);
		instructions.pack();
	}
}
