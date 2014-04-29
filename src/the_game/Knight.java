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

public class Knight extends Piece {
	
	private BufferedImage image;
	
	public Knight(TeamColor tc) throws IOException {
		super(tc, KNIGHT);
	}

	@Override
	public ArrayList<int[]> getLegalMoves(int[] position) {
		ArrayList<int[]> listOfLegalMoves = new ArrayList<int[]>();
		
		// Legal moves for knight
		listOfLegalMoves.add(new int[]{position[0]+2, position[1]-1});
		listOfLegalMoves.add(new int[]{position[0]+2, position[1]+1});
		listOfLegalMoves.add(new int[]{position[0]-2, position[1]-1});
		listOfLegalMoves.add(new int[]{position[0]-2, position[1]+1});
		listOfLegalMoves.add(new int[]{position[0]-1, position[1]+2});
		listOfLegalMoves.add(new int[]{position[0]+1, position[1]+2});
		listOfLegalMoves.add(new int[]{position[0]-1, position[1]-2});
		listOfLegalMoves.add(new int[]{position[0]+1, position[1]-2});
		
		return listOfLegalMoves;
	}

}