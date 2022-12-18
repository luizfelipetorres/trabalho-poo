package ui.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.Puzzle;
import ui.components.PieceButton;
import util.TypeShuffle;

public class Game{

	private JFrame frame;
	private List<PieceButton> buttons = new ArrayList<>();
	private Puzzle puzzle;

	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Game() throws IOException {
		initialize();
	}

	private void initialize() throws IOException {
		File img = new File("img//naruto.jpg"); //chooseImage();
		puzzle = new Puzzle(4, 4, img, TypeShuffle.pairs);
		frame = new JFrame();

		frame.getContentPane().setBackground(new Color(207, 207, 207));
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panelGame = new JPanel();
		panelGame.setBounds(10, 11, 500, 500);
		frame.getContentPane().add(panelGame);

		GridLayout gridLayout = new GridLayout(puzzle.getLINES(), puzzle.getCOLUMNS());
		panelGame.setLayout(gridLayout);

		int whith = panelGame.getWidth() / puzzle.getLINES();
		int height = panelGame.getHeight() / puzzle.getCOLUMNS();
		puzzle.getPieces().forEach(e -> buttons.add(new PieceButton(e, whith, height)));

	}
}
