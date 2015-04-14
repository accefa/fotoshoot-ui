/**
 *
 */
package accefa.service.drive.dc;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import accefa.guice.annotations.RaspiTarget;

import com.google.inject.Inject;

/**
 * @author Fabian Wüthrich
 *
 */
public class DcDriveServiceRest implements DcDriveService {

    private static final String RESOURCE_FORWARD = "drive/dc/forward";
    private static final String RESOURCE_BACKWARD = "drive/dc/backward";
    private static final String RESOURCE_RESET = "drive/dc/reset";

    private final WebTarget raspiTarget;

    @Inject
    public DcDriveServiceRest(@RaspiTarget final WebTarget raspiTarget) {
        this.raspiTarget = raspiTarget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#forward()
     */
    @Override
    public void forward() {
        final Response response = raspiTarget.path(RESOURCE_FORWARD)
                .request(MediaType.APPLICATION_JSON_TYPE).post(null);
        handleStatusInfo(response.getStatusInfo());
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#backward()
     */
    @Override
    public void backward() {
        final Response response = raspiTarget.path(RESOURCE_BACKWARD)
                .request(MediaType.APPLICATION_JSON_TYPE).post(null);
        handleStatusInfo(response.getStatusInfo());
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#reset()
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
