import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Rook extends Piece {
	
	public boolean canCastle = true;
	
	public Rook(TeamColor tc) throws IOException {
		super(tc, PieceType.ROOK);
	}

	@Override
	public ArrayList<int[]> getLegalMoves(int[] position) {
		ArrayList<int[]> listOfLegalMoves = new ArrayList<int[]>();
		
		// Legal moves for rook
		for(int i=1; i<8; i++) {
			int x = position[0];
			int y = position[1]-i;
			int[] tempPosition = new int[]{x,y};
			if(ChessBoard.hasSquare(tempPosition) && (ChessBoard.getSquare(tempPosition)).hasPiece()) {
				if(!((ChessBoard.getSquare(tempPosition).getPiece()).getPieceType().equals(getTeamColor()))) {
					listOfLegalMoves.add(tempPosition);
				}
				break;
			} else {
				listOfLegalMoves.add(tempPosition);
			}
		}
		for(int i=1; i<8; i++) {
			int x = position[0];
			int y = position[1]+i;
			int[] tempPosition = new int[]{x,y};
			if(ChessBoard.hasSquare(tempPosition) && (ChessBoard.getSquare(tempPosition)).hasPiece()) {
				if(!((ChessBoard.getSquare(tempPosition).getPiece()).getPieceType().equals(getTeamColor()))) {
					listOfLegalMoves.add(tempPosition);
				}
				break;
			} else {
				listOfLegalMoves.add(tempPosition);
			}
		}
		for(int i=1; i<8; i++) {
			int x = position[0]-i;
			int y = position[1];
			int[] tempPosition = new int[]{x,y};
			if(ChessBoard.hasSquare(tempPosition) && (ChessBoard.getSquare(tempPosition)).hasPiece()) {
				if(!((ChessBoard.getSquare(tempPosition).getPiece()).getPieceType().equals(getTeamColor()))) {
					listOfLegalMoves.add(tempPosition);
				}
				break;
			} else {
				listOfLegalMoves.add(tempPosition);
			}
		}
		for(int i=1; i<8; i++) {
			int x = position[0]+i;
			int y = position[1];
			int[] tempPosition = new int[]{x,y};
			if(ChessBoard.hasSquare(tempPosition) && (ChessBoard.getSquare(tempPosition)).hasPiece()) {
				if(!((ChessBoard.getSquare(tempPosition).getPiece()).getPieceType().equals(getTeamColor()))) {
					listOfLegalMoves.add(tempPosition);
				}
				break;
			} else {
				listOfLegalMoves.add(tempPosition);
			}
		}
		
		return super.cleanLegalMoves(listOfLegalMoves);
	}
	
	public void canNotCastle() {
		canCastle = false;
	}
}
