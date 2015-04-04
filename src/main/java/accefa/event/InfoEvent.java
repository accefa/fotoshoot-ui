package accefa.event;

/**
 * Error Event. Dieses Event kommt zum Einsatz falls der Benutzer informiert
 * werden soll.
 */
public class InfoEvent {

   private final String message;

   /**
    * Erzeugt ein neues Event.
    *
    * @param message
    *           Nachricht.
    */
   public InfoEvent(final String message) {
      this.message = message;
   }

   /**
    * Gibt die Nachricht zurück.
    *
    * @return Nachricht.
    */
   public String getMessage() {
      return message;
   }

}
