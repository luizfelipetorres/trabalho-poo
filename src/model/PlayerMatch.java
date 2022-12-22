package model;

import java.time.LocalDateTime;

public class PlayerMatch {
	
	private int playerId;
	private int matchId;
	private int duration;
	private boolean isCompleted;
	private double playerPoints;
	
	public PlayerMatch(int playerId, int matchId, LocalDateTime startTime, LocalDateTime endTime, int duration, boolean isCompleted) {
		super();
		this.playerId = playerId;
		this.matchId = matchId;
		this.duration = duration;
		this.isCompleted = isCompleted;
		this.playerPoints = calculetePoints(duration);
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

	public int getPlayerId() {
		return playerId;
	}

	public int getMatchId() {
		return matchId;
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

}
