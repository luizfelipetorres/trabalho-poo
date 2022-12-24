package controller;

import java.io.File;
import java.util.Random;

import model.Match;
import model.Player;
import model.PlayerMatch;
import model.Puzzle;
import util.TypeShuffle;

public class test {
	
	
	public static void main(String[] args) throws Exception {
		
		int i = 0;
		Random random = new Random();
		
		Puzzle puzzle = PuzzleController.getInstance().findById(1l).get();
		Player player = PlayerController.getInstance().findById(1);
		Match match =  MatchController.getInstance().findById(1l).get();
		while (i!=60) {
			PlayerMatch playerMatch = new PlayerMatch(player, match, random.nextInt(0,100), random.nextInt(0,1000), true);
			PlayerMatchController.getInstance().save(playerMatch);
			i++;
		}
		
		while (i!=10) {
			PlayerMatch playerMatch = new PlayerMatch(player, match, random.nextInt(0,100), random.nextInt(0,1000), false);
			PlayerMatchController.getInstance().save(playerMatch);
			i++;
		}

	}
}
