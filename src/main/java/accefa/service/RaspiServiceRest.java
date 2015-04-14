package accefa.service;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import accefa.guice.annotations.RaspiTarget;
import accefa.ui.model.ImageConfigModel;
import accefa.ui.model.StartSignalModel;
import accefa.util.ApplicationPreferences;

import com.google.inject.Inject;

public class RaspiServiceRest implements RaspiService {

    private static final String RESOURCE_CAMERA = "camera";
    private static final String RESOURCE_START = "start";

    private final ApplicationPreferences properties;
    private final WebTarget raspiTarget;

    @Inject
    public RaspiServiceRest(final ApplicationPreferences properties,
            @RaspiTarget final WebTarget raspiTarget) {
        this.properties = properties;
        this.raspiTarget = raspiTarget;
    }

    @Override
    public void startProcess() throws RaspiServiceException {
        try {
            final StartSignalModel model = new StartSignalModel();
            model.setUrl(properties.getWebserverUrl() + "stopp");

            final Response response = raspiTarget.path(RESOURCE_START)
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .put(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
            handleStatusInfo(response.getStatusInfo());
        } catch (final RuntimeException e) {
            throw new RaspiServiceException(e);
        }
    }

    @Override
    public void saveImageConfigModel(final ImageConfigModel model) throws RaspiServiceException {
        try {
            final Response response = raspiTarget.path(RESOURCE_CAMERA)
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .put(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
            handleStatusInfo(response.getStatusInfo());
        } catch (final RuntimeException e) {
            throw new RaspiServiceException(e);
        }
    }

    @Override
    public ImageConfigModel readImageConfigModel() throws RaspiServiceException {
        try {
            final Response response = raspiTarget.path(RESOURCE_CAMERA)
                    .request(MediaType.APPLICATION_JSON_TYPE).get();
            handleStatusInfo(response.getStatusInfo());
            return response.readEntity(ImageConfigModel.class);
        } catch (final RuntimeException e) {
            throw new RaspiServiceException(e);
        }
    }

    @Override
    public String getImageUrl() {
        return properties.getRaspiUrl() + "/static/detected.jpg";
    }

    private void handleStatusInfo(final StatusType status) {
        final int statusCode = status.getStatusCode();
        if (statusCode != Status.OK.getStatusCode()) {
            throw new RuntimeException("Error " + statusCode + " - " + status.getReasonPhrase());
        }
    }

}
