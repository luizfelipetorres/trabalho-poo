package dao;

import model.Puzzle;

public interface PuzzlerDAOListener {
	public Puzzle save(Puzzle puzzle);
	public Puzzle findById(Long id);
	public void remove(Long id);
	public void update(Puzzle puzzle);
}
