package model;

public class PlayerMatch {
	
	private Long id;
	private Player player;
	private Match match;
	private int duration;
	private double playerPoints;
	private boolean isComplete;
	

	public PlayerMatch(Long id, Player player, Match match, int duration, double playerPoints, boolean isComplete) {
		super();
		this.id = id;
		this.player = player;
		this.match = match;
		this.duration = duration;
		this.playerPoints = playerPoints;
		this.isComplete = isComplete;
	}
	
	public PlayerMatch(Player player, Match match, int duration, double playerPoints, boolean isComplete) {
		super();
		this.player = player;
		this.match = match;
		this.duration = duration;
		this.playerPoints = playerPoints;
		this.isComplete = isComplete;
	}

	public static float calculetePoints(int duration) {
		int minute = 60;
		float points = 0;
		
		if (duration < minute) {
			points = 1_000;
		} else if (duration < 10 * minute) {
			points = 100;
		} else if (duration < 100 * minute) {
			points = 10;
		}
		return points;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(double playerPoints) {
		this.playerPoints = playerPoints;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	
}
