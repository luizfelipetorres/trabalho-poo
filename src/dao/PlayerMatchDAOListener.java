package dao;

import java.util.List;

import model.PlayerMatch;

public interface PlayerMatchDAOListener {
	
	public PlayerMatch save(PlayerMatch playerMatch);
	public void update(PlayerMatch playerMatch);
	public PlayerMatch findById(Long id);
	public List<PlayerMatch> findAll();
	public void remove(Long id);

}
