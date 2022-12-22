package model;

import java.util.ArrayList;
import java.util.List;

public class Match {
	
	private int id;
	private Puzzle puzzle;
	
	public Match(int id, Puzzle puzzle) {
		super();
		this.id = id;
		this.puzzle = puzzle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
}
