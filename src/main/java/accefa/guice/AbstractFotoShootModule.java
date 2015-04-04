package accefa.guice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import accefa.server.RestServerController;
import accefa.service.RaspiService;
import accefa.util.ApplicationProperties;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * Google Guice Module. Hier sind alle Bindings definiert, welche für alle
 * Umgebungen gleich sind.
 */
public abstract class AbstractFotoShootModule extends AbstractModule {

   /**
    * Provider für den RaspiService.
    *
    * @param injector
    *           Injector.
    * @return Konkrete RaspiService-Instanz.
    */
   protected abstract RaspiService provideRaspiService(Injector injector);

   @Override
   protected void configure() {
      bind(ExecutorService.class).toInstance(Executors.newSingleThreadExecutor());

      // Einen globalen Eventbus registrieren
      final EventBus globalEventBus = new EventBus("Global Event Bus");
      bind(EventBus.class).toInstance(globalEventBus);

      bind(RestServerController.class).asEagerSingleton();

      // Alle Controller registrieren. Damit müssen sich die Controller nicht
      // mehr selber registrieren. Es werden alle Objekte registriert, welche
      // über Guice erzeugt werden. Die Controller sind dabei der Fall.
      bindListener(Matchers.any(), new TypeListener() {
         @Override
         public <I> void hear(final TypeLiteral<I> typeLiteral, final TypeEncounter<I> typeEncounter) {
            typeEncounter.register(new InjectionListener<I>() {
               @Override
               public void afterInjection(final I object) {
                  globalEventBus.register(object);
               }
            });
         }
      });
   }

   @Provides
   @Singleton
   private ApplicationProperties provideFotoShootProperties() {
      return new ApplicationProperties();
   }

}