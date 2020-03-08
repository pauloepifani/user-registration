package br.com.userregistration.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	private Connection() {
		
	}
	
	private static EntityManagerFactory getFactory() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("database");
		}
		return emf;
	}
	
	public static EntityManager getConnection() {
		Connection.getFactory();
		em = emf.createEntityManager();
		return em;
	}
	
	public static void closeConnection(EntityManager em) {
		if(em.isOpen()) {
			em.close();
		}
	}
	

}
