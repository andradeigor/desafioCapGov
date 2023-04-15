package desafioCapGov.resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import desafioCapGov.database.Database;
import desafioCapGov.entities.User;

public class UserResource {

	public UserResource() {
	}

	public void CreateUser(User u) {
		try {
			Session session = Database.getSession();
			session.beginTransaction();
			session.save(u);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println("alo dei erro");
		}

	}

	public User getUserById(String userID) {
		Session session = Database.getSession();
		session.beginTransaction();
		User u = session.get(User.class, userID);
		session.getTransaction().commit();
		u.setPassword(null);
		return u;
	}
}
