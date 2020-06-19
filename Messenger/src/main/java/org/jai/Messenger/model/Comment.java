package org.jai.Messenger.model;

import java.util.Date;

public class Comment {

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	private long id;
	private String message;
	private Date creationDate;
	private String author;

	public Comment() {
	}

	public Comment(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.author = author;
		this.creationDate = new Date();
	}

}
