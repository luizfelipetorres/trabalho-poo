package controller;

import java.util.List;
import java.util.Optional;

import dao.PlayerMatchDAO;
import model.PlayerMatch;
import util.RecordPlayerMatch;

public class PlayerMatchController {

	private static PlayerMatchController instance;

	private PlayerMatchController() {
	}

	public static PlayerMatchController getInstance() {
		if (instance == null) {
			instance = new PlayerMatchController();
		}
		return instance;
	}

	public boolean save(PlayerMatch playerMatch) {
		return PlayerMatchDAO.getInstance().save(playerMatch);
	}

	public boolean update(PlayerMatch playerMatch) {
		return PlayerMatchDAO.getInstance().update(playerMatch);
	}

	public Optional<PlayerMatch> findById(Long id) {
		return Optional.of(PlayerMatchDAO.getInstance().findById(id));
	}

	public List<PlayerMatch> findAll() {
		return PlayerMatchDAO.getInstance().findAll();
	}

	public void remove(Long id) {
		PlayerMatchDAO.getInstance().remove(id);
	}
	
	public RecordPlayerMatch recordPlayerMatchByPlayer(Long idPlayer) {
		return PlayerMatchDAO.getInstance().recordPlayerMatchByPlayer(idPlayer);
	}

}
