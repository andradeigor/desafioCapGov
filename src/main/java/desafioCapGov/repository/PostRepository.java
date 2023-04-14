package desafioCapGov.repository;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	public Response getAllPosts(@Context HttpHeaders headers) {
		String userID = headers.getHeaderString("userID");
		Session session = Database.getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Post p where p.userID = :userID");
		query.setParameter("userID", userID);
		List<Post> posts = query.getResultList();
		session.getTransaction().commit();
		return Response.ok().entity(posts).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostByID(@Context HttpHeaders headers, @PathParam("id") String postID) {
		String userID = headers.getHeaderString("userID");
		Session session = Database.getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Post p where p.userID = :userID and p.postID = :postID");
		query.setParameter("userID", userID);
		query.setParameter("postID", postID);
		query.setMaxResults(1);
		List<Post> posts = query.getResultList();
		session.getTransaction().commit();
		if (posts.size() < 1) {
			return Response.status(404).build();
		}
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
