package br.com.userregistration.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.userregistration.exception.EmailAlreadyExistException;
import br.com.userregistration.model.User;

public class UserDao {

	private static UserDao userDao;
	private static EntityManager em;

	private UserDao() {

	}

	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDao();
			em = Connection.getConnection();
		}
		if (!em.isOpen()) {
			em = Connection.getConnection();
		}
		return userDao;
	}

	public User findUserById(int id) {

		User user = em.find(User.class, id);
		Connection.closeConnection(em);
		return user;
	}

	public List<User> findAllUsers() {

		@SuppressWarnings("unchecked")
		List<User> users = em.createNativeQuery("SELECT * FROM User", User.class).getResultList();

		Connection.closeConnection(em);
		return users;
	}

	public User findUserLogin(String email, String password) {

		User user = (User) em.createNativeQuery(
				"SELECT * FROM User WHERE email = \"" + email + "\" AND password = \"" + password + "\"", User.class)
				.getSingleResult();
		Connection.closeConnection(em);
		return user;

	}
	
	public User findUserByEmail(String email, int id) throws EmailAlreadyExistException {
		
		User user = (User) em.createNativeQuery(
				"SELECT * FROM User WHERE email = \"" + email + "\" AND id <> \"" + id + "\"", User.class).getSingleResult();
		
		if(user != null) {
			throw new EmailAlreadyExistException("E-mail já cadastrado!");
		} else {
			
			return user;
		}
	}
	
	public void saveUser(User user) {

		try {

			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();

		} catch (RuntimeException e) {
			
			em.getTransaction().rollback();
			
		} finally {

			Connection.closeConnection(em);
		}
	}

	public void updateUser(User user) {

		try {

			EntityManager em = Connection.getConnection();
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();

		} catch (RuntimeException e) {

			em.getTransaction().rollback();

		} finally {

			Connection.closeConnection(em);
		}
	}

	public void deleteUser(int id) {

		User user = findUserById(id);
		System.out.println(user.getName());

		try {
			
			em = Connection.getConnection();
			em.getTransaction().begin();
			em.remove(em.getReference(User.class, user.getId()));
			em.getTransaction().commit();

		} catch (Exception e) {

			em.getTransaction().rollback();
		
		} finally {
			
			Connection.closeConnection(em);
		}
	}

}
