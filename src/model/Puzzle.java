package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import interfaces.ShuffleListener;
import util.TypeShuffle;

public class Puzzle {
	private Long id;
	private final int LINES;
	private final int COLUMNS;
	private final List<Piece> pieces;
	private final TypeShuffle typeShuffle;
	private File file;

	public Puzzle(int lines, int columns, File file, TypeShuffle typeShuffle) throws IOException {
		this.LINES = lines;
		this.COLUMNS = columns;
		this.typeShuffle = typeShuffle;
		this.pieces = new ArrayList<Piece>();
		this.file = file;
		initialize();
	}
	
	public Puzzle( Long id, int lines, int columns, File file, TypeShuffle typeShuffle) throws IOException {
		this.id = id;
		this.LINES = lines;
		this.COLUMNS = columns;
		this.typeShuffle = typeShuffle;
		this.pieces = new ArrayList<Piece>();
		this.file = file;
		initialize();
	}
	
	private void initialize() throws IOException {
		generatepieces();
		associateNeighbors();
		addEmpty();
	}
	
	private void generatepieces() throws IOException {
		BufferedImage imagem = ImageIO.read(getFile());
		int w = imagem.getWidth() / this.getCOLUMNS();
		int h = imagem.getHeight() / this.getLINES();
		int index = 1;
		
		for (int l = 0; l < this.getLINES(); l++) {
			for (int c = 0; c < this.getCOLUMNS(); c++) {
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
		pieces.stream().filter(e -> e.getIndex() == this.getSize()).findFirst().get().setEmpty(true);
	}

	public void shuffleTable(ShuffleListener listener) {
		try {
			Thread.sleep(1000);
			int animationTime = 2000;
			Random random = new Random();
			int numberExchange = 0;

			while (!this.isValidNumber(numberExchange)) {
				numberExchange = random.nextInt(0, this.getSize());
			}
			animationTime /= numberExchange;
			Set<Swap> swaps = new HashSet<>();

			while (swaps.size() < numberExchange) {
				Piece pieceOrigin = pieces.get(random.nextInt(this.getSize() - 1));
				Piece pieceDestiny = pieces.get(random.nextInt(this.getSize() - 1));

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
	
	public void exchange(int origin,  int destiny) {
		if((origin < getSize() && origin >=0) && (destiny < getSize() && destiny >=0)   ) {
			Piece pieceOrigin = pieces.get(origin);
			Piece pieceDestiny = pieces.get(destiny);
			pieceOrigin.exchange(pieceDestiny);
		}
	}

	private boolean isPair(int number) {
		return number % 2 == 0;
	}

	private boolean isValidNumber(int number) {
		return number != 0 && typeShuffle.equals(TypeShuffle.pairs) ? this.isPair(number) : !this.isPair(number);
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

	private int getSize() {
		return this.getLINES() * this.getCOLUMNS();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public TypeShuffle getTypeShuffle() {
		return typeShuffle;
	}
}