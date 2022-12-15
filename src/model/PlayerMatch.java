package model;

import java.time.LocalDateTime;

public class PlayerMatch {
	
	private int playerId;
	private int matchId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double playerPoints;
	
	public PlayerMatch(int playerId, int matchId, LocalDateTime startTime, LocalDateTime endTime, double playerPoints) {
		super();
		this.playerId = playerId;
		this.matchId = matchId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.playerPoints = playerPoints;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getMatchId() {
		return matchId;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public double getPlayerPoints() {
		return playerPoints;
	}

}
