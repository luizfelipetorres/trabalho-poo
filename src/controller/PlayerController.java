package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;

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

	public void authenticate(Player player) throws SQLException {
		PlayerDAO.getInstance().authenticate(player);
	}

	public void save(Player player) throws SQLException {
		PlayerDAO.getInstance().save(player);
	}

	public void update(Player player)  {
		PlayerDAO.getInstance().update(player);
	}
	
	public void updatePhoto(File file,Player player) throws SQLException, IOException {
		
		if(file != null) {
			String extension = file.getPath().substring(file.getPath().length() - 3 );
			File outputfile = new File("img//users//"+player.getPlayerUsername()+"." + extension);
			if(player.getFile() != null) player.getFile().delete();
			ImageIO.write(ImageIO.read(file), extension, outputfile);
			player.setFile(outputfile);
		}else {
			player.setFile(null);
		}
		
		update(player);
	}

	public List<Player> findAll() {
		return PlayerDAO.getInstance().findAll();
	}

	public Player findById(int id) {
		return PlayerDAO.getInstance().findById(id);
	}

	public List<Player> findByName(String name) throws Exception {
		return PlayerDAO.getInstance().findByName(name);
	}

	public void remove(int id) {
		PlayerDAO.getInstance().remove(id);
	}
}
