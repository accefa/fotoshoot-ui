package accefa.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.ui.models.ImageConfigModel;

public class RaspiServiceImpl implements RaspiService {

	private final RaspiUrlBuilder urlBuilder;

	public RaspiServiceImpl(final RaspiUrlBuilder urlBuilder) {
		this.urlBuilder = urlBuilder;
	}

	@Override
	public void startProcess() throws RaspiServiceException {
		System.out.println("ich starte den prozess - hoi adi");
	}

	@Override
	public void saveImageConfigModel(final ImageConfigModel model)
			throws RaspiServiceException {
		try {
			final WebTarget target = createWebTarget(urlBuilder.camera());
			target.request(MediaType.APPLICATION_JSON_TYPE).put(
					Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
		} catch (final RuntimeException e) {
			throw new RaspiServiceException(e);
		}
	}

	@Override
	public ImageConfigModel readImageConfigModel() throws RaspiServiceException {
		try {
			final WebTarget target = createWebTarget(urlBuilder.camera());
			final Response response = getJson(target);
			final ImageConfigModel imageConfigModel = response
					.readEntity(ImageConfigModel.class);
			response.close();
			return imageConfigModel;
		} catch (final RuntimeException e) {
			throw new RaspiServiceException(e);
		}
	}

	private Client createClient() {
		final ClientConfig clientConfig = new ClientConfig()
		.register(new JacksonFeature());
		final Client client = ClientBuilder.newClient(clientConfig);
		return client;
	}

	private WebTarget createWebTarget(final String url) {
		final Client client = createClient();
		return client.target(url);
	}

	private Response getJson(final WebTarget target) {
		return target.request(MediaType.APPLICATION_JSON_TYPE).get();
	}

}
