package accefa.guice;

import accefa.service.RaspiService;
import accefa.service.RaspiServiceRest;

import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Guice Module für Produktion.
 */
public class FotoShootModule extends AbstractFotoShootModule {

   @Override
   @Provides
   @Singleton
   protected RaspiService provideRaspiService(final Injector injector) {
      return new RaspiServiceRest();
   }

}
