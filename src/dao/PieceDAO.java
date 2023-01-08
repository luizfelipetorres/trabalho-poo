package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		String sql = "INSERT INTO PIECE(PLAYER_MATCH_ID, PIECE_INDEX, PIECE_CURRENT_POSITION, PIECE_COLUMN, PIECE_LINE, PIECE_EMPTY) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps;
		int currentPosition = 1;

		try {
			ps = connection.prepareStatement(sql);
			for (Piece piece : pieces) {
				ps.setLong(1, playerMatch);
				ps.setInt(2, piece.getIndex());
				ps.setInt(3, currentPosition++);
				ps.setInt(4, piece.getCOLUMN());
				ps.setInt(5, piece.getLINE());
				ps.setBoolean(6, piece.isEmpty());
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
			int currentPosition = 1;

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

	public List<Piece> findByPlayerMatchId(Long playerMatchId){
		List<Piece> response = new ArrayList<>();

		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM PIECE WHERE PLAYER_MATCH_ID = " + playerMatchId + " ORDER BY PIECE_INDEX";
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Piece piece = new Piece();
				
				piece.setIndex(rs.getInt("PIECE_INDEX"));
				piece.setCurrentPosition(rs.getInt("PIECE_CURRENT_POSITION"));
				piece.setCOLUMN(rs.getInt("PIECE_COLUMN"));
				piece.setLINE(rs.getInt("PIECE_LINE"));
				piece.setEmpty(rs.getBoolean("PIECE_EMPTY"));
				response.add(piece);
			}
			rs.close();
			connection.close();
			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	

}
