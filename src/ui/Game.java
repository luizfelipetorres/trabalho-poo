package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Puzzle;
import util.TypeShuffle;

public class Game {

	private JFrame frame;
	private List<PieceButton> buttons = new ArrayList<>();
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Game() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		File img = new File("img//naruto.jpg");
		Puzzle puzzle;
		
		puzzle = new Puzzle(2, 2,img,TypeShuffle.pairs);
		
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