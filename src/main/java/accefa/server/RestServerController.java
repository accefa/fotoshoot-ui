package accefa.server;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import accefa.util.ApplicationPreferences;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.sun.net.httpserver.HttpServer;

public class RestServerController {

   @Inject
   private ApplicationPreferences properties;

   @Inject
   private Injector injector;

   private HttpServer server;

   private ExecutorService executorService;

   public void start() {
      if (server == null) {
         final String url = properties.getWebserverUrl();
         final ResourceConfig config = new ResourceConfig().packages(RestServer.class.getPackage().getName());
         config.register(new AbstractBinder() {
            @Override
            protected void configure() {
               bind(injector.getInstance(EventBus.class)).to(EventBus.class);
            }
         });

         server = JdkHttpServerFactory.createHttpServer(URI.create(url), config, false);
         executorService = Executors.newSingleThreadExecutor();
         server.setExecutor(executorService);
         server.start();
      }
   }

   public void shutdown() {
      if (server != null) {
         server.stop(0);
         executorService.shutdownNow();
      }
   }

}
