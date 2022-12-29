package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Match;
import model.Player;
import model.PlayerMatch;
import util.RecordPlayerMatch;

public class PlayerMatchDAO implements PlayerMatchDAOListener {
	
	private static PlayerMatchDAO instance;

	private PlayerMatchDAO() {}

	public static PlayerMatchDAO getInstance() {
		if (instance == null) {
			instance = new PlayerMatchDAO();
		}
		return instance;
	}


	public PlayerMatch save(PlayerMatch playerMatch) {
		ResultSet id;
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO PLAYER_MATCH(PLAYER_MATCH_DURATION, PLAYER_MATCH_POINTS, PLAYER_MATCH_COMPLETE, PLAYER_ID, MATCH_ID) VALUES (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, playerMatch.getDuration());
			ps.setDouble(2, playerMatch.getPlayerPoints());
			ps.setBoolean(3, playerMatch.isComplete());
			ps.setInt(4, playerMatch.getPlayer().getPlayerId());
			ps.setLong(5, playerMatch.getMatch().getId());
			ps.execute();

			id = ps.getGeneratedKeys();

			if (id.next()) {
				playerMatch.setId(id.getLong(1));
			}

			ps.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return playerMatch;
	}

	@Override
	public void update(PlayerMatch playerMatch) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "UPDATE PLAYER_MATCH SET PLAYER_MATCH_DURATION = ?, PLAYER_MATCH_POINTS = ?, PLAYER_MATCH_COMPLETE = ?,PLAYER_ID = ?, MATCH_ID=? WHERE PLAYER_MATCH_ID = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, playerMatch.getDuration());
			ps.setDouble(2, playerMatch.getPlayerPoints());
			ps.setBoolean(3, playerMatch.isComplete());
			ps.setInt(4, playerMatch.getPlayer().getPlayerId());
			ps.setLong(5, playerMatch.getMatch().getId());
			ps.setLong(6, playerMatch.getId());
			ps.execute();
			ps.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public PlayerMatch findById(Long id) {

		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PLAYER_MATCH WHERE PLAYER_MATCH_ID = " + id;
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Match match = MatchDAO.getInstance().findById(rs.getLong("MATCH_ID"));
				Player player = PlayerDAO.getInstance().findById(rs.getInt("PLAYER_ID"));
				PlayerMatch playerMatch = new PlayerMatch(rs.getLong("PLAYER_MATCH_ID"), player, match,
						rs.getInt("PLAYER_MATCH_DURATION"), rs.getDouble("PLAYER_MATCH_POINTS"),
						rs.getBoolean("PLAYER_MATCH_COMPLETE"));
				rs.close();
				connection.close();
				return playerMatch;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PlayerMatch> findAll() {
		ArrayList<PlayerMatch> listPlayerMatch = new ArrayList<PlayerMatch>();

		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PLAYER_MATCH";
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Match match = MatchDAO.getInstance().findById(rs.getLong("MATCH_ID"));
				Player player = PlayerDAO.getInstance().findById(rs.getInt("PLAYER_ID"));
				PlayerMatch playerMatch = new PlayerMatch(rs.getLong("PLAYER_MATCH_ID"), player, match,
						rs.getInt("PLAYER_MATCH_DURATION"), rs.getDouble("PLAYER_MATCH_POINTS"),
						rs.getBoolean("PLAYER_MATCH_COMPLETE"));
				listPlayerMatch.add(playerMatch);
			}
			rs.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPlayerMatch;
	}

	@Override
	public void remove(Long id) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement stmt = connection.createStatement();

			String query = "DELETE FROM PLAYER_MATCH WHERE PLAYER_MATCH_ID = " + id;
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RecordPlayerMatch recordPlayerMatchByPlayer(Integer idPlayer) {
		RecordPlayerMatch recordPlayerMatch = new RecordPlayerMatch();
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT  COUNT(PLAYER_MATCH_COMPLETE) AS TOTAL_MATCH, "
					+ "SUM(PLAYER_MATCH_POINTS) AS TOTAL_DE_POINTS, "
					+ "MAX(PLAYER_MATCH_POINTS) AS MAX_POINTS, "
					+ "MIN(PLAYER_MATCH_POINTS) AS MIN_POINTS, "
					+ "AVG(PLAYER_MATCH_POINTS) AS AVG_POINTS, "
					+ "SUM(PLAYER_MATCH_DURATION) AS TOTAL_DURATION, "
					+ "MAX(PLAYER_MATCH_DURATION) AS MAX_DURATION, "
					+ "MIN(PLAYER_MATCH_DURATION) AS MIN_DURATION "
					+ "FROM PLAYER_MATCH "
					+ "WHERE player_id = " +idPlayer + " AND player_match_complete = TRUE";
			
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next()) {
				recordPlayerMatch.setTotalMatchCompleta(rs.getInt("TOTAL_MATCH"));
				recordPlayerMatch.setTotalPoints(rs.getLong("TOTAL_DE_POINTS"));
				recordPlayerMatch.setMaxPoints(rs.getInt("MAX_POINTS"));
				recordPlayerMatch.setMinPoints(rs.getInt("MIN_POINTS"));
				recordPlayerMatch.setAvgPoints(rs.getDouble("AVG_POINTS"));
				recordPlayerMatch.setTotalDuration(rs.getLong("TOTAL_DURATION"));
				recordPlayerMatch.setMaxDuration(rs.getLong("MAX_DURATION"));
				recordPlayerMatch.setMinDuration(rs.getLong("MIN_DURATION"));
			}
			
			sql = "SELECT COUNT(player_match_complete) AS TOTAL_MATCH "
				+ "FROM PLAYER_MATCH "
				+ "WHERE player_id = " +idPlayer + " AND player_match_complete = FALSE";
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				recordPlayerMatch.setTotalMatchNotCompleta(rs.getInt("TOTAL_MATCH"));
			}
					
			rs.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordPlayerMatch;
	}
}
