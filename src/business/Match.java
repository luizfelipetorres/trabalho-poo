package business;

import java.util.ArrayList;
import java.util.List;

public class Match {
	
	private int id;
	private List<Player> players;
	private Puzzle puzzle;
	
	public Match(int id, List<Player> players, Puzzle puzzle) {
		super();
		this.id = id;
		this.players = new ArrayList<Player>();
		this.puzzle = puzzle;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Puzzle getPuzzle() {
		return puzzle;
	}
	
}
