package the_game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameInfoPanel extends JPanel {
	
	static JLabel whoseTurn = new JLabel("White's Turn");
	static JLabel info = new JLabel("General info");
	static JLabel inCheckWhite = new JLabel("White is not in check");
	static JLabel inCheckBlack = new JLabel("Black is not in check");
	static JLabel gameStatus = new JLabel("Running normally");
	
	public GameInfoPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(whoseTurn);
		add(info);
		add(inCheckWhite);
		add(inCheckBlack);
		add(gameStatus);
	}
}
