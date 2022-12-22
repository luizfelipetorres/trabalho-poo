package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.ConnectionFactory;
import model.Piece;
import model.Puzzle;
import util.TypeShuffle;

public class PuzzlerDAO implements PuzzlerDAOListener {
	
	private static PuzzlerDAO instance;

	private PuzzlerDAO() {}

	public static PuzzlerDAO getInstance() {
		if (instance == null) {
			instance = new PuzzlerDAO();
		}
		return instance;
	}

	@Override
	public Puzzle save(Puzzle puzzle) {
		ResultSet id;
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO PUZZLE(PUZZLE_LINES, PUZZLE_COLUMNS, PUZZLE_TYPE_SHUFFLE,PUZZLE_URL_IMAGE) VALUES (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, puzzle.getLINES());
			ps.setInt(2, puzzle.getCOLUMNS());
			ps.setString(3, puzzle.getTypeShuffle().toString());
			ps.setString(4, puzzle.getFile().getPath());
			ps.execute();
			id = ps.getGeneratedKeys();
			
			if(id.next()) {
				puzzle.setId(id.getLong(1));
				savePiece(puzzle.getId(), puzzle.getPieces());
			}
			
			ps.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return puzzle;
	}
	
	@Override
	public Puzzle findById(Long id) {
		Puzzle puzzle = null;
		int lines;
		int columns;
		String urlImagem;
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
				urlImagem = rs.getString("PUZZLE_URL_IMAGE");
				rs.close();
				connection.close();
				puzzle =  new Puzzle(id,lines, columns, new File(urlImagem), typeShuffle);
				configPiece(puzzle);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return puzzle;
	}

	
	@Override
	public void update(Puzzle puzzle) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "UPDATE PUZZLE SET PUZZLE_LINES = ?, PUZZLE_COLUMNS = ?, PUZZLE_TYPE_SHUFFLE = ?,PUZZLE_URL_IMAGE = ? WHERE PUZZLE_ID=?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, puzzle.getLINES());
			ps.setInt(2, puzzle.getCOLUMNS());
			ps.setString(3, puzzle.getTypeShuffle().toString());
			ps.setString(4, puzzle.getFile().getPath());
			ps.setLong(5, puzzle.getId());
			ps.execute();
			ps.close();
			connection.close();
			
			updatePiece(puzzle.getId(), puzzle.getPieces());

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	@Override
	public void remove(Long id) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement stmt = connection.createStatement();

			String queryPiece = "DELETE FROM PIECE WHERE PUZZLE_ID = " + id;
			String queryPuzzle = "DELETE FROM PUZZLE WHERE PUZZLE_ID = " + id;
			stmt.executeUpdate(queryPiece);
			stmt.executeUpdate(queryPuzzle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	private void savePiece(Long puzzleID ,List<Piece> pieces) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		String sql = "INSERT INTO PIECE(PUZZLE_ID, PIECE_INDEX, PIECE_CURRENT_POSITION,PIECE_EMPTY) VALUES (?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		int currentPosition = 0;

		for (int i = 0; i < pieces.size(); i++) {
			ps.setLong(1, puzzleID);
			ps.setInt(2, pieces.get(i).getIndex());
			ps.setInt(3, currentPosition++);
			ps.setBoolean(4, pieces.get(i).isEmpty());
			ps.execute();
		}
				
		ps.close();
		connection.close();
		
	}
	
	
	private void updatePiece(Long puzzleID ,List<Piece> pieces) throws SQLException{
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "UPDATE PIECE SET PIECE_CURRENT_POSITION=?, PIECE_EMPTY=? WHERE PUZZLE_ID=? AND PIECE_INDEX=?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			int currentPosition = 0;
			
			for (int i = 0; i < pieces.size(); i++) {
				ps.setInt(1, currentPosition++);
				ps.setBoolean(2, pieces.get(i).isEmpty());
				ps.setLong(3, puzzleID);
				ps.setInt(4, pieces.get(i).getIndex());
				ps.execute();
			}
					
			ps.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	private void configPiece(Puzzle puzzle) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM PIECE WHERE PUZZLE_ID = " + puzzle.getId();
		Statement stmt = (Statement) connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Piece pieceIndice = pieceIndice(puzzle,rs.getInt("PIECE_INDEX") );
			Piece currentPosition = pieceCurrentPosition(puzzle, rs.getInt("PIECE_CURRENT_POSITION"));
			currentPosition.exchange(pieceIndice);
		}
		
		rs.close();
		connection.close();
	}
	
	private Piece pieceIndice(Puzzle puzzle, int index) {
		return  puzzle.getPieces().stream().filter(e -> e.getIndex() == index).findFirst().get();
	}
	
	private Piece pieceCurrentPosition (Puzzle puzzle, int currentPosition ) {
		return  puzzle.getPieces().get(currentPosition);
	}
	
}
