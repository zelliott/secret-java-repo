import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece {
	
	private BufferedImage image;
	public boolean canCastle = true;
	
	public King(TeamColor tc) throws IOException {
		super(tc, PieceType.KING);
	}

	@Override
	public ArrayList<int[]> getLegalMoves(int[] position) {
		ArrayList<int[]> listOfLegalMoves = new ArrayList<int[]>();

		// Eight legal moves for king
		listOfLegalMoves.add(new int[]{position[0], position[1]-1});
		listOfLegalMoves.add(new int[]{position[0]-1, position[1]});
		listOfLegalMoves.add(new int[]{position[0], position[1]+1});
		listOfLegalMoves.add(new int[]{position[0]+1, position[1]});
		listOfLegalMoves.add(new int[]{position[0]+1, position[1]+1});
		listOfLegalMoves.add(new int[]{position[0]+1, position[1]-1});
		listOfLegalMoves.add(new int[]{position[0]-1, position[1]+1});
		listOfLegalMoves.add(new int[]{position[0]-1, position[1]-1});
		
		// Castling check
		if(super.getTeamColor() == TeamColor.WHITE) {
			if(canCastle) {
				if(!ChessBoard.getSquare(new int[]{5,8}).protectedByBlack &&
				   !ChessBoard.getSquare(new int[]{6,8}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{6,8}).protectedByBlack &&
				   !ChessBoard.getSquare(new int[]{7,8}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{7,8}).protectedByBlack &&
				   ChessBoard.getSquare(new int[]{8,8}).hasPiece() &&
				   (ChessBoard.getSquare(new int[]{8,8}).getPiece().getPieceType()).equals(PieceType.ROOK) &&
				   ((Rook)ChessBoard.getSquare(new int[]{8,8}).getPiece()).canCastle) {
					listOfLegalMoves.add(new int[]{position[0]+2, position[1]});
				}
				if(!ChessBoard.getSquare(new int[]{5,8}).protectedByBlack &&
				   !ChessBoard.getSquare(new int[]{4,8}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{4,8}).protectedByBlack &&
				   !ChessBoard.getSquare(new int[]{3,8}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{3,8}).protectedByBlack &&
				   !ChessBoard.getSquare(new int[]{2,8}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{2,8}).protectedByBlack &&
				   ChessBoard.getSquare(new int[]{1,8}).hasPiece() &&
				   (ChessBoard.getSquare(new int[]{1,8}).getPiece().getPieceType()).equals(PieceType.ROOK) &&
				   ((Rook)ChessBoard.getSquare(new int[]{1,8}).getPiece()).canCastle) {
					listOfLegalMoves.add(new int[]{position[0]-2, position[1]});
				}
			}
		} else {
			if(canCastle) {
				if(!ChessBoard.getSquare(new int[]{5,1}).protectedByWhite &&
				   !ChessBoard.getSquare(new int[]{6,1}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{6,1}).protectedByWhite &&
				   !ChessBoard.getSquare(new int[]{7,1}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{7,1}).protectedByWhite &&
				   ChessBoard.getSquare(new int[]{8,1}).hasPiece() &&
				   (ChessBoard.getSquare(new int[]{8,1}).getPiece().getPieceType()).equals(PieceType.ROOK) &&
				   ((Rook)ChessBoard.getSquare(new int[]{8,1}).getPiece()).canCastle) {
					listOfLegalMoves.add(new int[]{position[0]+2, position[1]});
				}
				if(!ChessBoard.getSquare(new int[]{5,1}).protectedByWhite &&
				   !ChessBoard.getSquare(new int[]{4,1}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{4,1}).protectedByWhite &&
				   !ChessBoard.getSquare(new int[]{3,1}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{3,1}).protectedByWhite &&
				   !ChessBoard.getSquare(new int[]{2,1}).hasPiece() &&
				   !ChessBoard.getSquare(new int[]{2,1}).protectedByWhite &&
				   ChessBoard.getSquare(new int[]{1,1}).hasPiece() &&
				   (ChessBoard.getSquare(new int[]{1,1}).getPiece().getPieceType()).equals(PieceType.ROOK) &&
				   ((Rook)ChessBoard.getSquare(new int[]{1,1}).getPiece()).canCastle) {
					listOfLegalMoves.add(new int[]{position[0]-2, position[1]});
				}
			}
		}
		
		return super.cleanLegalMoves(listOfLegalMoves);
	}
	
	public void canNotCastle() {
		canCastle = false;
	}

}