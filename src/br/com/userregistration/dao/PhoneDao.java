package br.com.userregistration.dao;


import javax.persistence.EntityManager;

import br.com.userregistration.model.PhoneNumber;

public class PhoneDao {
	
	private static PhoneDao phoneDao;
	private static EntityManager em;
	
	private PhoneDao() {
		
	}
	
	public static PhoneDao getInstance() {
		if(phoneDao == null) {
			phoneDao = new PhoneDao();
			em = Connection.getConnection();
		}
		if(!em.isOpen()) {
			em = Connection.getConnection();
		}
		return phoneDao;
	}
	
	public void deletePhonesByUserId(int userId) {
		System.out.println("\n\nDELETE\n\n");
		em.getTransaction().begin();
		System.out.println("\n\nDELETE before\n\n");
		em.createNativeQuery("DELETE FROM PhoneNumber WHERE user_id = \"" + userId + "\"", PhoneNumber.class).executeUpdate();
		System.out.println("\n\nDELETE after\n\n");
		em.getTransaction().commit();
		Connection.closeConnection(em);
	}
	
}
