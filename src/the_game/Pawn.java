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
		
		// Two legal moves for pawn, up one or up two
		if(super.getTeamColor() == TeamColor.WHITE) {
			if(position[1]==7) {
				listOfLegalMoves.add(new int[]{position[0], position[1]-1});
				listOfLegalMoves.add(new int[]{position[0], position[1]-2});
			} else {
				listOfLegalMoves.add(new int[]{position[0], position[1]-1});
			}
		} else { // Must be white or black
			if(position[1]==2) {
				listOfLegalMoves.add(new int[]{position[0], position[1]+1});
				listOfLegalMoves.add(new int[]{position[0], position[1]+2});
			} else {
				listOfLegalMoves.add(new int[]{position[0], position[1]+1});
			}
		}
		
		// Remove moves if they are illegal
		for(int[] move : listOfLegalMoves) {
			for(Square s : ChessBoard.BOARD_SQUARES) {
				if(Arrays.equals(s.getPosition(), move)) {
					
				}
			}
		}
		
		return listOfLegalMoves;
	}
	

}