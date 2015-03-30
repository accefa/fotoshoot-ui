package accefa.server;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import accefa.ui.view.PiController;

@Path("stopp")
public class RestServer {

   @Inject
   private PiController piController;

   @GET
   @Produces("text/plain")
   public String stopp() {
      try {
         piController.stoppProcess();
         return "Danke.";
      } catch (final RuntimeException exception) {
         return "Uhhaa! Fehler: " + exception.getMessage();
      }

   }

}
