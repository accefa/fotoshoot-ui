package accefa.guice;

import accefa.service.RaspiService;
import accefa.service.RaspiServiceMock;
import accefa.service.drive.bldc.BldcDriveService;
import accefa.service.drive.bldc.BldcDriveServiceStub;

public class FotoShootModuleMock extends AbstractFotoShootModule {

    @Override
    protected void configureRaspiService() {
        bind(RaspiService.class).to(RaspiServiceMock.class);
    }

    @Override
    protected void configureBldcDriveService() {
        bind(BldcDriveService.class).to(BldcDriveServiceStub.class);
    }
}
