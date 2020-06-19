package org.jai.Messenger.model;

import java.util.Date;

public class Share {

	private long id;
	private Date creationDate;
	private String author;

	public Share() {
	}

	public Share(long id, String author) {
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
