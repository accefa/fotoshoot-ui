package accefa.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import accefa.ui.model.ImageConfigModel;

// TODO Eigentlich sollte nicht im /src/main/java sein - da nur Testmock Implemenation.
public class RaspiServiceMock implements RaspiService {

   @Override
   public void saveImageConfigModel(final ImageConfigModel model) throws RaspiServiceException {
      try {
         Thread.sleep(2000);
      } catch (final InterruptedException e) {
         e.printStackTrace();
      }
   }

   @Override
   public ImageConfigModel readImageConfigModel() throws RaspiServiceException {
      try {
         Thread.sleep(2000);
      } catch (final InterruptedException e) {
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
      try {
         Thread.sleep(4343);
         try {
            final URL myURL = new URL("http://localhost:8080/stopp");
            final URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            myURLConnection.getContent();
         } catch (final MalformedURLException e) {
            e.printStackTrace();
         } catch (final IOException e) {
            e.printStackTrace();
         }
      } catch (final InterruptedException e) {
         e.printStackTrace();
      }
   }

   @Override
   public String getImageUrl() {
      return "http://www.taz.de/uploads/images/684x342/roger_koeppel.20101127-13.jpg";
   }

}
