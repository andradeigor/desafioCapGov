package desafioCapGov.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import desafioCapGov.annotations.Logged;
import desafioCapGov.entities.ResponseError;
import desafioCapGov.lib.MyJwt;
import io.jsonwebtoken.io.IOException;

@Provider
@Logged
public class authFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		try {
			String Token = request.getHeaderString("Authorization");
			String UserId = MyJwt.decrypt(Token);
			request.getHeaders().add("userId", UserId);
		} catch (Exception e) {
			request.abortWith(Response.status(403).entity(new ResponseError("A valid token is required", 403)).build());
		}
	}

}
