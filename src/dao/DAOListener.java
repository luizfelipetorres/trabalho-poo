package dao;

import java.util.List;

public interface DAOListener {
	
	public <T> T save(T object);
	public <T> void update(T object);
	public <T> T findById(Long id);
	public <T> List<T> findAll();
	public void remove(Long id);
	
}
