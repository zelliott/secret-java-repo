package the_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameHeaderPanel extends JPanel {
	
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu sampleMenu = new JMenu("SAMPLE MENU");
	
	public GameHeaderPanel() {
		menuBar.add(sampleMenu);
		add(menuBar);
	}
}
