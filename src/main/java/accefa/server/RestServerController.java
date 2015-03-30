package accefa.server;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import accefa.ui.view.PiController;
import accefa.util.FotoShootProperties;

import com.sun.net.httpserver.HttpServer;

public class RestServerController {

   private HttpServer server;

   private final PiController piController;

   private ExecutorService executorService;

   public RestServerController(final PiController piController) {
      this.piController = piController;
   }

   public void start() {
      if (server == null) {
         final String url = FotoShootProperties.getInstance().getWebserverUrl();

         final ResourceConfig config = new ResourceConfig().packages(RestServer.class.getPackage().getName());
         config.register(new AbstractBinder() {
            @Override
            protected void configure() {
               bind(piController).to(PiController.class);
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
