package desafioCapGov.aplication;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class CapGov {

	public static void main(String[] args) {
		String BASE_URI = "http://localhost:8080/";
		ResourceConfig rc = new ResourceConfig().packages("desafioCapGov.repository", "desafioCapGov.filter");
		@SuppressWarnings("unused")
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
		System.out.println("servidor no ar teste - " + BASE_URI);

	}

}
