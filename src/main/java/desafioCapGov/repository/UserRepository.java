package desafioCapGov.repository;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;

import desafioCapGov.database.Database;
import desafioCapGov.entities.User;

@Path("/user")
public class UserRepository {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getPessoas() {
		Session session = Database.getSession();
		List<User> users = session.createQuery("from User").list();
		session.close();
		users.forEach(user -> user.setPassword(null));
		return users;
	}

}
