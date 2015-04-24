package accefa.service.image;

import accefa.service.ServiceException;
import accefa.ui.model.ImageConfigModel;

// TODO Eigentlich sollte nicht im /src/main/java sein - da nur Testmock Implemenation.
public class ImageServiceStub implements ImageService {

    private static final int SLEEP_TIME = 3000;

    @Override
    public void saveImageConfigModel(final ImageConfigModel model) throws ServiceException {
        sleep();
    }

    @Override
    public ImageConfigModel readImageConfigModel() throws ServiceException {
        sleep();
        final ImageConfigModel model = new ImageConfigModel();
        model.setContrast(80);
        model.setGreyscale(true);
        model.setGreyscaleThreshold(400);
        return model;
    }

    @Override
    public String getImageUrl() {
        return "http://www.taz.de/uploads/images/684x342/roger_koeppel.20101127-13.jpg";
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (final InterruptedException e) {
            // Egal wenn unterbrochen
        }
    }

	@Override
	public void shoot() throws ServiceException {
		try {
            Thread.sleep(SLEEP_TIME);
        } catch (final InterruptedException e) {
            // Egal wenn unterbrochen
        }
	}

}
