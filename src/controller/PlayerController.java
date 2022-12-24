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

	public void authenticate(String name, String password) throws SQLException {

		Player player = new Player();

		player.setPlayerUsername(name);
		player.setPlayerPassword(password);

		PlayerDAO.getInstance().authenticate(player);
	}

	public void save(String name, String email, String password) throws SQLException {

		Player player = new Player();

		player.setPlayerUsername(name);
		player.setPlayerEmail(email);
		player.setPlayerPassword(password);

		PlayerDAO.getInstance().save(player);
	}

	public void update(int id, String name, String email, String password) throws SQLException {

		Player player = new Player();

		player.setPlayerUsername(name);
		player.setPlayerEmail(email);
		player.setPlayerPassword(password);

		PlayerDAO.getInstance().update(player);
	}
	
	public void update(Player player) throws SQLException {
		PlayerDAO.getInstance().update(player);
	}

	public List<Player> findAll() {
		return PlayerDAO.getInstance().findAll();
	}

	public Player findById(int id) throws Exception {
		return PlayerDAO.getInstance().findById(id);
	}

	public List<Player> findByName(String name) throws Exception {
		return PlayerDAO.getInstance().findByName(name);
	}

	public void remove(int id) {
		PlayerDAO.getInstance().remove(id);
	}
}
