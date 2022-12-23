package controller;

import java.util.List;
import java.util.Optional;

import dao.MatchDAO;
import dao.PuzzlerDAO;
import model.Match;
import model.Puzzle;

public class MatchController {

	private static MatchController instance;

	private MatchController() {
	}

	public static MatchController getInstance() {
		if (instance == null) {
			instance = new MatchController();
		}
		return instance;
	}

	public Match save(Puzzle puzzle) {
		if (puzzle.getId() == null) {
			puzzle = PuzzlerDAO.getInstance().save(puzzle);
		}
		return MatchDAO.getInstance().save(new Match(puzzle));
	}

	public void update(Long id, Puzzle puzzle) {

		if (puzzle.getId() == null) {
			puzzle = PuzzlerDAO.getInstance().save(puzzle);
		} else {
			PuzzlerDAO.getInstance().update(puzzle);
		}

		MatchDAO.getInstance().update(new Match(id, puzzle));
	}

	public Optional<Match> findById(Long id) {
		return Optional.of(MatchDAO.getInstance().findById(id));
	}

	public List<Match> findAll() {
		return MatchDAO.getInstance().findAll();
	}

	public void remove(Long id) {
		MatchDAO.getInstance().remove(id);
	}

}
