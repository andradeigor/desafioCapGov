package desafioCapGov.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import desafioCapGov.annotations.Logged;
import io.jsonwebtoken.io.IOException;

@Provider
@Logged
public class authFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		System.out.println("to em todo canto paizao");
	}

}
