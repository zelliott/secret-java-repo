

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class GameHeaderPanel extends JPanel {
	
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenuItem newGame = new JMenuItem("New Game");
	private final JMenuItem instructions = new JMenuItem("Instructions");
	
	public GameHeaderPanel() {
		newGame.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					Game.board.reset();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		instructions.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Game.board.showInstructions();
			}
		});
		
		menuBar.add(newGame);
		menuBar.add(instructions);
		add(menuBar);
	}
}
