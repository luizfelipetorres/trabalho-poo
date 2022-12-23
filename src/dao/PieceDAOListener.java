package dao;

import java.util.List;

import model.Piece;

public interface PieceDAOListener {
	
	public void save(Long PlayerMatch ,List<Piece> pieces);
	public void update(Long PlayerMatch ,List<Piece> pieces);
	public void configPiece(Long PlayerMatch ,List<Piece> pieces);
}
