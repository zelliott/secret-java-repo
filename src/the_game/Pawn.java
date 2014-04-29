package the_game;

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

import static the_game.PieceType.*;
import static the_game.TeamColor.*;

public class Pawn extends Piece {
	
	public Pawn(TeamColor tc) throws IOException {
		super(tc, PAWN);
	}
	
	@Override
	public ArrayList<int[]> getLegalMoves(int[] position) {
		ArrayList<int[]> listOfLegalMoves = new ArrayList<int[]>();
		
		// Add moves
		if(super.getTeamColor() == TeamColor.WHITE) {
			// Move up 1 or 2 depending on row
			if(position[1]==7) {
				listOfLegalMoves.add(new int[]{position[0], position[1]-2});
			}
			listOfLegalMoves.add(new int[]{position[0], position[1]-1});
			
			// Check to see if diagonal capturing is allowed
			
		} else { // Must be white or black
			if(position[1]==2) {
				listOfLegalMoves.add(new int[]{position[0], position[1]+2});
			}
			listOfLegalMoves.add(new int[]{position[0], position[1]+1});
		}
		
		// Remove moves if they are illegal
		for(int[] move : listOfLegalMoves) {
			for(Square s : ChessBoard.BOARD_SQUARES) {
				if(Arrays.equals(s.getPosition(), move)) {
					if(s.hasPiece()) {
						boolean yourPiece = ((s.getPiece()).getTeamColor()).equals(ChessBoard.getTurn());
						if(!yourPiece) {
							// Or add functionality for diagonal capturing here!
						}
					}
				}
			}
		}
		
		return listOfLegalMoves;
	}
	

}