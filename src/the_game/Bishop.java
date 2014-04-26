package the_game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import static the_game.PieceType.*;

public class Bishop extends Piece {
	
	private BufferedImage image;
	
	public Bishop(String color) throws IOException {
		super(color, BISHOP);
	}

}