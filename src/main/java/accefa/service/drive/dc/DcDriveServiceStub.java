/**
 *
 */
package accefa.service.drive.dc;

import accefa.event.InfoEvent;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

/**
 * @author Fabian Wüthrich
 *
 */
public class DcDriveServiceStub implements DcDriveService {

    private static final int SLEEP_TIME = 3000;

    private final EventBus eventBus;

    @Inject
    public DcDriveServiceStub(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#forward()
     */
    @Override
    public void forward() {
        postInfoEvent("DC dreht vorwärts");
        sleep();
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#backward()
     */
    @Override
    public void backward() {
        postInfoEvent("DC dreht rückwärts");
        sleep();
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#stop()
     */
    @Override
    public void stop() {
        postInfoEvent("DC stoppt");
        sleep();
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#reset()
     */
    @Override
    public void reset() {
        postInfoEvent("DC wurde geresetet");
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
