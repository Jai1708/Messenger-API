package org.jai.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.jai.Messenger.model.Share;

public class ShareService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Share> getAllShares(long messageId) {
		Map<Long, Share> shares = messages.get(messageId).getShares();
		return new ArrayList<Share>(shares.values());
	}

	public Share getShare(long messageId, long shareId) {
		Map<Long, Share> shares = messages.get(messageId).getShares();
		Share share = shares.get(shareId);
		return share;
	}

	public Share addShare(long messageId, Share share) {
		Map<Long, Share> shares = messages.get(messageId).getShares();
		share.setId(shares.size()+1);
	    shares.put(share.getId(), share);
	return share;
	}

	public Share updateShare(long messageId, Share share) {
		Map<Long, Share> shares = messages.get(messageId).getShares();
		if (share.getId() <= 0) {
			return null;
		}
		shares.put(share.getId(), share);
		return share;
	}

	public Share removeShare(long messageId, long shareId) {
		Map<Long, Share> shares = messages.get(messageId).getShares();
		return shares.remove(shareId);
	}

	
}
