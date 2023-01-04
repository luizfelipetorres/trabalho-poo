package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.ConnectionFactory;
import model.Piece;

public class PieceDAO {

	private static PieceDAO instance;

	private PieceDAO() {
	}

	public static PieceDAO getInstance() {
		if (instance == null) {
			instance = new PieceDAO();
		}
		return instance;
	}

	public boolean save(Long playerMatch, List<Piece> pieces) {
		Connection connection = ConnectionFactory.getConnection();
		String sql = "INSERT INTO PIECE(PLAYER_MATCH_ID, PIECE_INDEX, PIECE_CURRENT_POSITION, PIECE_EMPTY) VALUES (?,?,?,?)";
		PreparedStatement ps;
		int currentPosition = 0;

		try {
			ps = connection.prepareStatement(sql);
			for (Piece piece : pieces) {
				ps.setLong(1, playerMatch);
				ps.setInt(2, piece.getIndex());
				ps.setInt(3, currentPosition++);
				ps.setBoolean(4, piece.isEmpty());
				ps.execute();
			}

			ps.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

	public boolean update(Long playerMatch, List<Piece> pieces) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "UPDATE PIECE SET PIECE_CURRENT_POSITION = ?, PIECE_EMPTY = ? WHERE PLAYER_MATCH_ID = ? AND PIECE_INDEX = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			int currentPosition = 0;

			for (Piece piece : pieces) {
				ps.setInt(1, currentPosition++);
				ps.setBoolean(2, piece.isEmpty());
				ps.setLong(3, playerMatch);
				ps.setInt(4, piece.getIndex());
				ps.execute();
			}

			ps.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

	public void configPiece(Long playerMatch, List<Piece> pieces) {

		Connection connection = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM PIECE WHERE PLAYER_MATCH_ID = " + playerMatch;
		Statement stmt;
		try {
			stmt = (Statement) connection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Piece currentPosition = pieces.get(rs.getInt("PIECE_INDEX"));
				Piece pieceIndex = pieceIndice(pieces, rs.getInt("PIECE_CURRENT_POSITION"));
				pieceIndex.exchange(currentPosition);
			}

			rs.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	private Piece pieceIndice(List<Piece> pieces, int index) {
		return pieces.stream().filter(e -> e.getIndex() == index).findFirst().get();
	}

}
