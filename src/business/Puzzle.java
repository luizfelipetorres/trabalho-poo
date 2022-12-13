package business;

import java.util.Random;

public class Puzzle {

	private int line;
	private int column;
	private Piece[][] pieces;

	public Puzzle(int line , int column, TypeShuffle typeShuffle) {
		setLine(line);
		setColumn(column);
		this.generateTable();
		this.shuffleTable(typeShuffle);
	}

	private void generateTable() {
		this.pieces = new Piece[getLine()][getColumn()];
		int value = 1;

		for (int i = 0; i < getLine(); i++) {
			for (int j = 0; j < getColumn(); j++) {
				this.pieces[i][j] = new Piece(value++);
			}
		}
	}

	private void shuffleTable(TypeShuffle typeShuffle) {
		Random random = new Random();
		int numberShuffle = 0;

		if (typeShuffle.equals(TypeShuffle.pairs)) {
			while (numberShuffle == 0 || numberShuffle % 2 != 0) {
				numberShuffle = random.nextInt(0, size()+1);
			}
		}

		if (typeShuffle.equals(TypeShuffle.odd)) {
			while (numberShuffle == 0 || numberShuffle % 2 == 0) {
				numberShuffle = random.nextInt(0, size()+1);
			}
		}

		for (int i = 0; i < numberShuffle; i++) {
			int line1 = random.nextInt(0, getLine());
			int column1 = random.nextInt(0, getColumn());

			int line2 = random.nextInt(0, getLine());
			int column2 = random.nextInt(0, getColumn());

			Piece parts = this.pieces[line2][column2];
			pieces[line2][column2] = pieces[line1][column1];
			pieces[line1][column1] = parts;
		}

		System.out.println("quantidade de embaralhamento:" + numberShuffle);

	}

	public void movePieces(int line1, int column1, int line2, int column2) {

		if (validOperation(line1, column1, line2, column2)) {
			
			Piece parts = pieces[line2][column2];
			pieces[line2][column2] = pieces[line1][column1];
			pieces[line1][column1] = parts;

			if (completedPuzzle()) {
				System.out.println("Completou o quebra cabeca");
			}
			
		} else {
			System.out.println("invalido");
		}

	}

	private boolean validOperation(int line1, int column1, int line2, int column2) {

		if (line1 < 0 || column1 < 0 || 
			line2 < 0 || column2 < 0 ||
			line1 >= getLine() || column1 >= getColumn() ||
			line2 >= getLine() || column2 >= getColumn()) {
			return false;
		}

		Piece parts1 = this.pieces[line1][column1];
		Piece parts2 = this.pieces[line2][column2];

		if (parts1.equals(parts2)) {
			return false;
		}

		if (parts1.getIndex() != size()  && parts2.getIndex() != size()) {
			return false;
		}

		int sum = (line1 + column1) - (line2 + column2);

		if (sum == 1)
			return true;
		else if (sum == -1)
			return true;
		else
			return false;
	}

	private boolean completedPuzzle() {

		int value = 1;

		for (int i = 0; i < getLine(); i++) {
			for (int j = 0; j < getColumn(); j++) {
				if (pieces[i][j].getIndex() != value++) {
					return false;
				}
			}
		}

		return true;
	}

	public void show() {
		for (int i = 0; i < getLine(); i++) {
			for (int j = 0; j < getColumn(); j++) {
				System.out.printf("%02d ", pieces[i][j].getIndex());
			}
			System.out.println();
		}
	}
	
	public int size() {
		return getLine() * getColumn();
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}

}
