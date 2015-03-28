package sample;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.ui.models.ImageConfigModel;

public class RestClient {
	public static void main(final String[] args) {
		final ClientConfig clientConfig = new ClientConfig()
				.register(new JacksonFeature());
		final Client client = ClientBuilder.newClient(clientConfig);
		WebTarget target = client.target("http://192.168.1.3:8080/camera");
		final Response response = target.request(
				MediaType.APPLICATION_JSON_TYPE).get();
		final ImageConfigModel imageConfigModel = response
				.readEntity(ImageConfigModel.class);

		System.out.println("now read!");

		System.out.println(imageConfigModel);

		response.close();

		imageConfigModel
				.setImage("/srv/pren/application/config/../static/detected.jpg");
		imageConfigModel.setGreyscaleThreshold(67);

		target = createWebTarget("http://192.168.1.3:8080/camera");
		target.request(MediaType.TEXT_PLAIN_TYPE)
		.put(Entity.entity(imageConfigModel,
				MediaType.APPLICATION_JSON_TYPE));

		System.out.println("now go to save");
	}

	private static WebTarget createWebTarget(final String url) {
		final Client client = createClient();
		return client.target(url);
	}

	private static Client createClient() {
		final ClientConfig clientConfig = new ClientConfig()
		.register(new JacksonFeature());
		final Client client = ClientBuilder.newClient(clientConfig);
		return client;
	}
}
