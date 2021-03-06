package accefa.service.image;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import accefa.service.RaspiClientFactory;
import accefa.service.ServiceException;
import accefa.ui.model.ImageConfigModel;
import accefa.ui.model.StartSignalModel;
import accefa.util.ApplicationPreferences;

import com.google.inject.Inject;

public class ImageServiceRest implements ImageService {

    private static final String RESOURCE_CAMERA = "camera";
    private static final Object RESOURCE_IMAGE = "static/detected.jpg";

    private final ApplicationPreferences properties;
    private final RaspiClientFactory clientFactory;

    @Inject
    public ImageServiceRest(final ApplicationPreferences properties,
            final RaspiClientFactory raspiService) {
        this.properties = properties;
        this.clientFactory = raspiService;
    }

    @Override
    public void saveImageConfigModel(final ImageConfigModel model) throws ServiceException {
        try {
            final Response response = clientFactory.getRaspiTarget().path(RESOURCE_CAMERA)
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .put(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
            handleStatusInfo(response.getStatusInfo());
        } catch (final RuntimeException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ImageConfigModel readImageConfigModel() throws ServiceException {
        try {
            final Response response = clientFactory.getRaspiTarget().path(RESOURCE_CAMERA)
                    .request(MediaType.APPLICATION_JSON_TYPE).get();
            handleStatusInfo(response.getStatusInfo());
            return response.readEntity(ImageConfigModel.class);
        } catch (final RuntimeException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String getImageUrl() {
        final StringBuilder imageUrl = new StringBuilder();
        final String raspiUrl = properties.getRaspiUrl();
        imageUrl.append(raspiUrl);
        if (!raspiUrl.endsWith("/")) {
            imageUrl.append("/");
        }
        imageUrl.append(RESOURCE_IMAGE);
        return imageUrl.toString();
    }

    private void handleStatusInfo(final StatusType status) {
        final int statusCode = status.getStatusCode();
        if (statusCode != Status.OK.getStatusCode()) {
            throw new RuntimeException("Error " + statusCode + " - " + status.getReasonPhrase());
        }
    }

	@Override
	public void shoot() throws ServiceException {
		try {
            final Response response = clientFactory.getRaspiTarget().path(RESOURCE_CAMERA)
                    .request(MediaType.TEXT_PLAIN).post(null);
            handleStatusInfo(response.getStatusInfo());
        } catch (final RuntimeException e) {
            throw new ServiceException(e);
        }
	}

}
