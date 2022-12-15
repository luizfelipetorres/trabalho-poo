package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.ImageIcon;

public class Piece {
	private final int LINE;
	private final int COLUMN;
	
	private  int index;
	private ImageIcon img;
	private boolean isEmpty;
	
	public List<Piece> neighbors = new ArrayList<Piece>();
	
	public Piece(int index, int line, int column, boolean isEmpty) {
		this.index = index;
		this.LINE = line;
		this.COLUMN = column;
		this.isEmpty = isEmpty;
		img = new ImageIcon ("img\\numero-"+index+".png");
	}
	
	void addNeighbor(Piece neighbor) {
		boolean different =  LINE != neighbor.getLINE() && COLUMN!= neighbor.getCOLUMN() ;
		
		int deltaLine = Math.abs(LINE - neighbor.getLINE());
		int deltaColumn = Math.abs(COLUMN - neighbor.getCOLUMN());
		int generalDelta = deltaLine + deltaColumn;
		
		if(generalDelta == 1 && !different) {
			neighbors.add(neighbor);
		}
	}
	
	public void movement() {
		Optional<Piece> piece = neighbors.stream().filter(e -> e.isEmpty()).findFirst();
		
		if(piece.isPresent()) {
			exchange(this, piece.get());
			System.out.println("troca realizada");
		}
	}
	
	void exchange(Piece origin, Piece destiny) {
		int index = destiny.getIndex();
		ImageIcon img = destiny.getImg();
		boolean isEmpty = destiny.isEmpty();
		destiny.setAttributes(getIndex(), getImg(), isEmpty());
		setAttributes(index, img, isEmpty);
	}
	
	private void setAttributes(int index,ImageIcon img,boolean isEmpty) {
		setIndex(index);
		setImg(img);
		setEmpty(isEmpty);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
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
			setImg(new ImageIcon("img\\vazio.png"));
		}
		
		this.isEmpty = isEmpty;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	@Override
	public int hashCode() {
		return Objects.hash(COLUMN, LINE, img, index, isEmpty, neighbors);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		return COLUMN == other.COLUMN && LINE == other.LINE && Objects.equals(img, other.img) && index == other.index
				&& isEmpty == other.isEmpty && Objects.equals(neighbors, other.neighbors);
	}
	
}
