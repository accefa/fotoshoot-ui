/**
 *
 */
package accefa.service.drive.bldc;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import accefa.guice.annotations.RaspiTarget;
import accefa.ui.model.BldcDriveModel;

import com.google.inject.Inject;

/**
 * @author Fabian Wüthrich
 *
 */
public class BldcDriveServiceRest implements BldcDriveService {

    private static final String RESOURCE_START = "drive/bldc/start";
    private static final String RESOURCE_STOP = "drive/bldc/stop";
    private static final String RESOURCE_RESET = "drive/bldc/reset";

    private final WebTarget raspiTarget;

    @Inject
    public BldcDriveServiceRest(@RaspiTarget final WebTarget raspiTarget) {
        this.raspiTarget = raspiTarget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.bldc.BldcDriveService#start(int)
     */
    @Override
    public void start(final int rpm) {
        final BldcDriveModel model = new BldcDriveModel();
        model.setRpm(rpm);

        final Response response = raspiTarget.path(RESOURCE_START)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
        handleStatusInfo(response.getStatusInfo());
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.bldc.BldcDriveService#stop()
     */
    @Override
    public void stop() {
        final Response response = raspiTarget.path(RESOURCE_STOP)
                .request(MediaType.APPLICATION_JSON_TYPE).post(null);
        handleStatusInfo(response.getStatusInfo());
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.bldc.BldcDriveService#reset()
     */
    @Override
    public void reset() {
        final Response response = raspiTarget.path(RESOURCE_RESET)
                .request(MediaType.APPLICATION_JSON_TYPE).post(null);
        handleStatusInfo(response.getStatusInfo());
    }

    private void handleStatusInfo(final StatusType status) {
        final int statusCode = status.getStatusCode();
        if (statusCode != Status.OK.getStatusCode()) {
            throw new RuntimeException("Error " + statusCode + " - " + status.getReasonPhrase());
        }
    }
}
