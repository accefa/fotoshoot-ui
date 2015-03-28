package accefa.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.ui.models.ImageConfigModel;

public class RaspiServiceRest implements RaspiService {

	private final String baseUrl;

	private static final String RESOURCE_CAMERA = "camera";

	public RaspiServiceRest(final String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public void startProcess() throws RaspiServiceException {
		// TODO
		System.out.println("ich starte den prozess - hoi adi");
	}

	@Override
	public void saveImageConfigModel(final ImageConfigModel model)
			throws RaspiServiceException {
		try {
			createClient().target(baseUrl).path(RESOURCE_CAMERA)
					.request(MediaType.TEXT_PLAIN_TYPE)
					.put(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
		} catch (final RuntimeException e) {
			throw new RaspiServiceException(e);
		}
	}

	@Override
	public ImageConfigModel readImageConfigModel() throws RaspiServiceException {
		try {
			return createClient().target(baseUrl).path(RESOURCE_CAMERA)
					.request(MediaType.APPLICATION_JSON_TYPE).get()
					.readEntity(ImageConfigModel.class);
		} catch (final RuntimeException e) {
			throw new RaspiServiceException(e);
		}
	}

	private Client createClient() {
		final ClientConfig clientConfig = new ClientConfig()
				.register(new JacksonFeature());
		return ClientBuilder.newClient(clientConfig);
	}

	@Override
	public String getImageUrl() {
		return baseUrl + "/static/detected.jpg";
	}

}
