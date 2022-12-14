package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import business.Puzzle;
import business.TypeShuffle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;

public class Game {

	private JFrame frame;
	private Puzzle puzzle;
	private int numberField = 0;

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
	 */
	public Game() {
		initialize();
		puzzle = new Puzzle(4, 4,TypeShuffle.pairs);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(207, 207, 207));
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelGame = new JPanel();
		panelGame.setBounds(10, 11, 500, 500);
		frame.getContentPane().add(panelGame);
		panelGame.setLayout(null);
		
		for(int i = 5; i < panelGame.getWidth(); i=i+45) {
			for(int j = 5; j < panelGame.getHeight(); j=j+45) {
				
				JPanel fieldGame = new JPanel(new BorderLayout(0, 0));
				fieldGame.setBackground(new Color(128, 128, 128));
				fieldGame.setBounds(j, i, 40, 40);
				panelGame.add(fieldGame);
			
				JLabel lbNumber = new JLabel(String.valueOf(numberField));
				lbNumber.setHorizontalAlignment(SwingConstants.CENTER);
				lbNumber.setBounds(0, 0, 10, 10);
				fieldGame.add(lbNumber);
				
				numberField++;
			}	
		}
	}
}
