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
        sleep();
        postInfoEvent("DC dreht vorwärts");
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#backward()
     */
    @Override
    public void backward() {
        sleep();
        postInfoEvent("DC dreht rückwärts");
    }

    /*
     * (non-Javadoc)
     * 
     * @see accefa.service.drive.dc.DcDriveService#reset()
     */
    @Override
    public void reset() {
        sleep();
        postInfoEvent("DC wurde geresetet");
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
