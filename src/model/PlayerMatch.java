package model;

public class PlayerMatch {
	
	private Long id;
	private Player player;
	private Match match;
	private Long duration;
	private boolean isCompleted;
	private double playerPoints;
	

	public PlayerMatch(Player player, Match match, Long duration, boolean isCompleted) {
		super();
		this.player = player;
		this.match = match;
		this.duration = duration;
		this.isCompleted = isCompleted;
		this.playerPoints = calculetePoints(duration);
	}
	
	public PlayerMatch(Long id, Player player, Match match, Long duration, double playerPoints, boolean isCompleted) {
		super();
		this.id = id;
		this.player = player;
		this.match = match;
		this.duration = duration;
		this.playerPoints = playerPoints;
		this.isCompleted = isCompleted;
	}

	public static float calculetePoints(Long duration) {
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public double getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(double playerPoints) {
		this.playerPoints = playerPoints;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isComplete) {
		this.isCompleted = isComplete;
	}

	
}
