package model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_usuario")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findByPlayerName", query = "SELECT p FROM Player p WHERE p.playerName LIKE CONCAT(:playerName,'%')"),
    @NamedQuery(name = "Player.findByAuthentication", query = "SELECT p FROM Player p WHERE p.playerEmail = :playerEmail AND p.playerPassword = :playerPassword")})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PLAYER_ID")
    private Integer playerId;
    @NotNull(message = "Name cannot be empty!")
    @Column(name = "PLAYER_NAME", unique = true, nullable = false)
    private String playerName;
    @NotNull(message = "Email cannot be empty!")
    @Column(name = "PLAYER_EMAIL", unique = true, nullable = false)
    private String playerEmail;
    @NotNull(message = "Password cannot be empty!")
    @Column(name = "PLAYER_PASSWORD", unique = true, nullable = false)
    private String playerPassword;

    public Player() {
    }

    public Player(Integer playerId) {
        this.playerId = playerId;
    }

    public Player(Integer playerId, String playerName, String playerEmail, String playerPassword) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerEmail = playerEmail;
        this.playerPassword = playerPassword;
    }

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
  
}
