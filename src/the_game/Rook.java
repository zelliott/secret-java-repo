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

public class Rook extends Piece {
	
	private BufferedImage image;
	
	public Rook(TeamColor tc) throws IOException {
		super(tc, ROOK);
	}

	@Override
	public ArrayList<int[]> getLegalMoves(int[] position) {
		ArrayList<int[]> listOfLegalMoves = new ArrayList<int[]>();
		
		// Legal moves for rook
		for(int i=1; i<8; i++) {
			int x = position[0];
			int y = position[1]-i;
			if((ChessBoard.getSquare(new int[]{x,y})).hasPiece()) {
				break;
			} else {
				listOfLegalMoves.add(new int[]{x,y});
			}
		}
		for(int i=1; i<8; i++) {
			int x = position[0];
			int y = position[1]+i;
			if((ChessBoard.getSquare(new int[]{x,y})).hasPiece()) {
				break;
			} else {
				listOfLegalMoves.add(new int[]{x,y});
			}
		}
		for(int i=1; i<8; i++) {
			int x = position[0]-i;
			int y = position[1];
			if((ChessBoard.getSquare(new int[]{x,y})).hasPiece()) {
				break;
			} else {
				listOfLegalMoves.add(new int[]{x,y});
			}
		}
		for(int i=1; i<8; i++) {
			int x = position[0]+i;
			int y = position[1];
			if((ChessBoard.getSquare(new int[]{x,y})).hasPiece()) {
				break;
			} else {
				listOfLegalMoves.add(new int[]{x,y});
			}
		}
		
		return listOfLegalMoves;
	}

}
