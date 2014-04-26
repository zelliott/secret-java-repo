package the_game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import static the_game.TeamColor.*;


public abstract class Piece extends ImageIcon {
	
	private BufferedImage image;
	private PieceType storedPieceType;
	
	public Piece(TeamColor tc, PieceType p) throws IOException {
		
		// Store the PieceType
		storedPieceType = p;
		
		String pieceTypeName = "";
		
		switch (p) {
		case ROOK:
			pieceTypeName = "rook";
			break;
		case KNIGHT:
			pieceTypeName = "knight";
			break;
			
		case BISHOP:
			pieceTypeName = "bishop";
			break;
		
		case QUEEN:
			pieceTypeName = "queen";
			break;
			
		case KING:
			pieceTypeName = "king";
			break;
			
		case PAWN:
			pieceTypeName = "pawn";
			break;
			
		default: 
			// Invalid PieceType
			break;
		}
		if(pieceTypeName.equals("")) {
			// This should never happen
			// If it does it means that somehow no PieceType was assigned
		} else {
			if(tc.equals(BLACK)) {
				image = ImageIO.read(new File("b_" + pieceTypeName + ".png"));
			} else if(tc.equals(WHITE)) {
				image = ImageIO.read(new File("w_" + pieceTypeName + ".png"));
			} else {
				// Not a color
			}
			setImage(image);
		}
	}
	
	public PieceType getPieceType() {
		return storedPieceType;
	}
}
