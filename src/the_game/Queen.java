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

public class Queen extends Piece {
	
	private BufferedImage image;
	
	public Queen(TeamColor tc) throws IOException {
		super(tc, QUEEN);
	}

	@Override
	public ArrayList<int[]> getLegalMoves(int[]position) {
		ArrayList<int[]> listOfLegalMoves = new ArrayList<int[]>();
		
		// Legal moves for queen
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
		for(int i=1; i<8; i++) {
			listOfLegalMoves.add(new int[]{position[0], position[1]-i});
		}
		for(int i=1; i<8; i++) {
			listOfLegalMoves.add(new int[]{position[0], position[1]+i});
		}
		for(int i=1; i<8; i++) {
			listOfLegalMoves.add(new int[]{position[0]-i, position[1]});
		}
		for(int i=1; i<8; i++) {
			listOfLegalMoves.add(new int[]{position[0]+i, position[1]});
		}
		
		return listOfLegalMoves;
	}

}