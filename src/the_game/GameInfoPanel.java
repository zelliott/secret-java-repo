package the_game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameInfoPanel extends JPanel {
	
	static JLabel whoseTurn = new JLabel("White's Turn");
	static JLabel info = new JLabel("General info");
	static JLabel inCheck = new JLabel("Not in check");
	
	public GameInfoPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(whoseTurn);
		add(info);
		add(inCheck);
	}
}
