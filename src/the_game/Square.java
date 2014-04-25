package the_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Square extends JPanel {
	
	private static final int MIN_SQUARE_HEIGHT = 70;
	private static final int MIN_SQUARE_WIDTH = 70;
	
	public Square() {
		setPreferredSize(new Dimension(MIN_SQUARE_WIDTH, MIN_SQUARE_HEIGHT));
	}
	
	public Dimension getSquareDimension() {
		return new Dimension(MIN_SQUARE_HEIGHT, MIN_SQUARE_WIDTH);
	}
}
