package desafioCapGov.repository;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import desafioCapGov.annotations.Logged;
import desafioCapGov.entities.ResponseError;
import desafioCapGov.entities.User;
import desafioCapGov.resource.UserResource;

@Path("/user")
public class UserRepository {

	private UserResource userResource = new UserResource();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Logged
	public Response getPessoas(@Context HttpHeaders headers) {
		String userID = headers.getHeaderString("userID");
		User user = userResource.getUserById(userID);
		if (user == null) {
			Response.status(404).entity(new ResponseError("User not exist!", 404)).build();
		}
		return Response.ok().entity(user).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User u) {
		User user = new User(u.getName(), u.getEmail(), u.getPassword());
		try {
			userResource.CreateUser(user);
			user.setPassword(null);
			return Response.status(201).entity(user).build();
		} catch (Exception e) {
			return Response.status(400).entity(new ResponseError("Email already in use", 400)).build();
		}
	}
}
