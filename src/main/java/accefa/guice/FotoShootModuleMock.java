package accefa.guice;

import accefa.service.drive.bldc.BldcDriveService;
import accefa.service.drive.bldc.BldcDriveServiceStub;
import accefa.service.drive.dc.DcDriveService;
import accefa.service.drive.dc.DcDriveServiceStub;
import accefa.service.drive.stp.StpDriveService;
import accefa.service.drive.stp.StpDriveServiceStub;
import accefa.service.image.ImageService;
import accefa.service.image.ImageServiceStub;

public class FotoShootModuleMock extends AbstractFotoShootModule {

    @Override
    protected void configureRaspiService() {
        bind(ImageService.class).to(ImageServiceStub.class);
    }

    @Override
    protected void configureBldcDriveService() {
        bind(BldcDriveService.class).to(BldcDriveServiceStub.class);
    }

    @Override
    protected void configureDcDriveService() {
        bind(DcDriveService.class).to(DcDriveServiceStub.class);
    }

    @Override
    protected void configureStpDriveService() {
        bind(StpDriveService.class).to(StpDriveServiceStub.class);
    }
}
