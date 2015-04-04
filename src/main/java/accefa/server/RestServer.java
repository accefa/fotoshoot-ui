package accefa.server;

import javafx.application.Platform;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import accefa.event.ProcessStoppedEvent;

import com.google.common.eventbus.EventBus;

@Path("stopp")
public class RestServer {

   @Inject
   private EventBus eventBus;

   @GET
   @Produces("text/plain")
   public String stopp() {
      try {
         Platform.runLater(new Runnable() {
            @Override
            public void run() {
               eventBus.post(new ProcessStoppedEvent());
            }
         });
         return "Danke.";
      } catch (final RuntimeException exception) {
         return "Uhhaa! Fehler: " + exception.getMessage();
      }

   }

}
