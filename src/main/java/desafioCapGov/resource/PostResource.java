package desafioCapGov.resource;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import desafioCapGov.database.Database;
import desafioCapGov.entities.Post;

public class PostResource {
	public PostResource() {
	}

	public void createPost(Post p) {
		Session session = Database.getSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
	}

	public Post getPostByID(String postID) {
		Session session = Database.getSession();
		session.beginTransaction();
		Post p = session.get(Post.class, postID);
		session.getTransaction().commit();
		session.close();
		return p;
	}

	public List<Post> getPostsByUser(String userID) {
		Session session = Database.getSession();
		session.beginTransaction();
		Query query = session.createQuery("from Post p where p.userID = :userID");
		query.setParameter("userID", userID);
		@SuppressWarnings("unchecked")
		List<Post> posts = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return posts;
	}

	public void updatePost(Post p) {
		Session session = Database.getSession();
		session.beginTransaction();
		session.update(p);
		session.getTransaction().commit();
		session.close();
	}

	public void deletePost(Post p) {
		Session session = Database.getSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();

	}
}
