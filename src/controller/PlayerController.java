package controller;

import java.sql.SQLException;
import java.util.List;

import dao.PlayerDAO;
import model.Player;

public class PlayerController {

	private static PlayerController instance;

	private PlayerController() {
	}

	public static PlayerController getInstance() {
		if (instance == null) {
			instance = new PlayerController();
		}
		return instance;
	}

	public Player authenticate(Player player) throws SQLException {
		return PlayerDAO.getInstance().authenticate(player);
	}

	public void save(Player player) throws SQLException {
		PlayerDAO.getInstance().save(player);
	}

	public boolean update(Player player) {
		return PlayerDAO.getInstance().update(player);
	}

	public List<Player> findAll() {
		return PlayerDAO.getInstance().findAll();
	}

	public Player findById(Long id) {
		return PlayerDAO.getInstance().findById(id);
	}

	public List<Player> findByName(String name) throws Exception {
		return PlayerDAO.getInstance().findByName(name);
	}

	public void remove(Long id) {
		PlayerDAO.getInstance().remove(id);
	}
}
