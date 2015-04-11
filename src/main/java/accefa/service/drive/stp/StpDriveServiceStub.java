/**
 *
 */
package accefa.service.drive.stp;

import accefa.event.InfoEvent;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

/**
 * @author Fabian Wüthrich
 *
 */
public class StpDriveServiceStub implements StpDriveService {

    private static final int SLEEP_TIME = 3000;

    private final EventBus eventBus;

    @Inject
    public StpDriveServiceStub(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /*
     * (non-Javadoc)
     *
     * @see accefa.service.drive.stp.StpDriveService#start(int)
     */
    @Override
    public void start(final int steps) {
        postInfoEvent("STP dreht " + steps + " Schritte");
        sleep();
    }

    /*
     * (non-Javadoc)
     *
     * @see accefa.service.drive.stp.StpDriveService#reset()
     */
    @Override
    public void reset() {
        postInfoEvent("STP wurde geresetet");
        sleep();
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
