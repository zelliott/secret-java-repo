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

public class Bishop extends Piece {
	
	private BufferedImage image;
	
	public Bishop(TeamColor tc) throws IOException {
		super(tc, BISHOP);
	}

	@Override
	public ArrayList<int[]> getLegalMoves(int[] position) {
		ArrayList<int[]> listOfLegalMoves = new ArrayList<int[]>();
		
		// Legal moves for bishop
		for(int i=1; i<8; i++) {
			listOfLegalMoves.add(new int[]{position[0]-i, position[1]-i});
		}
		for(int i=1; i<8; i++) {
			listOfLegalMoves.add(new int[]{position[0]+i, position[1]+i});
		}
		for(int i=1; i<8; i++) {
			listOfLegalMoves.add(new int[]{position[0]+i, position[1]-i});
		}
		for(int i=1; i<8; i++) {
			listOfLegalMoves.add(new int[]{position[0]-i, position[1]+i});
		}
		
		return listOfLegalMoves;
	}

}