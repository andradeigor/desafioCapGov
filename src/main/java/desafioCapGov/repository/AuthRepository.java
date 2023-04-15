package desafioCapGov.repository;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mindrot.jbcrypt.BCrypt;

import desafioCapGov.entities.Auth;
import desafioCapGov.entities.ResponseError;
import desafioCapGov.entities.Token;
import desafioCapGov.entities.User;
import desafioCapGov.lib.MyJwt;
import desafioCapGov.resource.AuthResource;

@Path("/auth")
public class AuthRepository {
	private AuthResource authResource = new AuthResource();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(Auth credentials) {
		User user = authResource.getUserByEmail(credentials.getEmail());
		if (user == null) {
			return Response.status(404).entity(new ResponseError("Email ou senha inválidos!", 404)).build();
		}

		boolean passwordIsCorrect = BCrypt.checkpw(credentials.getPassword(), user.getPassword());

		if (!passwordIsCorrect) {
			return Response.status(404).entity(new ResponseError("Email ou senha inválidos!", 404)).build();
		}

		String jwt = MyJwt.encrypt(user.getUserID());
		Token token = new Token(jwt);

		return Response.ok().entity(token).build();

	}
}
