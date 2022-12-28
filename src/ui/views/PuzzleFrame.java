package ui.views;

import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;
import ui.components.PuzzleBoard;
import ui.components.Stopwatch;

public class PuzzleFrame extends JPanel {

	private static final long serialVersionUID = 1L;

	public PuzzleFrame(Player player) {
		
		this.initialize(player);
	}
	
	public void initialize(Player player) {
		this.setBounds(0, 0, 1175, 670);
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(null);
		
		JLabel lbBgmain = new JLabel("");
		lbBgmain.setIcon(new ImageIcon("img\\bg-main.jpg"));
		lbBgmain.setBounds(645, 0, 430, 640);
		this.add(lbBgmain);
				
		try {
			PuzzleBoard.getInstance().initialize(this, 2);
			Stopwatch.getInstance().initialize(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
