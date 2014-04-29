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
			listOfLegalMoves.add(new int[]{position[0], position[1]-1});
			listOfLegalMoves.add(new int[]{position[0], position[1]-2});
		} else { // Must be white or black
			listOfLegalMoves.add(new int[]{position[0], position[1]+1});
			listOfLegalMoves.add(new int[]{position[0], position[1]+2});
		}
		
		return listOfLegalMoves;
	}
	

}