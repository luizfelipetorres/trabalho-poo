package business;

import java.util.Random;

public class Puzzle {

	private int size;
	private Piece[][] pieces;

	public Puzzle(int size, TypeShuffle typeShuffle) {
		this.size = size;
		this.generateTable();
		this.shuffleTable(typeShuffle);
	}

	private void generateTable() {
		this.pieces = new Piece[getSize()][getSize()];
		int value = 1;

		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces.length; j++) {
				this.pieces[i][j] = new Piece(value++);
			}
		}
	}

	private void shuffleTable(TypeShuffle typeShuffle) {
		Random random = new Random();
		int numberShuffle = 0;

		if (typeShuffle.equals(TypeShuffle.pairs)) {
			while (numberShuffle == 0 || numberShuffle % 2 != 0) {
				numberShuffle = random.nextInt(0, 100);
			}
		}

		if (typeShuffle.equals(TypeShuffle.odd)) {
			while (numberShuffle == 0 || numberShuffle % 2 == 0) {
				numberShuffle = random.nextInt(0, 100);
			}
		}

		for (int i = 0; i < numberShuffle; i++) {
			int line1 = random.nextInt(0, getSize());
			int column1 = random.nextInt(0, getSize());

			int line2 = random.nextInt(0, getSize());
			int column2 = random.nextInt(0, getSize());

			Piece parts = this.pieces[line2][column2];
			pieces[line2][column2] = pieces[line1][column1];
			pieces[line1][column1] = parts;
		}

		System.out.println("quantidade de embaralhamento:" + numberShuffle);

	}

	public void movePieces(int line1, int column1, int line2, int column2) {

		if (validOperation(line1, column1, line2, column2)) {
			Piece parts = this.pieces[line2][column2];
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

		if (line1 < 0 || column1 < 0 || line2 < 0 || column2 < 0 || line1 >= getSize() || column1 >= getSize()
				|| line2 >= getSize() || column2 >= getSize()) {
			System.out.println("e1");
			return false;
		}

		Piece parts1 = this.pieces[line1][column1];
		Piece parts2 = this.pieces[line2][column2];

		if (parts1.equals(parts2)) {
			return false;
		}

		if (parts1.getIndex() != getSize() * getSize() && parts2.getIndex() != getSize() * getSize()) {
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

		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces.length; j++) {
				if (pieces[i][j].getIndex() != value++) {
					return false;
				}
			}
		}

		return true;
	}

	public void show() {
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces.length; j++) {
				System.out.printf("%02d ", pieces[i][j].getIndex());
			}
			System.out.println();
		}
	}

	public int getSize() {
		return size;
	}

}
