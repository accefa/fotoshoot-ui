package accefa.guice;

import accefa.service.RaspiService;
import accefa.service.RaspiServiceRest;
import accefa.service.drive.bldc.BldcDriveService;
import accefa.service.drive.bldc.BldcDriveServiceRest;
import accefa.service.drive.dc.DcDriveService;
import accefa.service.drive.dc.DcDriveServiceRest;
import accefa.service.drive.stp.StpDriveService;
import accefa.service.drive.stp.StpDriveServiceRest;

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

    @Override
    protected void configureDcDriveService() {
        bind(DcDriveService.class).to(DcDriveServiceRest.class);
    }

    @Override
    protected void configureStpDriveService() {
        bind(StpDriveService.class).to(StpDriveServiceRest.class);
    }

}
