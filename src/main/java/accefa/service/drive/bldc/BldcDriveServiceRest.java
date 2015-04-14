/**
 *
 */
package accefa.service.drive.bldc;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.ui.model.BldcDriveModel;
import accefa.util.ApplicationPreferences;

import com.google.inject.Inject;

/**
 * @author Fabian Wüthrich
 *
 */
public class BldcDriveServiceRest implements BldcDriveService {

    private static final String RESOURCE_START = "drive/bldc/start";
    private static final String RESOURCE_STOP = "drive/bldc/stop";
    private static final String RESOURCE_RESET = "drive/bldc/reset";
    private final ApplicationPreferences preferences;

    @Inject
    public BldcDriveServiceRest(final ApplicationPreferences preferences) {
        this.preferences = preferences;
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

        createClient().target(preferences.getRaspiUrl()).path(RESOURCE_START)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
        // TODO Error Handling
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.bldc.BldcDriveService#stop()
     */
    @Override
    public void stop() {
        createClient().target(preferences.getRaspiUrl()).path(RESOURCE_STOP)
                .request(MediaType.APPLICATION_JSON_TYPE).post(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.bldc.BldcDriveService#reset()
     */
    @Override
    public void reset() {
        createClient().target(preferences.getRaspiUrl()).path(RESOURCE_RESET)
                .request(MediaType.APPLICATION_JSON_TYPE).post(null);
    }

    private Client createClient() {
        final ClientConfig clientConfig = new ClientConfig().register(new JacksonFeature());
        return ClientBuilder.newClient(clientConfig);
    }

}
