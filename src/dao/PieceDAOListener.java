package dao;

import java.util.List;

import model.Piece;

public interface PieceDAOListener {
	
	public void save(Long playerMatch ,List<Piece> pieces);
	public void update(Long playerMatch ,List<Piece> pieces);
	public void configPiece(Long playerMatch ,List<Piece> pieces);
}
