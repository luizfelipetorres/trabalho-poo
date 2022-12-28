package ui.views;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;
import ui.components.PuzzleBoard;
import ui.components.Stopwatch;
import util.TypeShuffle;
import javax.swing.JFrame;

public class PuzzleFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private PuzzleBoard puzzleBoard;
	private Stopwatch stopWatch;
	private TypeShuffle typeShuffle;

	public PuzzleFrame(Player player, File image, int size, TypeShuffle typeShuffle) {
		this.typeShuffle = typeShuffle;
		this.puzzleBoard = PuzzleBoard.getInstance();
		this.stopWatch = Stopwatch.getInstance();
		this.initialize(player, image, size);
	}
	
	public void initialize(Player player, File image, int size) {
		this.setBounds(0, 0, 1175, 670);
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(null);
		
		JLabel lbBgmain = new JLabel("");
		lbBgmain.setIcon(new ImageIcon("img\\bg-main.jpg"));
		lbBgmain.setBounds(645, 0, 430, 640);
		this.add(lbBgmain);
				
		try {
			puzzleBoard.initialize(this, size, image, typeShuffle);
			stopWatch.initialize(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
