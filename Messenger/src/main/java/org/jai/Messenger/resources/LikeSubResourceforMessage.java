package org.jai.Messenger.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jai.Messenger.model.Like;
import org.jai.Messenger.service.LikeService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path(value = "/msglikes")
public class LikeSubResourceforMessage {

	LikeService likeService = new LikeService();

	@GET
	public List<Like> getAllLikesforMessage(@PathParam("messageId") long messageId,@QueryParam("year") long year,@QueryParam("start") int start,@QueryParam("size") int size) {
		if (year > 0) {

			return likeService.getAllLikesforYear(messageId, year);
		}

		if (start >= 0 && size> 0) {

			return likeService.getAllLikesPaginated(messageId, start, size);
		}
		return likeService.getAllLikes(messageId);
	}

	@GET
	@Path("/{likeId}")
	public Like getLikeforMessage(@PathParam("messageId") long messageId, @PathParam("likeId") long likeId) {
		return likeService.getLike(messageId, likeId);
	}

	@POST
	public Like addLikeforMessage(@PathParam("messageId") long messageId, Like like) {
		return likeService.addLike(messageId, like);
	}

	@PUT
	@Path("/{likeId}")
	public Like updateLikeforMessage(@PathParam("messageId") long messageId, @PathParam("likeId") long id,
			Like like) {
		like.setId(id);
		return likeService.updateLike(messageId, like);
	}

	@DELETE
	@Path("/{likeId}")
	public void deleteLikeforMessage(@PathParam("messageId") long messageId, @PathParam("likeId") long likeId) {
		likeService.removeLike(messageId, likeId);
	}
}
