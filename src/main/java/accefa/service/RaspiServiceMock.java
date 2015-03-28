package accefa.service;

import accefa.ui.models.ImageConfigModel;

// TODO Eigentlich sollte nicht im /src/main/java sein - da nur Testmock Implemenation.
public class RaspiServiceMock implements RaspiService {

	@Override
	public void saveImageConfigModel(final ImageConfigModel model)
			throws RaspiServiceException {
		try {
			Thread.sleep(2000);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ImageConfigModel readImageConfigModel() throws RaspiServiceException {
		try {
			Thread.sleep(2000);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final ImageConfigModel model = new ImageConfigModel();
		model.setContrast(80);
		model.setGreyscale(true);
		model.setGreyscaleThreshold(400);
		return model;
	}

	@Override
	public void startProcess() throws RaspiServiceException {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getImageUrl() {
		return "http://www.taz.de/uploads/images/684x342/roger_koeppel.20101127-13.jpg";
	}

}
