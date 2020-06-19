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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jai.Messenger.model.Comment;
import org.jai.Messenger.service.CommentService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path(value = "/procomments")
public class CommentSubResourceforProfile {

	CommentService commentService = new CommentService();

	

	@GET
	public List<Comment> getAllCommentsforProfile(@PathParam("profileName") String profileName) {

		return commentService.getAllCommentsforProfile(profileName);
	}

	@GET
	@Path("/{commentId}")
	public Response getCommentforProfile(@PathParam("profileName") String profileName,
			@PathParam("commentId") long commentId) {
		Comment comment = commentService.getCommentforProfile(profileName, commentId);
		return Response.ok().entity(comment).build();
	}

	
	
	@POST
	public Response addCommentforProfile(@PathParam("profileName") String profileName, Comment comment,
			@Context UriInfo uriInfo) {
		Comment newComment = commentService.addCommentforProfile(profileName, comment);
		String newId = String.valueOf(comment.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newComment).build();
	}

	

	@PUT
	@Path("/{commentId}")
	public Response updateCommentforProfile(@PathParam("profileName") String profileName,
			@PathParam("commentId") long id, Comment comment) {
		comment.setId(id);
		Comment newComment = commentService.updateCommentforProfile(profileName, comment);
		return Response.ok().entity(newComment).build();
	}

	
	@DELETE
	@Path("/{commentId}")
	public Response deleteCommentforProfile(@PathParam("profileName") String profileName,
			@PathParam("commentId") long commentId) {
		commentService.removeCommentforProfile(profileName, commentId);
		return Response.noContent().build();
	}
}
