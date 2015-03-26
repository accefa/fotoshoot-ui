package accefa.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.ui.models.ImageConfigModel;

public class RaspiServiceImpl implements RaspiService {

   private final RaspiUrlBuilder urlBuilder;

   public RaspiServiceImpl(final RaspiUrlBuilder urlBuilder) {
      this.urlBuilder = urlBuilder;
   }

   @Override
   public void saveImageConfigModel(final ImageConfigModel model) {
      // TODO Auto-generated method stub
   }

   @Override
   public ImageConfigModel readImageConfigModel() {
      // TODO Exception Handling
      final WebTarget target = createWebTarget(urlBuilder.camera());
      final Response response = getJson(target);
      final ImageConfigModel imageConfigModel = response.readEntity(ImageConfigModel.class);
      response.close();
      return imageConfigModel;
   }

   private WebTarget createWebTarget(final String url) {
      final ClientConfig clientConfig = new ClientConfig().register(new JacksonFeature());
      final Client client = ClientBuilder.newClient(clientConfig);
      return client.target(url);
   }

   private Response getJson(final WebTarget target) {
      return target.request(MediaType.APPLICATION_JSON_TYPE).get();
   }

}
