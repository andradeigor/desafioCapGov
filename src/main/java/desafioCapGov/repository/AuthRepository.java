package desafioCapGov.repository;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import desafioCapGov.database.Database;
import desafioCapGov.entities.Auth;
import desafioCapGov.entities.ResponseError;
import desafioCapGov.entities.Token;
import desafioCapGov.entities.User;
import desafioCapGov.lib.MyJwt;

@Path("/auth")
public class AuthRepository {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(Auth credentials) {
		Session session = Database.getSession();
		session.beginTransaction();
		Query query = session.createQuery("from User u where u.email = :email");
		query.setParameter("email", credentials.getEmail());
		query.setMaxResults(1);
		List<User> userList = query.getResultList();
		session.getTransaction().commit();

		if (userList.size() <= 0) {
			return Response.status(404).entity(new ResponseError("Email ou senha inválidos!", 404)).build();
		}
		User user = userList.get(0);
		boolean passwordIsCorrect = BCrypt.checkpw(credentials.getPassword(), user.getPassword());

		if (!passwordIsCorrect) {
			return Response.status(404).entity(new ResponseError("Email ou senha inválidos!", 404)).build();
		}

		String jwt = MyJwt.encrypt(user.getUserID());
		Token token = new Token(jwt);

		return Response.ok().entity(token).build();

	}
}
