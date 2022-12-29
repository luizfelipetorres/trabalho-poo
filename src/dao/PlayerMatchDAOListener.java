package dao;

import java.util.List;

import model.PlayerMatch;
import util.RecordPlayerMatch;

public interface PlayerMatchDAOListener {
	
	public PlayerMatch save(PlayerMatch playerMatch);
	public void update(PlayerMatch playerMatch);
	public PlayerMatch findById(Long id);
	public List<PlayerMatch> findAll();
	public void remove(Long id);
	public RecordPlayerMatch recordPlayerMatchByPlayer(Integer idPlayer);

}
