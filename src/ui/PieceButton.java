package ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import business.Piece;

@SuppressWarnings("serial")
public class PieceButton extends JButton {
	
	private Piece piece;
	private int whith;
	private int height;
	public PieceButton(Piece piece, int whith, int height) {	
		this.piece = piece;
		this.whith = whith;
		this.height = height;
		configImg();
	}
	
	public void configImg() {
		ImageIcon img = piece.getImg();
		img.setImage(img.getImage().getScaledInstance(whith,height, 100));
		setIcon(img);
	}

	public Piece getPiece() {
		return piece;
	}

}
