package interfaces;

import java.util.List;

public interface DAOListener<T> {
	
	public boolean save(T object);
	public boolean update(T object);
	public List<T> findAll();
	public T findById(Long id);
	
}
