package the_game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameInfoPanel extends JPanel {
	
	static JLabel whoseTurn = new JLabel("White's Turn");
	static JLabel isCheck = new JLabel("Not in check");
	
	public GameInfoPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(whoseTurn);
		add(isCheck);
	}
}
