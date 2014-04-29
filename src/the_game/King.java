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

public class King extends Piece {
	
	private BufferedImage image;
	
	public King(TeamColor tc) throws IOException {
		super(tc, KING);
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
			// If King hasn't moved
				// Then check if Either rook hasn't moved
				// Then check to make sure no pieces are blocking
				// Then check to make sure you would not be in check
				// If so, allow king to move 2 to that direction and rook to move
		} else {
			// Likewise for black
		}
		
		return super.cleanLegalMoves(listOfLegalMoves);
	}

}