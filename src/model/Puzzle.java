package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import util.TypeShuffle;

public class Puzzle {

	private final int LINES;
	private final int COLUMNS;
	private final List<Piece> pieces = new ArrayList<>();
	
	public Puzzle(int lines , int columns, TypeShuffle typeShuffle) {
		this.LINES = lines;
		this.COLUMNS = columns;
		generatepieces();
		associateNeighbors();
		addEmpty();
		shuffleTable(typeShuffle);
	}
	
	public Puzzle(int lines , int columns, File file ,TypeShuffle typeShuffle) throws IOException {
		this.LINES = lines;
		this.COLUMNS = columns;
		generatepieces(file);
		associateNeighbors();
		addEmpty();
		shuffleTable(typeShuffle);
	}

	private void generatepieces() {
		int index = 1;
		for(int l = 0 ; l<LINES;l++) {
			for(int c=0 ; c<COLUMNS; c++) {
				Piece piece = new Piece(index, l, c, false);
				piece.setImg(new ImageIcon ("img\\numero-"+index+".png"));
				pieces.add(piece);
				index++;
			}
		}
	}
	
	private void generatepieces( File file) throws IOException {
		
		BufferedImage imagem = ImageIO.read(file);
		int w = imagem.getWidth()/getCOLUMNS();
		int h = imagem.getHeight()/getLINES();
		int index = 1;
		for(int l = 0 ; l<LINES;l++) {
			for(int c=0 ; c<COLUMNS; c++) {
				Piece piece = new Piece(index, l, c, false);
				piece.setImg(new ImageIcon (imagem.getSubimage( c*w,l*h,w,h)));
				pieces.add(piece);
				index++;
			}
		}
	}
	
	private void associateNeighbors() {
		pieces.forEach(m ->{
			pieces.forEach(s->s.addNeighbor(m));
		});
	}
	
	private void addEmpty() {
		pieces.stream().filter(e -> e.getIndex() == size()).findFirst().get().setEmpty(true);
	}
	
	
	private void shuffleTable(TypeShuffle typeShuffle) {
		Random random = new Random();
		int numberExchange = 0;
		int value = 1;

		if (typeShuffle.equals(TypeShuffle.pairs)) {
			while (numberExchange == 0 || numberExchange % 2 != 0) {
				numberExchange = random.nextInt(0, size()/2+1);
			}
		}

		if (typeShuffle.equals(TypeShuffle.odd)) {
			while (numberExchange == 0 || numberExchange % 2 == 0) {
				numberExchange = random.nextInt(0, size()/2+1);
			}
		}
		
		Map< Integer, Integer> map = new HashMap<>();

		while (value<=numberExchange) {
			Piece pieceOrigin = pieces.get(random.nextInt(0, size()-1));
			Piece pieceDestiny = pieces.get(random.nextInt(0, size()-1));
			
			if(!pieceOrigin.equals(pieceDestiny)){
				
				boolean valid = true;
				
				for (Integer key : map.keySet()) {

                   if(key == pieceOrigin.getIndex() && map.get(key) == pieceDestiny.getIndex()) valid = false;
                   if(key == pieceDestiny.getIndex() && map.get(key) == pieceOrigin.getIndex()) valid = false;
				}
				
				if(valid){
					map.put(pieceOrigin.getIndex(), pieceDestiny.getIndex());
					pieceOrigin.exchange(pieceOrigin, pieceDestiny);
					value++;
				}
			}
		}
	}
	
	public boolean completedPuzzle() {

		int value = 1;
		
		for (Piece piece : pieces) {
			if(piece.getIndex() != value++) {
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