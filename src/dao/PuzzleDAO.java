package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.ConnectionFactory;
import interfaces.DAOListener;
import model.Puzzle;
import util.TypeShuffle;

public class PuzzleDAO implements DAOListener<Puzzle> {
	
	private static PuzzleDAO instance;

	private PuzzleDAO() {}

	public static PuzzleDAO getInstance() {
		if (instance == null) {
			instance = new PuzzleDAO();
		}
		return instance;
	}

	@Override
	public boolean save(Puzzle puzzle) {
		ResultSet id;
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO PUZZLE(PUZZLE_SIZE, PUZZLE_TYPE_SHUFFLE, PUZZLE_URL_IMAGE) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, puzzle.getSIZE());
			ps.setString(2, puzzle.getTypeShuffle().toString());
			ps.setString(3, puzzle.getUrlImage());
			ps.execute();
			id = ps.getGeneratedKeys();
			
			if(id.next()) {
				puzzle.setId(id.getLong(1));
			}
			ps.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		
		return false;
	}
	
	@Override
	public List<Puzzle> findAll() {
		/* implements logic */
		return null;
	}
	
	@Override
	public Puzzle findById(Long id) {
		Puzzle puzzle = null;
		int lines;
		int columns;
		String urlImage;
		TypeShuffle typeShuffle;
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PUZZLE WHERE PUZZLE_ID = " + id;
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			

			if (rs.next()) {
				lines =  rs.getInt("PUZZLE_LINES");
				columns =  rs.getInt("PUZZLE_COLUMNS");
				typeShuffle = TypeShuffle.valueOf(rs.getString("PUZZLE_TYPE_SHUFFLE"));
				urlImage = rs.getString("PUZZLE_URL_IMAGE");
				rs.close();
				connection.close();
				puzzle =  new Puzzle(id,lines, columns, urlImage, typeShuffle);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return puzzle;
	}

	
	@Override
	public boolean update(Puzzle puzzle) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "UPDATE PUZZLE SET PUZZLE_SIZE = ?, PUZZLE_TYPE_SHUFFLE = ?,PUZZLE_URL_IMAGE = ? WHERE PUZZLE_ID=?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, puzzle.getSIZE());
			ps.setString(2, puzzle.getTypeShuffle().toString());
			ps.setString(3, puzzle.getUrlImage());
			ps.setLong(4, puzzle.getId());
			ps.execute();
			ps.close();
			connection.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	@Override
	public void remove(Long id) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement stmt = connection.createStatement();

			String query = "DELETE FROM PUZZLE WHERE PUZZLE_ID = " + id;
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
