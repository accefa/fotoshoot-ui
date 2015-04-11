package accefa.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import accefa.ui.model.ImageConfigModel;

// TODO Eigentlich sollte nicht im /src/main/java sein - da nur Testmock Implemenation.
public class RaspiServiceMock implements RaspiService {

    private static final int SLEEP_TIME = 3000;

    @Override
    public void saveImageConfigModel(final ImageConfigModel model) throws RaspiServiceException {
        sleep();
    }

    @Override
    public ImageConfigModel readImageConfigModel() throws RaspiServiceException {
        sleep();
        final ImageConfigModel model = new ImageConfigModel();
        model.setContrast(80);
        model.setGreyscale(true);
        model.setGreyscaleThreshold(400);
        return model;
    }

    @Override
    public void startProcess() throws RaspiServiceException {
        sleep();
        try {
            final URL myURL = new URL("http://localhost:8080/stopp");
            final URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            myURLConnection.getContent();
        } catch (final IOException e) {
            e.printStackTrace();
        }
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

}
