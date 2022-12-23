package controller;

import java.util.Optional;

import dao.PuzzlerDAO;
import model.Puzzle;

public class PuzzleController {

	private static PuzzleController instance;

	private PuzzleController() {
	}

	public static PuzzleController getInstance() {
		if (instance == null) {
			instance = new PuzzleController();
		}
		return instance;
	}

	public Puzzle save(Puzzle puzzle) {
		return PuzzlerDAO.getInstance().save(puzzle);
	}

	public Optional<Puzzle> findById(Long id) {
		return Optional.of(PuzzlerDAO.getInstance().findById(id));
	}

	public void update(Puzzle puzzle) {
		PuzzlerDAO.getInstance().update(puzzle);
	}

	public void remove(Long id) {
		PuzzlerDAO.getInstance().remove(id);
	}
}
