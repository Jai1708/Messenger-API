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
import javax.ws.rs.core.MediaType;
import org.jai.Messenger.model.Share;
import org.jai.Messenger.service.ShareService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path(value = "/msgshares")
public class ShareSubResourceforMessage {

	ShareService shareService = new ShareService();

	@GET
	public List<Share> getAllSharesforMessage(@PathParam("messageId") long messageId) {
			return shareService.getAllShares(messageId);
	}

	@GET
	@Path("/{shareId}")
	public Share getShareforMessage(@PathParam("messageId") long messageId, @PathParam("shareId") long shareId) {
		return shareService.getShare(messageId, shareId);
	}

	@POST
	public Share addShareforMessage(@PathParam("messageId") long messageId, Share share) {
		return shareService.addShare(messageId, share);
	}

	@PUT
	@Path("/{shareId}")
	public Share updateShareforMessage(@PathParam("messageId") long messageId, @PathParam("shareId") long shareId,
			Share share) {
		share.setId(shareId);
		return shareService.updateShare(messageId, share);
	}

	@DELETE
	@Path("/{shareId}")
	public void deleteShareforMessage(@PathParam("messageId") long messageId, @PathParam("shareId") long shareId) {
		shareService.removeShare(messageId, shareId);
	}
}
