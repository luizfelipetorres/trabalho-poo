package business;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Puzzle puzzle = new Puzzle(2, TypeShuffle.pairs);
		
		puzzle.show();
		
		while (true) {
			int l1 = sc.nextInt();
			int c1 =  sc.nextInt();
			int l2 = sc.nextInt();
			int c2 =  sc.nextInt();
			puzzle.movePieces(l1, c1, l2, c2);
			puzzle.show();
			
			System.out.println("-------------------------------------------------------");
		}
		
	}
}
