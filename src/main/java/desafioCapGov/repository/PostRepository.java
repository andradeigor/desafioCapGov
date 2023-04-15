package desafioCapGov.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import desafioCapGov.annotations.Logged;
import desafioCapGov.entities.Post;
import desafioCapGov.entities.ResponseError;
import desafioCapGov.resource.PostResource;

@Path("/posts")
@Logged
public class PostRepository {
	private PostResource postResource = new PostResource();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPosts(@Context HttpHeaders headers) {
		String userID = headers.getHeaderString("userID");
		List<Post> posts = postResource.getPostsByUser(userID);
		return Response.ok().entity(posts).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPostByID(@Context HttpHeaders headers, @PathParam("id") String postID) {
		String userID = headers.getHeaderString("userID");
		Post p = postResource.getPostByID(postID);
		if (p == null || !(p.getUserId().equals(userID))) {
			return Response.status(404).entity(new ResponseError("Post not found!", 404)).build();
		}
		return Response.ok().entity(p).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPost(Post p, @Context HttpHeaders headers) {
		String userID = headers.getHeaderString("userID");
		Post post = new Post(p.getTitle(), p.getContent(), userID);
		postResource.createPost(post);
		return Response.status(201).entity(post).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response updatePost(Post updatedPost, @Context HttpHeaders headers, @PathParam("id") String postID) {
		String userID = headers.getHeaderString("userID");
		Post p = postResource.getPostByID(postID);
		if (p == null || !(p.getUserId().equals(userID))) {
			return Response.status(404).entity(new ResponseError("Post not found", 404)).build();
		}
		p.setContent(updatedPost.getContent());
		p.setTitle(updatedPost.getTitle());
		p.setUpdatedAt(LocalDateTime.now());
		postResource.updatePost(p);
		return Response.ok().build();

	}

	@DELETE
	@Path("/{id}")
	public Response deletePost(@PathParam("id") String postID, @Context HttpHeaders headers) {
		String userID = headers.getHeaderString("userID");
		Post p = postResource.getPostByID(postID);
		if (p.getUserId().equals(userID)) {
			postResource.deletePost(p);
			return Response.ok().build();
		} else {
			return Response.status(403).entity(new ResponseError("You can only delete your posts!!", 403)).build();
		}

	}
}
