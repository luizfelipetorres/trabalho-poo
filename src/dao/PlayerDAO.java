package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Player;

public class PlayerDAO implements PlayerDAOListener {

	private static PlayerDAO instance;

	private PlayerDAO() {
	}

	public static PlayerDAO getInstance() {
		if (instance == null) {
			instance = new PlayerDAO();
		}
		return instance;
	}
	
	@Override
	public Player authenticate(Player player) {
		
		Player playerIntern = new Player();

		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PLAYER AS P WHERE P.PLAYER_USERNAME = '" + player.getPlayerUsername() + "' AND P.PLAYER_PASSWORD = '" + player.getPlayerPassword() + "'";
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				playerIntern.setPlayerId((rs.getInt("PLAYER_ID")));
				playerIntern.setPlayerUsername(rs.getString("PLAYER_USERNAME"));
				playerIntern.setPlayerEmail(rs.getString("PLAYER_EMAIL"));
				playerIntern.setPlayerPassword(rs.getString("PLAYER_PASSWORD"));
				rs.close();
				connection.close();
				return playerIntern;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public boolean save(Player player) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO PLAYER(PLAYER_USERNAME, PLAYER_EMAIL, PLAYER_PASSWORD) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, player.getPlayerUsername());
			ps.setString(2, player.getPlayerEmail());
			ps.setString(3, player.getPlayerPassword());
			ps.execute();
			ps.close();
			connection.close();
			JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!", "Feito", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", 0);;
			System.out.println(e.toString());
			return false;
		}
	}

	@Override
	public void update(Player player) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		String sql = "UPDATE PLAYER SET PLAYER_USERNAME = ?, PLAYER_EMAIL = ?, PLAYER_PASSWORD = ?, PLAYER_URL_IMAGE = ? WHERE PLAYER_ID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, player.getPlayerUsername());
		ps.setString(2, player.getPlayerEmail());
		ps.setString(3, player.getPlayerPassword());
		ps.setInt(5, player.getPlayerId());
		
		if(player.getFile() == null) ps.setString(4, null);
		else ps.setString(4, player.getFile().getPath());
		
		ps.execute();
		ps.close();
		connection.close();

	}
	
	@Override
	public List<Player> findAll() {

		ArrayList<Player> listPlayers = new ArrayList<Player>();

		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PLAYER";
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Player player = new Player();
				player.setPlayerId((rs.getInt("PLAYER_ID")));
				player.setPlayerUsername(rs.getString("PLAYER_USERNAME"));
				player.setPlayerEmail(rs.getString("PLAYER_EMAIL"));
				player.setPlayerPassword(rs.getString("PLAYER_PASSWORD"));
				listPlayers.add(player);
			}
			rs.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPlayers;
	}

	@Override
	public Player findById(Integer integer) {

		Player player = new Player();

		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PLAYER WHERE PLAYER_ID = " + integer;
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				player.setPlayerId((rs.getInt("PLAYER_ID")));
				player.setPlayerUsername(rs.getString("PLAYER_USERNAME"));
				player.setPlayerEmail(rs.getString("PLAYER_EMAIL"));
				player.setPlayerPassword(rs.getString("PLAYER_PASSWORD"));
				if(rs.getString("PLAYER_URL_IMAGE") == null) player.setFile(null);
				else player.setFile(new File(rs.getString("PLAYER_URL_IMAGE")));
				rs.close();
				connection.close();
				return player;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Player> findByName(String playerName) {
		
		ArrayList<Player> listPlayers = new ArrayList<Player>();
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PLAYER WHERE PLAYER_ID LIKE " + "'%" + playerName + "%'";
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Player player = new Player();
				player.setPlayerId((rs.getInt("PLAYER_ID")));
				player.setPlayerUsername(rs.getString("PLAYER_USERNAME"));
				player.setPlayerEmail(rs.getString("PLAYER_EMAIL"));
				player.setPlayerPassword(rs.getString("PLAYER_PASSWORD"));
				listPlayers.add(player);
			}
			rs.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPlayers;
	}

	@Override
	public void remove(Integer integer) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement stmt = connection.createStatement();

			String query = "DELETE FROM PLAYER WHERE PLAYER_ID = " + integer;

			stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
