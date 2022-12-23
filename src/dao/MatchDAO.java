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
import model.Puzzle;

public class MatchDAO implements MatchDAOListener {

	private static MatchDAO instance;

	private MatchDAO() {}

	public static MatchDAO getInstance() {
		if (instance == null) {
			instance = new MatchDAO();
		}
		return instance;
	}

	
	@Override
	public Match save(Match match) {
		ResultSet id;
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO MATCH(PUZZLE_ID) VALUES (?)";
			PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, match.getPuzzle().getId());
			ps.execute();
			id = ps.getGeneratedKeys();
			if(id.next()) {
				match.setId(id.getLong(1));
			}
			ps.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return match;
	}

	@Override
	public void update(Match match) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "UPDATE MATCH SET PUZZLE_ID = ? WHERE PUZZLE_ID=?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, match.getPuzzle().getId());
			ps.setLong(2, match.getId());
			ps.execute();
			ps.close();
			connection.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public Match findById(Long id) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM MATCH WHERE MATCH_ID = " + id;
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			if (rs.next()) {
				Puzzle puzzle = PuzzlerDAO.getInstance().findById(rs.getLong("PUZZLE_ID"));
				return new Match(rs.getLong("MATCH_ID"), puzzle);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public void remove(Long id) {
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement stmt = connection.createStatement();

			String query = "DELETE FROM MATCH WHERE MATCH_ID = " + id;
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Match> findAll() {
		ArrayList<Match> listMatches = new ArrayList<Match>();

		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM MATCH";
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Puzzle puzzle = PuzzlerDAO.getInstance().findById(rs.getLong("PUZZLE_ID"));
				listMatches.add( new Match(rs.getLong("MATCH_ID"), puzzle));
			}
			rs.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMatches;
	}


}
