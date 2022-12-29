package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import interfaces.ShuffleListener;
import model.Puzzle;
import ui.views.PuzzleFrame;
import util.TypeShuffle;

public class PuzzleBoard extends Component {

	private static final long serialVersionUID = 1L;
	private List<PieceButton> buttons;
	private Puzzle puzzle;
	private static PuzzleBoard instance;
	
	public static PuzzleBoard getInstance() {
		if (instance == null) 
			instance = new PuzzleBoard();
		return instance;
	}

	private PuzzleBoard() {
		this.buttons = new ArrayList<PieceButton>();
	}

	public Component initialize(PuzzleFrame puzzleFrame, int size, File image, TypeShuffle typeShuffle) throws IOException {
		reset();

		puzzle = new Puzzle(size, size, image, typeShuffle);

		JPanel panelPuzzle = new JPanel();
		panelPuzzle.setBorder(new LineBorder(new Color(0, 0, 128)));
		panelPuzzle.setBounds(10, 80, 630, 560);
		panelPuzzle.setLayout(new GridLayout(puzzle.getLINES(), puzzle.getCOLUMNS()));
		puzzleFrame.add(panelPuzzle);

		int width = panelPuzzle.getWidth() / puzzle.getLINES();
		int height = panelPuzzle.getHeight() / puzzle.getCOLUMNS();
		puzzle.getPieces().forEach(e -> buttons.add(new PieceButton(e, width, height)));

		ShuffleListener listener = new ShuffleListener() {
			@Override
			public void updateButtons() {
				buttons.forEach(button -> button.configImg());
			}
		};

		Thread shuffle = new Thread(() -> {
			puzzle.shuffleTable(listener);
		});

		shuffle.start();

		buttons.forEach(button -> {
			panelPuzzle.add(button);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (Stopwatch.getInstance().isRunning()) {
						makeMovement(button);
						button.setBorder(null);
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (button.getPiece().verifyMovement() && Stopwatch.getInstance().isRunning()) {
						button.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (Stopwatch.getInstance().isRunning()) {
						button.setBorder(null);
					}
				}
			});
		});
		return panelPuzzle;
	}
	
	public void reset() {
		if (!buttons.isEmpty())
			this.buttons = new ArrayList<PieceButton>();
	}

	private void makeMovement(PieceButton button) {
		PieceButton piece = buttons.stream().filter(h -> h.getPiece().isEmpty()).findFirst().get();
		button.getPiece().movement();
		button.configImg();
		piece.configImg();
		if (puzzle.completedPuzzle()) {
			Stopwatch.getInstance().stop();
			JOptionPane.showMessageDialog(null, "Parabéns, você ganhou!!!");
		}
	}
}
