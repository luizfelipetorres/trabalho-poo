package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import interfaces.ShuffleListener;
import util.TypeShuffle;

public class Puzzle {

	private final int LINES;
	private final int COLUMNS;
	private final List<Piece> pieces = new ArrayList<>();
	private final TypeShuffle typeShuffle;

	public Puzzle(int lines, int columns, TypeShuffle typeShuffle) {
		this.LINES = lines;
		this.COLUMNS = columns;
		this.typeShuffle = typeShuffle;
		generatepieces();
		associateNeighbors();
		addEmpty();
	}

	public Puzzle(int lines, int columns, File file, TypeShuffle typeShuffle) throws IOException {
		this.LINES = lines;
		this.COLUMNS = columns;
		this.typeShuffle = typeShuffle;
		generatepieces(file);
		associateNeighbors();
		addEmpty();
		// shuffleTable(typeShuffle);
	}

	private void generatepieces() {
		int index = 1;
		for (int l = 0; l < LINES; l++) {
			for (int c = 0; c < COLUMNS; c++) {
				Piece piece = new Piece(index, l, c, false);
				piece.setImg(new ImageIcon("img\\numero-" + index + ".png"));
				pieces.add(piece);
				index++;
			}
		}
	}

	private void generatepieces(File file) throws IOException {
		BufferedImage imagem = ImageIO.read(file);
		int w = imagem.getWidth() / getCOLUMNS();
		int h = imagem.getHeight() / getLINES();
		int index = 1;
		for (int l = 0; l < LINES; l++) {
			for (int c = 0; c < COLUMNS; c++) {
				Piece piece = new Piece(index, l, c, false);
				piece.setImg(new ImageIcon(imagem.getSubimage(c * w, l * h, w, h)));
				pieces.add(piece);
				index++;
			}
		}
	}

	private void associateNeighbors() {
		pieces.forEach(m -> {
			pieces.forEach(s -> s.addNeighbor(m));
		});
	}

	private void addEmpty() {
		pieces.stream().filter(e -> e.getIndex() == size()).findFirst().get().setEmpty(true);
	}

	public void shuffleTable(ShuffleListener listener) {
		try {
			Thread.sleep(1000);
			int animationTime = 2000;
			Random random = new Random();
			int numberExchange = 0;

			while (!isValidNumber(numberExchange)) {
				numberExchange = random.nextInt(0, size());
			}
			animationTime /= numberExchange;
			Set<Swap> swaps = new HashSet<>();

			while (swaps.size() < numberExchange) {
				Piece pieceOrigin = pieces.get(random.nextInt(size() - 1));
				Piece pieceDestiny = pieces.get(random.nextInt(size() - 1));

				if (!pieceOrigin.equals(pieceDestiny)) {
					Swap current = new Swap(pieceOrigin.getIndex(), pieceDestiny.getIndex());
					
					if (!swaps.contains(current)) {
						swaps.add(current);
						pieceOrigin.exchange(pieceDestiny);
						listener.updateButtons();
						Thread.sleep(animationTime);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isPair(int number) {
		return number % 2 == 0;
	}

	private boolean isValidNumber(int number) {
		return number != 0 && typeShuffle.equals(TypeShuffle.pairs) ? isPair(number) : !isPair(number);
	}

	public boolean completedPuzzle() {
		int value = 1;
		for (Piece piece : pieces) {
			if (piece.getIndex() != value++) {
				return false;
			}
		}
		return true;
	}

	public int size() {
		return getLINES() * getCOLUMNS();
	}

	public int getLINES() {
		return LINES;
	}

	public int getCOLUMNS() {
		return COLUMNS;
	}

	public List<Piece> getPieces() {
		return pieces;
	}
}