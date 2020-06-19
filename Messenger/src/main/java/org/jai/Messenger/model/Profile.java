package org.jai.Messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Profile")
public class Profile {

	private long id;
	private String profileName;
	private String firstName;
	private String lastName;
	private Date created;

	private static Map<Long, Comment> comments = new HashMap<>();
	private static Map<Long, Like> likes = new HashMap<>();
	private static Map<Long, Share> shares = new HashMap<>();
	
	public Profile() {}
	
	public Profile(long id,String profileName, String firstName, String lastName) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public static Map<Long, Comment> getComments() {
		return comments;
	}
	public static void setComments(Map<Long, Comment> comments) {
		Profile.comments = comments;
	}
	public static Map<Long, Like> getLikes() {
		return likes;
	}
	public static void setLikes(Map<Long, Like> likes) {
		Profile.likes = likes;
	}
	public static Map<Long, Share> getShares() {
		return shares;
	}
	public static void setShares(Map<Long, Share> shares) {
		Profile.shares = shares;
	}
	
private List<Link> links = new ArrayList<>();
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String url,String rel)
	{
		Link link = new Link();	
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
}
