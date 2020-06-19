package org.jai.Messenger.model;

import java.util.Date;

public class Like {

	private long id;
	private Date creationDate;
	private String author;

	public Like() {
	}

	public Like(long id, String author) {
		super();
		this.id = id;
		this.author = author;
		this.creationDate = new Date();

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
