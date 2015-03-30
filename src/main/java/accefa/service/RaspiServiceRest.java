package accefa.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.ui.models.ImageConfigModel;
import accefa.ui.models.StartSignalModel;
import accefa.util.FotoShootProperties;

public class RaspiServiceRest implements RaspiService {

   private static final String RESOURCE_CAMERA = "camera";

   private static final String RESOURCE_START = "start";

   private String getBaseUrl() {
      return FotoShootProperties.getInstance().getRaspiUrl();
   }

   @Override
   public void startProcess() throws RaspiServiceException {
      try {
         final StartSignalModel model = new StartSignalModel();
         model.setUrl(FotoShootProperties.getInstance().getWebserverUrl() + "stopp");

         createClient().target(getBaseUrl()).path(RESOURCE_START).request(MediaType.TEXT_PLAIN_TYPE)
         .put(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
      } catch (final RuntimeException e) {
         throw new RaspiServiceException(e);
      }
   }

   @Override
   public void saveImageConfigModel(final ImageConfigModel model) throws RaspiServiceException {
      try {
         createClient().target(getBaseUrl()).path(RESOURCE_CAMERA).request(MediaType.TEXT_PLAIN_TYPE)
         .put(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
      } catch (final RuntimeException e) {
         throw new RaspiServiceException(e);
      }
   }

   @Override
   public ImageConfigModel readImageConfigModel() throws RaspiServiceException {
      try {
         return createClient().target(getBaseUrl()).path(RESOURCE_CAMERA).request(MediaType.APPLICATION_JSON_TYPE)
               .get().readEntity(ImageConfigModel.class);
      } catch (final RuntimeException e) {
         throw new RaspiServiceException(e);
      }
   }

   private Client createClient() {
      final ClientConfig clientConfig = new ClientConfig().register(new JacksonFeature());
      return ClientBuilder.newClient(clientConfig);
   }

   @Override
   public String getImageUrl() {
      return getBaseUrl() + "/static/detected.jpg";
   }

}
