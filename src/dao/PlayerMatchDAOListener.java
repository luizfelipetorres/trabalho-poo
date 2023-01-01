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
	List<PlayerMatch> findAll(int page, int limit, boolean isComplete);
	List<PlayerMatch> findByMatchID(Long matchId, int limit , boolean isComplete);
	public int totalElement(boolean isComplete);
}
