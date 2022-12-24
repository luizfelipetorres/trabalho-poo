package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

	@Override
	public String toString() {
		return "Player [playerUsername=" + playerUsername + ", playerEmail=" + playerEmail + "]";
	}

	public File getFile() {
		return file;
	}

	public File getFileOrDefault() {
		if(file == null) {
			return new File("img//users//defaultUsers.png");
		}
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void updateImage(File file) throws IOException {
		String extension = file.getPath().substring(file.getPath().length() - 3 );
        File outputfile = new File("img//users//"+getPlayerUsername()+"." + extension);
        if(this.file != null) this.file.delete();
        ImageIO.write(ImageIO.read(file), extension, outputfile);
        this.file = new File("img//users//"+getPlayerUsername()+"." + extension);
	}
	
	
	
	
}
