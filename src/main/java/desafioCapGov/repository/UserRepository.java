package desafioCapGov.repository;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;

import desafioCapGov.database.Database;
import desafioCapGov.entities.User;

@Path("/user")
public class UserRepository {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPessoas() {
		Session session = Database.getSession();
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("from User").list();
		session.close();
		users.forEach(user -> user.setPassword(null));
		return Response.ok().entity(users).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User u) {
		User user = new User(u.getName(), u.getEmail(), u.getPassword());
		Session session = Database.getSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		user.setPassword(null);
		return Response.status(201).entity(user).build();
	}
}
