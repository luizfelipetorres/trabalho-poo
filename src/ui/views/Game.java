package ui.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.FileChooserUI;

import interfaces.ShuffleListener;
import model.Puzzle;
import ui.components.PieceButton;
import util.TypeShuffle;

public class Game {

	private JFrame frame;
	private List<PieceButton> buttons = new ArrayList<>();
	private Puzzle puzzle;

	public static void main(String[] args) {
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
		puzzle = new Puzzle(8, 8, img, TypeShuffle.pairs);
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

		ShuffleListener listener = new ShuffleListener() {
			@Override
			public void updateButtons() {
				buttons.forEach(button -> button.configImg());
			}
		};

		buttons.forEach(e -> panelGame.add(e));

		Thread shuffle = new Thread(() -> {
			puzzle.shuffleTable(listener);
			buttons.forEach(button -> {
				button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						makeMovement(button);
					}
				});
			});
		});
		shuffle.start();
	}

	private File chooseImage() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
		fileChooser.setFileFilter(filter);
		boolean choosed = fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION;
		return choosed ? fileChooser.getSelectedFile() : new File("img//naruto.jpg");
	}

	private void makeMovement(PieceButton button) {
		PieceButton piece = buttons.stream().filter(h -> h.getPiece().isEmpty()).findFirst().get();
		button.getPiece().movement();
		button.configImg();
		piece.configImg();
		if (puzzle.completedPuzzle()) {
			JOptionPane.showMessageDialog(null, "ganhou;)");
		}
	}
}
