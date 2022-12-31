package view.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import interfaces.ShuffleListener;
import interfaces.StopwatchListener;
import model.Puzzle;
import util.TypeShuffle;

public class PuzzleBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	private StopwatchListener stopwatchListener;
	private List<PieceButton> buttons;
	private Puzzle puzzle;

	public PuzzleBoard(int size, File image, TypeShuffle typeShuffle, StopwatchListener stopwatchListener) {
		this.stopwatchListener = stopwatchListener;
		this.buttons = new ArrayList<PieceButton>();
		this.puzzle = new Puzzle(size, size, image, typeShuffle);
		this.initialize();
	}

	private void initialize() {
		
		this.setBorder(new LineBorder(new Color(0, 0, 128)));
		this.setBounds(10, 80, 630, 560);
		this.setLayout(new GridLayout(puzzle.getLINES(), puzzle.getCOLUMNS()));

		int width = this.getWidth() / puzzle.getLINES();
		int height = this.getHeight() / puzzle.getCOLUMNS();
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
			this.add(button);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (stopwatchListener.isRunning()) {
						makeMovement(button);
						button.setBorder(null);
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (button.getPiece().verifyMovement() && stopwatchListener.isRunning()) {
						button.setBorder(BorderFactory.createLineBorder(Color.green, 3));
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (stopwatchListener.isRunning()) {
						button.setBorder(null);
					}
				}
			});
		});
	}

	private void makeMovement(PieceButton button) {
		PieceButton piece = buttons.stream().filter(h -> h.getPiece().isEmpty()).findFirst().get();
		button.getPiece().movement();
		button.configImg();
		piece.configImg();
		if (puzzle.completedPuzzle()) {
			stopwatchListener.stop();
			JOptionPane.showMessageDialog(null, "Parabéns, você ganhou!!!");
		}
	}
}
