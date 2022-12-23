package dao;

import java.util.List;

import model.Match;

public interface MatchDAOListener {
	
	public Match save(Match match);
	public void update(Match match);
	public Match findById(Long id);
	public void remove(Long id);
	public List<Match> findAll();
}
