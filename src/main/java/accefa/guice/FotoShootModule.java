package accefa.guice;

import accefa.service.RaspiService;
import accefa.service.RaspiServiceRest;

/**
 * Guice Module für Produktion.
 */
public class FotoShootModule extends AbstractFotoShootModule {

   @Override
   protected void configureRaspiService() {
      bind(RaspiService.class).to(RaspiServiceRest.class);
   }

}
