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

    private static final int SLEEP_TIME = 3000;

    @Inject
    private EventBus eventBus;

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.bldc.BldcDriveService#start(int)
     */
    @Override
    public void start(final int rpm) {
        sleep();
        postInfoEvent("BLDC gestartet mit " + rpm + " RPM");
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.bldc.BldcDriveService#stop()
     */
    @Override
    public void stop() {
        sleep();
        postInfoEvent("BLDC gestoppt");
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.bldc.BldcDriveService#reset()
     */
    @Override
    public void reset() {
        sleep();
        postInfoEvent("BLDC geresetet");
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (final InterruptedException e) {
            // Egal wenn unterbrochen
        }
    }

    private void postInfoEvent(final String info) {
        eventBus.post(new InfoEvent("STUB: " + info));
    }
}
