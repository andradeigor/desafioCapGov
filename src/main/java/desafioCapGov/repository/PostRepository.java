package desafioCapGov.repository;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;

import desafioCapGov.annotations.Logged;
import desafioCapGov.database.Database;
import desafioCapGov.entities.Post;

@Path("/posts")
@Logged
public class PostRepository {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPosts(ContainerRequestContext request) {
		String userID = request.getHeaderString("userID");
		Session session = Database.getSession();
		session.beginTransaction();
		List<Post> posts = session.createQuery("from Post").list();
		session.getTransaction().commit();
		return Response.ok().entity(posts).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPost(Post p, @Context HttpHeaders headers) {
		String userID = headers.getHeaderString("userID");
		Post post = new Post(p.getTitle(), p.getContent(), userID);
		Session session = Database.getSession();
		session.beginTransaction();
		session.save(post);
		session.getTransaction().commit();
		session.close();
		return Response.status(201).entity(post).build();
	}
}
