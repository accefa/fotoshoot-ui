/**
 *
 */
package accefa.service.drive.stp;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import accefa.guice.annotations.RaspiTarget;
import accefa.ui.model.StpDriveModel;

import com.google.inject.Inject;

/**
 * @author Fabian Wüthrich
 *
 */
public class StpDriveServiceRest implements StpDriveService {

    private static final String RESOURCE_START = "drive/stp/start";
    private static final String RESOURCE_RESET = "drive/stp/reset";

    private final WebTarget raspiTarget;

    @Inject
    public StpDriveServiceRest(@RaspiTarget final WebTarget raspiTarget) {
        this.raspiTarget = raspiTarget;
    }

    /*
     * (non-Javadoc)
     *
     * @see accefa.service.drive.stp.StpDriveService#start(int)
     */
    @Override
    public void start(final int steps) {
        final StpDriveModel model = new StpDriveModel();
        model.setSteps(steps);

        final Response response = raspiTarget.path(RESOURCE_START)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
        handleStatusInfo(response.getStatusInfo());
    }

    /*
     * (non-Javadoc)
     *
     * @see accefa.service.drive.stp.StpDriveService#reset()
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
