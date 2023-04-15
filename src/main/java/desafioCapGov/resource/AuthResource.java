package desafioCapGov.resource;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;

import desafioCapGov.database.Database;
import desafioCapGov.entities.User;

public class AuthResource {
	public AuthResource() {
	}

	public User getUserByEmail(String email) {
		try {
			Session session = Database.getSession();
			session.beginTransaction();
			Query query = session.createQuery("From User u where u.email = :email");
			query.setParameter("email", email);
			User u = (User) query.getSingleResult();
			session.getTransaction().commit();
			session.close();
			return u;

		} catch (NoResultException e) {
			return null;
		}

	}
}
