package org.jai.Messenger.resources;

import java.net.URI;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jai.Messenger.model.Comment;
import org.jai.Messenger.service.CommentService;

@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
@Path(value = "/msgcomments")
public class CommentSubResourceforMessage {

	CommentService commentService = new CommentService();

	@GET
	public List<Comment> getAllCommentsforMessage(@PathParam("messageId") long messageId, @QueryParam("year") long year,
			@QueryParam("start") int start, @QueryParam("size") int size) {
		if (year > 0) {

			return commentService.getAllCommentsForYear(messageId, year);
		}

		if (start >= 0 && size > 0) {

			return commentService.getAllCommentsPaginated(messageId, start, size);
		}
		return commentService.getAllCommentsforMessage(messageId);
	}

	

	@GET
	@Path("/{commentId}")
	public Response getCommentforMessage(@PathParam("messageId") long messageId,
			@PathParam("commentId") long commentId) {
		Comment comment = commentService.getCommentforMessage(messageId, commentId);
		return Response.ok().entity(comment).build();
	}


	@POST
	public Response addCommentforMessage(@PathParam("messageId") long messageId, Comment comment,
			@Context UriInfo uriInfo) {
		Comment newComment = commentService.addCommentforMessage(messageId, comment);
		String newId = String.valueOf(comment.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newComment).build();
	}


	@PUT
	@Path("/{commentId}")
	public Response updateCommentforMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long id,
			Comment comment) {
		comment.setId(id);
		Comment newComment = commentService.updateCommentforMessage(messageId, comment);
		return Response.ok().entity(newComment).build();
	}

	
	@DELETE
	@Path("/{commentId}")
	public Response deleteCommentforMessage(@PathParam("messageId") long messageId,
			@PathParam("commentId") long commentId) {
		commentService.removeCommentforMessage(messageId, commentId);
		return Response.noContent().build();
	}

	
}
