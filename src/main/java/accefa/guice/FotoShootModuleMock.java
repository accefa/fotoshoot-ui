package accefa.guice;

import accefa.service.RaspiService;
import accefa.service.RaspiServiceMock;

public class FotoShootModuleMock extends AbstractFotoShootModule {

   @Override
   protected void configureRaspiService() {
      bind(RaspiService.class).to(RaspiServiceMock.class);
   }
}
