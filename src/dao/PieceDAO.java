package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.ConnectionFactory;
import model.Piece;

public class PieceDAO implements PieceDAOListener{

	private static PieceDAO instance;

	private PieceDAO() {
	}

	public static PieceDAO getInstance() {
		if (instance == null) {
			instance = new PieceDAO();
		}
		return instance;
	}
	
	@Override
	public void save(Long PlayerMatch, List<Piece> pieces) {
		Connection connection = ConnectionFactory.getConnection();
		String sql = "INSERT INTO PIECE(PLAYER_MATCH_ID, PIECE_INDEX, PIECE_CURRENT_POSITION,PIECE_EMPTY) VALUES (?,?,?,?)";
		PreparedStatement ps;
		int currentPosition = 0;
		
		try {
			ps = connection.prepareStatement(sql);
			for (int i = 0; i < pieces.size(); i++) {
				ps.setLong(1, PlayerMatch);
				ps.setInt(2, pieces.get(i).getIndex());
				ps.setInt(3, currentPosition++);
				ps.setBoolean(4, pieces.get(i).isEmpty());
				ps.execute();
			}
			
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

				
	}

	@Override
	public void update(Long PlayerMatch, List<Piece> pieces) {
		try {
			Connection connection = ConnectionFactory.getConnection();
			String sql = "UPDATE PIECE SET PIECE_CURRENT_POSITION=?, PIECE_EMPTY=? WHERE PLAYER_MATCH_ID=? AND PIECE_INDEX=?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			int currentPosition = 0;
			
			for (int i = 0; i < pieces.size(); i++) {
				ps.setInt(1, currentPosition++);
				ps.setBoolean(2, pieces.get(i).isEmpty());
				ps.setLong(3, PlayerMatch);
				ps.setInt(4, pieces.get(i).getIndex());
				ps.execute();
			}
					
			ps.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void configPiece(Long PlayerMatch, List<Piece> pieces){

		Connection connection = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM PIECE WHERE PLAYER_MATCH_ID = " + PlayerMatch;
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
			e.printStackTrace();
		}
	}
	
	private Piece pieceIndice(List<Piece>  pieces, int index) {
		return  pieces.stream().filter(e -> e.getIndex() == index).findFirst().get();
	}
	

}
