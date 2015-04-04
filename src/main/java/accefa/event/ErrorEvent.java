package accefa.event;

/**
 * Error Event. Dieses Event kommt zum Einsatz falls ein Controller einen Fehler
 * hat.
 */
public class ErrorEvent {

   private final String message;

   /**
    * Erzeugt ein neues Event.
    *
    * @param message
    *           Nachricht.
    */
   public ErrorEvent(final String message) {
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
