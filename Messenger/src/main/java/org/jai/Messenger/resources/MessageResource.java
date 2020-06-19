package org.jai.Messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jai.Messenger.beans.MessageFilterBean;
import org.jai.Messenger.component.Hateoas;
import org.jai.Messenger.model.Message;
import org.jai.Messenger.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MessageResource {

	MessageService messageService = new MessageService();
	Hateoas hateoas = new Hateoas();

	@GET
	public Response getMessages(@BeanParam MessageFilterBean filterBean) {

		if (filterBean.getYear() > 0) {

			List<Message> getAllMessagesforYear = messageService.getAllMessagesforYear(filterBean.getYear());
			GenericEntity<List<Message>> listofAllMessagesforYear = new GenericEntity<List<Message>>(
					getAllMessagesforYear) {
			};
			Response.ok().entity(listofAllMessagesforYear).build();
		}

		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {

			List<Message> getAllMessagesPaginated = messageService.getAllMessagePaginated(filterBean.getStart(),
					filterBean.getSize());
			GenericEntity<List<Message>> listofAllMessagesPaginated = new GenericEntity<List<Message>>(
					getAllMessagesPaginated) {
			};
			return Response.ok().entity(listofAllMessagesPaginated).build();
		}

		List<Message> getAllMessages = messageService.getAllMessages();
		GenericEntity<List<Message>> listofAllMessages = new GenericEntity<List<Message>>(getAllMessages) {
		};
		return Response.ok().entity(listofAllMessages).build();
	}

	@GET
	@Path("/{messageId}")
	public Response getMessage(@PathParam("messageId") long key, @Context UriInfo uriInfo) {
		Message newGetMessage = messageService.getMessage(key);
		String uri1 = hateoas.getUriForMessage(uriInfo, newGetMessage);
		String uri2 = hateoas.getMessageUriForProfile(uriInfo, newGetMessage);
		String uri3 = hateoas.getMessageUriForComments(uriInfo, newGetMessage);
		String uri4 = hateoas.getMessageUriForLikes(uriInfo, newGetMessage);
		String uri5 = hateoas.getMessageUriForShares(uriInfo, newGetMessage);

		newGetMessage.addLink(uri1, "self");
		newGetMessage.addLink(uri2, "profile");
		newGetMessage.addLink(uri3, "comments");
		newGetMessage.addLink(uri4, "likes");
		newGetMessage.addLink(uri5, "shares");

		return Response.ok().entity(newGetMessage).build();
	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(message.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Response updateMessage(@PathParam("messageId") long key, Message message) {
		message.setId(key);
		Message newUpdateMessage = messageService.updateMessage(message);
		return Response.ok().entity(newUpdateMessage).build();
	}

	@DELETE
	@Path("/{messageId}")
	public Response deleteMessage(@PathParam("messageId") long key) {
		messageService.removeMessage(key);
		return Response.noContent().build();
	}

	@Path("/{messageId}/cmts")
	public CommentSubResourceforMessage getCommentSubResourceforMessage() {
		return new CommentSubResourceforMessage();
	}

	@Path("/{messageId}/likes")
	public LikeSubResourceforMessage getLikeSubResourceforMessage() {
		return new LikeSubResourceforMessage();
	}

	@Path("/{messageId}/shares")
	public ShareSubResourceforMessage getShareSubResourceforMessage() {
		return new ShareSubResourceforMessage();
	}

}
