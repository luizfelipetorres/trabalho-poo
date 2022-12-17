package dao;

import java.awt.HeadlessException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Player;

public class PlayerDAO implements PlayerDAOListener{
	
	private static PlayerDAO instance;
	
	private PlayerDAO() {
    }
    
    public static PlayerDAO getInstance(){
        if(instance == null){
            instance = new PlayerDAO();
        }
        return instance;
    }

	@Override
	public void save(Player player) {
		EntityManager entityManager = ConnectionFactory.getConnection();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(player);
            entityManager.getTransaction().commit();
            
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Save" + ex);
            entityManager.getTransaction().rollback();
        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Registro duplicado!" + ex);
        } finally {
            entityManager.close();
        }
	}

	@Override
	public void update(Player player) {
		EntityManager entityManager = ConnectionFactory.getConnection();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(player);
            entityManager.getTransaction().commit();
            
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Update" + ex);
            entityManager.getTransaction().rollback();
        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(null, "Registro duplicado ou nulo || log do erro: " + ex);
        } finally {
            entityManager.close();
        }
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Player> findAll() {
		EntityManager entityManager = ConnectionFactory.getConnection();
        List<Player> players = null;
        
        try {
        	players = entityManager.createNamedQuery("Player.findAll").getResultList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "FindAll" + ex);
        } finally {
            entityManager.close();
        }
        return players;
	}

	@Override
	public Player findById(Integer integer) {
		EntityManager entityManager = ConnectionFactory.getConnection();
		Player player = null;
        
        try {
        	player = entityManager.find(Player.class, integer);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "FindById" + ex);
        } finally {
            entityManager.close();
        }
        return player;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Player> findByName(String playerName) {
		EntityManager entityManager = ConnectionFactory.getConnection();
        List<Player> clientes = null;
    
        try {
            Query query = entityManager.createNamedQuery("Cliente.findByPlayerName", Player.class);
            query.setParameter("playerName", playerName);
            
            clientes = query.getResultList();
            
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "findByPlayerName" + ex);
        } finally {
            entityManager.close();
        }
        return clientes;
	}

	@Override
	public void remove(Integer integer) {
EntityManager entityManager = ConnectionFactory.getConnection();
        
        try {
        	Player player = entityManager.find(Player.class, integer);
            entityManager.getTransaction().begin();
            entityManager.remove(player);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Remove" + ex);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
	}
}
