package org.jai.Messenger.component;

import java.net.URI;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.jai.Messenger.model.Message;
import org.jai.Messenger.model.Profile;
import org.jai.Messenger.resources.CommentSubResourceforMessage;
import org.jai.Messenger.resources.CommentSubResourceforProfile;
import org.jai.Messenger.resources.LikeSubResourceforMessage;
import org.jai.Messenger.resources.LikeSubResourceforProfile;
import org.jai.Messenger.resources.MessageResource;
import org.jai.Messenger.resources.ProfileResource;
import org.jai.Messenger.resources.ShareSubResourceforMessage;
import org.jai.Messenger.resources.ShareSubResourceforProfile;

public class Hateoas {

	public String getUriForMessage(@Context UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build();
        
		return uri.toString();
	}
	
	public String getMessageUriForProfile(@Context UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build();
        
		return uri.toString();
	}
	
	public String getMessageUriForComments(@Context UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentSubResourceforMessage")
				.path(CommentSubResourceforMessage.class)
				.resolveTemplate("messageId",message.getId())
				.build();
        
		return uri.toString();
	}
	
	public String getMessageUriForLikes(@Context UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getLikeSubResourceforMessage")
				.path(LikeSubResourceforMessage.class)
				.resolveTemplate("messageId",message.getId())
				.build();
        
		return uri.toString();
	}
	
	public String getMessageUriForShares(@Context UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getShareSubResourceforMessage")
				.path(ShareSubResourceforMessage.class)
				.resolveTemplate("messageId",message.getId())
				.build();
        
		return uri.toString();
	}
	
	
	
	
	
	public String getUriForProfile(@Context UriInfo uriInfo, Profile profile) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(profile.getProfileName())
				.build();
        
		return uri.toString();
	}
	
	
	
	public String getProfileUriForMessage(@Context UriInfo uriInfo, Profile profile) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(profile.getId()))
				.build();
        
		return uri.toString();
	}
	

	
	public String getProfileUriForComments(@Context UriInfo uriInfo, Profile profile) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(ProfileResource.class, "getCommentSubResourceforProfile")
				.path(CommentSubResourceforProfile.class)
				.resolveTemplate("profileName",profile.getProfileName())
				.build();
        
		return uri.toString();
	}
	
	
	
	
	public String getProfileUriForLikes(@Context UriInfo uriInfo, Profile profile) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(ProfileResource.class, "getLikeSubResourceforProfile")
				.path(LikeSubResourceforProfile.class)
				.resolveTemplate("profileName",profile.getProfileName())
				.build();
        
		return uri.toString();
	}
	
	
	
	public String getProfileUriForShares(@Context UriInfo uriInfo, Profile profile) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(ProfileResource.class, "getShareSubResourceforProfile")
				.path(ShareSubResourceforProfile.class)
				.resolveTemplate("profileName",profile.getProfileName())
				.build();
        
		return uri.toString();
	}
}
