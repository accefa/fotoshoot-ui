package accefa.service;

public class RaspiServiceException extends Exception {

   private static final long serialVersionUID = 1L;

   public RaspiServiceException() {
      super();
   }

   public RaspiServiceException(final Exception e) {
      super(e);
   }
}
