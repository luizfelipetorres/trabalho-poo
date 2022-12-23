package controller;

import java.util.List;

import dao.PieceDAO;
import model.Piece;

public class PieceControle {

	private static PieceControle instance;

	private PieceControle() {
	}

	public static PieceControle getInstance() {
		if (instance == null) {
			instance = new PieceControle();
		}
		return instance;
	}

	public void save(Long PlayerMatch, List<Piece> pieces) {
		PieceDAO.getInstance().save(PlayerMatch, pieces);
	}

	public void update(Long PlayerMatch, List<Piece> pieces) {
		PieceDAO.getInstance().update(PlayerMatch, pieces);
	}

	public void configPiece(Long PlayerMatch, List<Piece> pieces) {
		PieceDAO.getInstance().configPiece(PlayerMatch, pieces);
	}

}
