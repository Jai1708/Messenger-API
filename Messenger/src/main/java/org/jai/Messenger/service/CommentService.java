package org.jai.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jai.Messenger.database.DatabaseClass;
import org.jai.Messenger.model.Comment;
import org.jai.Messenger.model.ErrorMessage;
import org.jai.Messenger.model.Message;
import org.jai.Messenger.model.Profile;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public List<Comment> getAllCommentsforMessage(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public List<Comment> getAllCommentsforProfile(String profileName) {
		Map<Long, Comment> comments = profiles.get(profileName).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getCommentforMessage(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "https://www.google.com");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(response);
		}
		return comment;
	}
	
	public Comment getCommentforProfile(String profileName, long commentId) {
		
		Profile profile = profiles.get(profileName);
		
		Map<Long, Comment> comments = profile.getComments();
		Comment comment = comments.get(commentId);
		return comment;
	}

	public Comment addCommentforMessage(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment addCommentforProfile(String profileName, Comment comment) {
		Map<Long, Comment> comments = profiles.get(profileName).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateCommentforMessage(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			System.out.println(comment.getId());
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateCommentforProfile(String profileName, Comment comment) {
		Map<Long, Comment> comments = profiles.get(profileName).getComments();
		if (comment.getId() <= 0) {
			System.out.println(comment.getId());
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment removeCommentforMessage(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
	public Comment removeCommentforProfile(String profileName, long commentId) {
		Map<Long, Comment> comments = profiles.get(profileName).getComments();
		return comments.remove(commentId);
	}

	public List<Comment> getAllCommentsForYear(long messageId, long year) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		List<Comment> commentsForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Comment comment : comments.values()) {
			cal.setTime(comment.getCreationDate());
			if (cal.get(Calendar.YEAR) == year) {
				commentsForYear.add(comment);
			}
		}

		return commentsForYear;
	}

	public List<Comment> getAllCommentsPaginated(long messageId, int start, int size) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		ArrayList<Comment> list = new ArrayList<Comment>(comments.values());
		if (start > list.size() || size > list.size()) {
			return new ArrayList<Comment>();
		}
		return list.subList(start, size);
	}

}
