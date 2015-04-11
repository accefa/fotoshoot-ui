package accefa.guice;

import accefa.service.RaspiService;
import accefa.service.RaspiServiceRest;
import accefa.service.drive.bldc.BldcDriveService;
import accefa.service.drive.bldc.BldcDriveServiceRest;

/**
 * Guice Module für Produktion.
 */
public class FotoShootModule extends AbstractFotoShootModule {

    @Override
    protected void configureRaspiService() {
        bind(RaspiService.class).to(RaspiServiceRest.class);
    }

    @Override
    protected void configureBldcDriveService() {
        bind(BldcDriveService.class).to(BldcDriveServiceRest.class);
    }

}
