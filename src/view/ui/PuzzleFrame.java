package view.ui;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.MatchDAO;
import dao.PlayerMatchDAO;
import dao.PuzzleDAO;
import interfaces.PuzzleBoardListener;
import interfaces.StopwatchListener;
import model.Match;
import model.Player;
import model.PlayerMatch;
import model.Puzzle;
import util.TypeShuffle;
import view.components.PuzzleBoard;
import view.components.Stopwatch;

public class PuzzleFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player player;
	private PuzzleBoard puzzleBoard;
	private Stopwatch stopWatch;
	
	public PuzzleFrame(Player player, int size, File image, TypeShuffle typeShuffle) {
		super();
		this.player = player;
		this.stopWatch = new Stopwatch();
		this.puzzleBoard = new PuzzleBoard(size, image, typeShuffle, puzzleBoardListener(), stopwatchListener());
		this.initialize();
	}

	public void initialize() {

		this.setBounds(0, 0, 1175, 670);
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(null);

		JLabel lbBgmain = new JLabel("");
		lbBgmain.setIcon(new ImageIcon("img\\bg-main.jpg"));
		lbBgmain.setBounds(645, 0, 430, 640);
		this.add(lbBgmain);

		this.add(puzzleBoard);
		this.add(stopWatch);

	}
	
	public PuzzleBoardListener puzzleBoardListener() {
		
		return new PuzzleBoardListener() {

			@Override
			public void persistence(Puzzle puzzle) {
				PuzzleDAO.getInstance().save(puzzle);
				Match match = new Match(puzzle);
				MatchDAO.getInstance().save(match);
				
				PlayerMatch playerMatch = new PlayerMatch(
						player,
						match,
						stopWatch.getDuration(),
						true
						);
				PlayerMatchDAO.getInstance().save(playerMatch);
				stopWatch.stop();
				
			}
			
		};
	}
	public StopwatchListener stopwatchListener() {
		
		return new StopwatchListener() {
			
			@Override
			public void pause() {
				stopWatch.pause();
			}
			
			@Override
			public void stop() {
				stopWatch.stop();
			}

			@Override
			public boolean isRunning() {
				return stopWatch.isRunning();
			}

			@Override
			public Long getDuration() {
				return stopWatch.getDuration();
			} 
			
		};
	}

}
