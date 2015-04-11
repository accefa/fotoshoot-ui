/**
 *
 */
package accefa.service.drive.bldc;

import accefa.event.InfoEvent;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

/**
 * @author Fabian Wüthrich
 *
 */
public class BldcDriveServiceStub implements BldcDriveService {

    @Inject
    private EventBus eventBus;

    /*
     * (non-Javadoc)
     *
     * @see accefa.service.drive.bldc.BldcDriveService#start(int)
     */
    @Override
    public void start(final int rpm) {
        eventBus.post(new InfoEvent("Motor gestartet mit " + rpm + " RPM"));
    }

    /*
     * (non-Javadoc)
     *
     * @see accefa.service.drive.bldc.BldcDriveService#stop()
     */
    @Override
    public void stop() {
        eventBus.post(new InfoEvent("Motor gestoppt"));
    }

    /*
     * (non-Javadoc)
     *
     * @see accefa.service.drive.bldc.BldcDriveService#reset()
     */
    @Override
    public void reset() {
        eventBus.post(new InfoEvent("Motor geresetet"));
    }

}
