package controller;

import java.util.List;

import dao.MatchDAO;
import interfaces.DAOListener;
import model.Match;

public class MatchController implements DAOListener<Match>{

	private static MatchController instance;

	private MatchController() {
	}

	public static MatchController getInstance() {
		if (instance == null) {
			instance = new MatchController();
		}
		return instance;
	}
	
	@Override
	public boolean save(Match match) {
		/*if (puzzle.getId() == null) {
			puzzle = PuzzleDAO.getInstance().save(puzzle);
		}*/
		return MatchDAO.getInstance().save(match);
	}

	@Override
	public boolean update(Match match) {

		/*if (puzzle.getId() == null) {
			puzzle = PuzzleDAO.getInstance().save(puzzle);
		} else {
			PuzzleDAO.getInstance().update(puzzle);
		}*/

		return MatchDAO.getInstance().update(match);
	}

	@Override
	public Match findById(Long id) {
		return MatchDAO.getInstance().findById(id);
	}

	@Override
	public List<Match> findAll() {
		return MatchDAO.getInstance().findAll();
	}

	@Override
	public void remove(Long id) {
		MatchDAO.getInstance().remove(id);
	}

}
