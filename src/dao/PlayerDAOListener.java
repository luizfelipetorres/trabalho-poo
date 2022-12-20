package dao;

import java.util.List;

import model.Player;

public interface PlayerDAOListener {
	
	public Player authenticate(Player player);
	public boolean save(Player player);
    public void update(Player player);
    public List<Player> findAll();
    public Player findById(Integer integer);
    public List<Player> findByName(String name);
    public void remove(Integer integer);
    
}
