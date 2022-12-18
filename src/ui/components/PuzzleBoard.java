package ui.components;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Puzzle;
import util.TypeShuffle;

public class PuzzleBoard extends Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PieceButton> buttons = new ArrayList<>();

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public PuzzleBoard(JPanel panelGame, int size) throws IOException {
		initialize(panelGame, size);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize(JPanel panelGame, int size) throws IOException {
		
		File img = new File("img//naruto.jpg");
		Puzzle puzzle = new Puzzle(size, size,img,TypeShuffle.pairs);
		
		GridLayout gridLayout = new GridLayout(puzzle.getLINES(), puzzle.getCOLUMNS());
		panelGame.setLayout(gridLayout);
		
		int whith = panelGame.getWidth() / puzzle.getLINES();
		int height = panelGame.getHeight() / puzzle.getCOLUMNS();
		puzzle.getPieces().forEach(e -> buttons.add(new PieceButton(e, whith, height)));
				
		buttons.forEach(el ->{
			el.addMouseListener(new MouseAdapter() {
	            @Override
	             public void mouseClicked(MouseEvent e) {
	            	PieceButton piece =  buttons.stream().filter(h -> h.getPiece().isEmpty()).findFirst().get();
	            	el.getPiece().movement();  
	            	el.configImg();
	            	piece.configImg();
	            	if(puzzle.completedPuzzle()) {
	                    JOptionPane.showMessageDialog (null, "ganhou;)");
	            	}
	             }            
	        });
		});
		
		buttons.forEach(e -> panelGame.add(e));
		


	}
}
