package accefa.service;

import accefa.ui.models.ImageConfigModel;

public class RaspiServiceImpl implements RaspiService {

   @Override
   public void saveImageConfigModel(final ImageConfigModel model) {
      // TODO Auto-generated method stub

   }

   @Override
   public ImageConfigModel readImageConfigModel() {
      return new ImageConfigModel();
      /*
       * final ClientConfig clientConfig = new ClientConfig().register(new
       * JacksonFeature()); final Client client =
       * ClientBuilder.newClient(clientConfig); final WebTarget target =
       * client.target("http://localhost:8080/camera"); final Response response
       * = target.request(MediaType.APPLICATION_JSON_TYPE).get(); final
       * ImageConfigModel imageConfigModel =
       * response.readEntity(ImageConfigModel.class); response.close(); return
       * imageConfigModel;
       */
   }

}
