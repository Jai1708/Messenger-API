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
import org.jai.Messenger.model.Profile;
import org.jai.Messenger.service.ProfileService;

@Path("profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService profileService = new ProfileService();
	Hateoas hateoas = new Hateoas();

	@GET
	public Response getProfiles(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {

			List<Profile> getAllProfilesforYear = profileService.getAllProfilesforYear(filterBean.getYear());
			GenericEntity<List<Profile>> listofAllProfilesforYear = new GenericEntity<List<Profile>>(
					getAllProfilesforYear) {
			};
			return Response.ok().entity(listofAllProfilesforYear).build();

		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {

			List<Profile> getAllProfilesPaginated = profileService.getAllProfilePaginated(filterBean.getStart(),
					filterBean.getSize());
			GenericEntity<List<Profile>> listofAllProfilesPaginated = new GenericEntity<List<Profile>>(
					getAllProfilesPaginated) {
			};
			return Response.ok().entity(listofAllProfilesPaginated).build();
		}

		List<Profile> getAllProfiles = profileService.getAllProfiles();
		GenericEntity<List<Profile>> listofAllProfiles = new GenericEntity<List<Profile>>(getAllProfiles) {
		};
		return Response.ok().entity(listofAllProfiles).build();
	}

	@GET
	@Path("/{profileName}")
	public Response getProfile(@PathParam("profileName") String userName, @Context UriInfo uriInfo) {
		Profile profile = profileService.getProfile(userName);
		String uri1 = hateoas.getUriForProfile(uriInfo, profile);
		String uri2 = hateoas.getProfileUriForMessage(uriInfo, profile);
		String uri3 = hateoas.getProfileUriForComments(uriInfo, profile);
		String uri4 = hateoas.getProfileUriForLikes(uriInfo, profile);
		String uri5 = hateoas.getProfileUriForShares(uriInfo, profile);

		profile.addLink(uri1, "self");
		profile.addLink(uri2, "message");
		profile.addLink(uri3, "comments");
		profile.addLink(uri4, "likes");
		profile.addLink(uri5, "shares");

		return Response.ok().entity(profile).build();
	}

	@POST
	public Response addProfile(Profile profile, @Context UriInfo uriInfo) {
		Profile newprofile = profileService.addProfile(profile);
		String newProfileName = String.valueOf(profile.getProfileName());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newProfileName).build();
		return Response.created(uri).entity(newprofile).build();
	}

	@PUT
	@Path("/{profileName}")
	public Response updateProfile(@PathParam("profileName") String userName, Profile profile) {
		profile.setProfileName(userName);
		Profile newProfile = profileService.updateProfile(profile);
		return Response.ok().entity(newProfile).build();
	}

	@DELETE
	@Path("/{profileName}")
	public Response deleteProfile(@PathParam("profileName") String userName) {
		profileService.removeProfile(userName);
		return Response.noContent().build();
	}

	@Path("/{profileName}/cmts")
	public CommentSubResourceforProfile getCommentSubResourceforProfile() {
		return new CommentSubResourceforProfile();
	}

	@Path("/{profileName}/likes")
	public LikeSubResourceforProfile getLikeSubResourceforProfile() {
		return new LikeSubResourceforProfile();
	}

	@Path("/{profileName}/shares")
	public ShareSubResourceforProfile getShareSubResourceforProfile() {
		return new ShareSubResourceforProfile();
	}

}
