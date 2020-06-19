package org.jai.Messenger.annotations;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/annotations")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class ParamAnnotations {
	@GET
	public String getParamUsingAnnotations(@MatrixParam("matrix") String matrixValue,
			@HeaderParam("header") String headerValue, @CookieParam("cookiename") String cookieValue) {
		return "Matrix Param is:" + matrixValue + "Header Param is:" + headerValue + "Cookie Param is:" + cookieValue;

	}

	@GET
	@Path("/context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();

		return "Path: " + path + "  Cookies: " + cookies;
	}

}
