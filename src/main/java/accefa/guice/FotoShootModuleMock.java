package accefa.guice;

import accefa.service.RaspiService;
import accefa.service.RaspiServiceMock;

import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class FotoShootModuleMock extends AbstractFotoShootModule {

   @Override
   @Provides
   @Singleton
   protected RaspiService provideRaspiService(final Injector injector) {
      return new RaspiServiceMock();
   }

}
