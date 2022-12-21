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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import interfaces.ShuffleListener;
import model.Puzzle;
import util.TypeShuffle;

public class PuzzleBoard extends Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PuzzleBoard instance;
	private List<PieceButton> buttons;
	private Puzzle puzzle;
	private File defaultImage;

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public static PuzzleBoard getInstance(){
		if(instance == null) {
			instance = new PuzzleBoard();
		}
		return instance;
	}
	
	private PuzzleBoard(){
		this.buttons = new ArrayList<PieceButton>();
		this.defaultImage = new File("img//naruto.jpg");
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	public Component initialize(JFrame frame, int size) throws IOException {
		
		JPanel panelPuzzle = new JPanel();
		panelPuzzle.setBorder(new LineBorder(new Color(0, 0, 128)));
		panelPuzzle.setBounds(10, 91, 631, 571);
		frame.getContentPane().add(panelPuzzle);
		
		File img = chooseImage();
		puzzle = new Puzzle(size, size,img,TypeShuffle.pairs);
		
		GridLayout gridLayout = new GridLayout(puzzle.getLINES(), puzzle.getCOLUMNS());
		panelPuzzle.setLayout(gridLayout);
		
		int whith = panelPuzzle.getWidth() / puzzle.getLINES();
		int height = panelPuzzle.getHeight() / puzzle.getCOLUMNS();
		puzzle.getPieces().forEach(e -> buttons.add(new PieceButton(e, whith, height)));
		
		ShuffleListener listener = new ShuffleListener() {
			@Override
			public void updateButtons() {
				buttons.forEach(button -> button.configImg());
			}
		};
		buttons.forEach(e -> panelPuzzle.add(e));

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
		
		return panelPuzzle;
		
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
	
	private boolean userChooseImage() {
		String msg = "Deseja escolher uma imagem?";
		int option = JOptionPane.showConfirmDialog(this, msg);
		return option == JOptionPane.YES_OPTION;
	}
	
	private File chooseImage() {
		if (!userChooseImage()) 
			return defaultImage;

		JFileChooser fileChooser = new JFileChooser(defaultImage);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
		fileChooser.setFileFilter(filter);
		boolean choosed = fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION;
		return choosed ? fileChooser.getSelectedFile() : defaultImage;
	}
}
