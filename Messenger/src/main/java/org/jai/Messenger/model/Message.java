package org.jai.Messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Message") 
public class Message {

	private long id;
	private String message;
	private Date creationDate;
	private String author;
	private static Map<Long, Comment> comments = new HashMap<>();
	private static Map<Long, Like> likes = new HashMap<>();
	private static Map<Long, Share> shares = new HashMap<>();
	public static Map<Long, Share> getShares() {
		return shares;
	}

	public static void setShares(Map<Long, Share> shares) {
		Message.shares = shares;
	}

	public static Map<Long, Like> getLikes() {
		return likes;
	}

	public static void setLikes(Map<Long, Like> likes) {
		Message.likes = likes;
	}

	private List<Link> links = new ArrayList<>();
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public Message() {}
	
	public Message(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.creationDate = new Date();
		this.author = author;
	}

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
	
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}

	public void addLink(String url,String rel)
	{
		Link link = new Link();	
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	} 
}
