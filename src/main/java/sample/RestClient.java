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
		String json = target.request().get(String.class);
		System.out.println(json);
	}

	private static URI getBaseURI() {

		return UriBuilder.fromUri("http://localhost:8080/camera").build();

	}
}
