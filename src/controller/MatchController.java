package controller;

import java.util.List;

import dao.MatchDAO;
import model.Match;

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

	public boolean save(Match match) {
		return MatchDAO.getInstance().save(match);
	}

	public boolean update(Match match) {
		return MatchDAO.getInstance().update(match);
	}

	public Match findById(Long id) {
		return MatchDAO.getInstance().findById(id);
	}

	public List<Match> findAll() {
		return MatchDAO.getInstance().findAll();
	}

	public void remove(Long id) {
		MatchDAO.getInstance().remove(id);
	}

}
