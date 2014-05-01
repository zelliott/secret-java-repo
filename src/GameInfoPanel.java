

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameInfoPanel extends JPanel {
	
	static JLabel whoseTurn = new JLabel("White's Turn");
	//static JLabel info = new JLabel("General info");
	static JLabel inCheckWhite = new JLabel("White is not in check");
	static JLabel inCheckBlack = new JLabel("Black is not in check");
	static JLabel gameStatus = new JLabel("Running normally");
	
	public GameInfoPanel() {
		whoseTurn.setFont(new Font("Helvetica", Font.BOLD, 18));
		inCheckWhite.setFont(new Font("Helvetica", Font.PLAIN, 16));
		inCheckWhite.setBorder(new EmptyBorder(0,0,10,0));
		inCheckBlack.setFont(new Font("Helvetica", Font.PLAIN, 16));
		inCheckBlack.setBorder(new EmptyBorder(0,0,10,0));
		gameStatus.setFont(new Font("Helvetica", Font.PLAIN, 16));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(whoseTurn);
		add(new JSeparator());
		//add(info);
		add(inCheckWhite);
		add(inCheckBlack);
		add(gameStatus);
	}
}
