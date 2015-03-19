package sample;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class RestClient {
	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget target = client.target(getBaseURI());
		System.out.println(target.request().get(String.class));
	}

	private static URI getBaseURI() {

		return UriBuilder.fromUri("http://localhost:8080/").build();

	}
}
