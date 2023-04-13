package desafioCapGov.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Posts")
public class Post {
	@Id
	private String postID;
	@Column(name = "title")
	private String title;
	@Column(name = "content")
	private String content;
	@Column(name = "createdAt")
	private LocalDateTime createdAt;
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;
	@Column(name = "userID")
	private String userID;

	public Post() {
	}

	public Post(String title, String content) {
		super();
		this.postID = UUID.randomUUID().toString();
		this.title = title;
		this.content = content;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public Post(String title, String content, String userId) {
		super();
		this.postID = UUID.randomUUID().toString();
		this.title = title;
		this.content = content;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.userID = userId;
	}

	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUserId() {
		return userID;
	}

	public void setUserId(String userId) {
		this.userID = userId;
	}

	@Override
	public String toString() {
		return "Post [postID=" + postID + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", userId=" + userID + "]";
	}

}
