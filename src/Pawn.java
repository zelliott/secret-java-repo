

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

public class Pawn extends Piece {
	
	public Pawn(TeamColor tc) throws IOException {
		super(tc, PieceType.PAWN);
	}
	
	@Override
	public ArrayList<int[]> getLegalMoves(int[] position) {
		ArrayList<int[]> listOfLegalMoves = new ArrayList<int[]>();
		
		// Add moves
		if(super.getTeamColor() == TeamColor.WHITE) {
			// Save possible move locations
			int[] positionFrontOfPawn = new int[]{position[0], position[1]-1};
			int[] positionTwoMovesFrontOfPawn = new int[]{position[0], position[1]-2};
			int[] positionLeftOfPawn = new int[]{position[0]-1, position[1]-1};
			int[] positionRightOfPawn = new int[]{position[0]+1, position[1]-1};
			
			// If pawn is on the first row
			if(position[1]==7) {
				// If there is NOT a piece on positionFrontOfPawn
				if(!(ChessBoard.hasSquare(positionFrontOfPawn) && (ChessBoard.getSquare(positionFrontOfPawn)).hasPiece())) {
					listOfLegalMoves.add(positionFrontOfPawn);
				}
				// If there is NOT a piece on positionTwoMovesFrontOfPawn
				// or positionFrontOfPawn
				if(!(ChessBoard.hasSquare(positionTwoMovesFrontOfPawn) && (ChessBoard.getSquare(positionTwoMovesFrontOfPawn)).hasPiece()) &&
				   !(ChessBoard.hasSquare(positionFrontOfPawn) && (ChessBoard.getSquare(positionFrontOfPawn)).hasPiece())) {
					listOfLegalMoves.add(positionTwoMovesFrontOfPawn);
				}
			} else {
				// If there is NOT a piece on positionFrontOfPawn
				if(!(ChessBoard.hasSquare(positionFrontOfPawn) && (ChessBoard.getSquare(positionFrontOfPawn)).hasPiece())) {
					listOfLegalMoves.add(positionFrontOfPawn);
				}
			}
			
			// If there IS a piece on the positionLeftOfPawn
			if((ChessBoard.hasSquare(positionLeftOfPawn) && (ChessBoard.getSquare(positionLeftOfPawn)).hasPiece())) {
				listOfLegalMoves.add(positionLeftOfPawn);
				
			}// If there IS a piece on the positionRightOfPawn
			if((ChessBoard.hasSquare(positionRightOfPawn) && (ChessBoard.getSquare(positionRightOfPawn)).hasPiece())) {
				listOfLegalMoves.add(positionRightOfPawn);
			}
		} else { // TeamColor.BLACK
			// Save possible move locations
			int[] positionFrontOfPawn = new int[]{position[0], position[1]+1};
			int[] positionTwoMovesFrontOfPawn = new int[]{position[0], position[1]+2};
			int[] positionLeftOfPawn = new int[]{position[0]-1, position[1]+1};
			int[] positionRightOfPawn = new int[]{position[0]+1, position[1]+1};
			
			// If pawn is on the first row
			if(position[1]==2) {
				// If there is NOT a piece on positionFrontOfPawn
				if(!(ChessBoard.hasSquare(positionFrontOfPawn) && (ChessBoard.getSquare(positionFrontOfPawn)).hasPiece())) {
					listOfLegalMoves.add(positionFrontOfPawn);
				}
				// If there is NOT a piece on positionTwoMovesFrontOfPawn
				// or positionFrontOfPawn
				if(!(ChessBoard.hasSquare(positionTwoMovesFrontOfPawn) && (ChessBoard.getSquare(positionTwoMovesFrontOfPawn)).hasPiece()) &&
				   !(ChessBoard.hasSquare(positionFrontOfPawn) && (ChessBoard.getSquare(positionFrontOfPawn)).hasPiece())) {
					listOfLegalMoves.add(positionTwoMovesFrontOfPawn);
				}
			} else {
				// If there is NOT a piece on positionFrontOfPawn
				if(!(ChessBoard.hasSquare(positionFrontOfPawn) && (ChessBoard.getSquare(positionFrontOfPawn)).hasPiece())) {
					listOfLegalMoves.add(positionFrontOfPawn);
				}
			}
			
			// If there IS a piece on the positionLeftOfPawn
			if((ChessBoard.hasSquare(positionLeftOfPawn) && (ChessBoard.getSquare(positionLeftOfPawn)).hasPiece())) {
				listOfLegalMoves.add(positionLeftOfPawn);
				
			}// If there IS a piece on the positionRightOfPawn
			if((ChessBoard.hasSquare(positionRightOfPawn) && (ChessBoard.getSquare(positionRightOfPawn)).hasPiece())) {
				listOfLegalMoves.add(positionRightOfPawn);
			}
		}
		
		return super.cleanLegalMoves(listOfLegalMoves);
	}
	

}