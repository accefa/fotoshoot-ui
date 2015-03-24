package sample;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.ui.models.ImageConfigModel;

public class RestClient {
	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig()
				.register(new JacksonFeature());
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget target = client.target("http://localhost:8080/camera");
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.get();
		ImageConfigModel imageConfigModel = response.readEntity(ImageConfigModel.class);
		
		System.out.println(imageConfigModel);
		
		response.close();
	}
}
