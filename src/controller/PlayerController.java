package controller;

import java.sql.SQLException;
import java.util.List;

import dao.PlayerDAO;
import model.Player;
import model.validation.HibernateValidator;

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

    public void save(String name, String email, String password) throws SQLException {

        Player player = new Player();

        player.setPlayerName(name);
        player.setPlayerEmail(email);
        player.setPlayerPassword(password);

        if (HibernateValidator.isPlayer(player) == true) {
        	PlayerDAO.getInstance().save(player);
        }
    }

    public void update(int id, String name, String email, String password) throws SQLException {

    	Player player = new Player();

    	player.setPlayerName(name);
        player.setPlayerEmail(email);
        player.setPlayerPassword(password);
        
        if (HibernateValidator.isPlayer(player) == true) {
        	PlayerDAO.getInstance().update(player);
        }
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
