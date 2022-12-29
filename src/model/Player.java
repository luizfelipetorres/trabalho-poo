package model;

import java.io.File;

public class Player {

    private Integer playerId;
    private String playerUsername;
    private String playerEmail;
    private String playerPassword;
    private File file;
    
    public Player() {
    }
    
    public Player(String playerUsername, String playerPassword) {
        this.playerUsername = playerUsername;
        this.playerPassword = playerPassword;
    }

    public Player(String playerUsername, String playerEmail, String playerPassword) {
        this.playerUsername = playerUsername;
        this.playerEmail = playerEmail;
        this.playerPassword = playerPassword;
    }

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getPlayerUsername() {
		return playerUsername;
	}

	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public String getPlayerPassword() {
		return playerPassword;
	}

	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
