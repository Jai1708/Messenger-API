package org.jai.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jai.Messenger.database.DatabaseClass;
import org.jai.Messenger.model.Like;
import org.jai.Messenger.model.Message;
import org.jai.Messenger.model.Profile;

public class LikeService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	private static Map<String, Profile> profiles = new HashMap<>();

	public List<Like> getAllLikes(long messageId) {
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		return new ArrayList<Like>(likes.values());
	}
	
	public Like getLike(long messageId, long likeId) {
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		Like like = likes.get(likeId);
		return like;
	}

	public Like addLike(long messageId, Like like) {
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		like.setId(likes.size() + 1);
		likes.put(like.getId(), like);
		return like;
	}

	public Like updateLike(long messageId, Like like) {
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		if (like.getId() <= 0) {
			System.out.println(like.getId());
			return null;
		}
		likes.put(like.getId(), like);
		return like;
	}

	public Like removeLike(long messageId, long likeId) {
		Map<Long, Like> likes= messages.get(messageId).getLikes();
		return likes.remove(likeId);
	}

	public List<Like> getAllLikesforYear(long messageId, long year) {
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		List<Like> likesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Like like : likes.values()) {
			cal.setTime(like.getCreationDate());
			if (cal.get(Calendar.YEAR) == year) {
				likesForYear.add(like);
			}
		}

		return likesForYear;
	}

	public List<Like> getAllLikesPaginated(long messageId, int start, int size) {
		Map<Long, Like> likes = messages.get(messageId).getLikes();
		ArrayList<Like> list = new ArrayList<Like>(likes.values());
		if (start > list.size() || size > list.size()) {
			return new ArrayList<Like>();
		}
		return list.subList(start, size);
	}

}
