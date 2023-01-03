package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;

public class Piece {

	private final int LINE;
	private final int COLUMN;
	
	private int index;
	private ImageIcon img;
	private boolean isEmpty;
	private List<Piece> neighbors;
	
	public Piece(int index, int line, int column, boolean isEmpty) {
		this.index = index;
		this.LINE = line;
		this.COLUMN = column;
		this.isEmpty = isEmpty;
		this.neighbors = new ArrayList<Piece>();
	}
	
	public void addNeighbor(Piece neighbor) {
		boolean different =  this.getLINE() != neighbor.getLINE() && this.getCOLUMN() != neighbor.getCOLUMN();
		
		int deltaLine = Math.abs(this.getLINE() - neighbor.getLINE());
		int deltaColumn = Math.abs(this.getCOLUMN() - neighbor.getCOLUMN());
		int generalDelta = deltaLine + deltaColumn;
		
		if(generalDelta == 1 && !different) {
			neighbors.add(neighbor);
		}
	}
	
	public void movement() {
		Optional<Piece> piece = neighbors.stream().filter(e -> e.isEmpty()).findFirst();

		if(piece.isPresent()) {
			exchange(piece.get());
		}
	}
	
	public boolean verifyMovement() {
		Optional<Piece> piece = neighbors.stream().filter(e -> e.isEmpty()).findFirst();
		return piece.isPresent() ? true : false;
	}
	
	
	public void exchange(Piece destiny) {
		int index = destiny.getIndex();
		ImageIcon img = destiny.getImg();
		boolean isEmpty = destiny.isEmpty();
		destiny.setAttributes(this.getIndex(), this.getImg(), this.isEmpty());
		this.setAttributes(index, img, isEmpty);
	}
	
	private void setAttributes(int index, ImageIcon img, boolean isEmpty) {
		this.setIndex(index);
		this.setImg(img);
		this.setEmpty(isEmpty);
	}

	public int getIndex() {
		return index;
	}

	private void setIndex(int index) {
		this.index = index;
	}

	public int getLINE() {
		return LINE;
	}

	public int getCOLUMN() {
		return COLUMN;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		if(isEmpty) {
			this.setImg(new ImageIcon("img\\empty.png"));
		}
		
		this.isEmpty = isEmpty;
	}

	public ImageIcon getImg() {
		return img;
		
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

}
