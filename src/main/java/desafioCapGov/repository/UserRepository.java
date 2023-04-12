package desafioCapGov.repository;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;

import desafioCapGov.annotations.Logged;
import desafioCapGov.database.Database;
import desafioCapGov.entities.ResponseError;
import desafioCapGov.entities.User;

@Path("/user")
public class UserRepository {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Logged
	public Response getPessoas(ContainerRequestContext request) {
		Session session = Database.getSession();
		session.beginTransaction();
		Query query = session.createQuery("from User u where u.userID = :userID");
		query.setParameter("userID", request.getHeaderString("userId"));
		query.setMaxResults(1);
		List<User> userList = query.getResultList();
		session.getTransaction().commit();

		if (userList.size() <= 0) {
			return Response.status(404).entity(new ResponseError("Email ou senha inválidos!", 404)).build();
		}
		User user = userList.get(0);
		user.setPassword(null);
		return Response.ok().entity(user).build();
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
