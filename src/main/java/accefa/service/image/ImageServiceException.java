package accefa.service.image;

public class ImageServiceException extends Exception {

   private static final long serialVersionUID = 1L;

   public ImageServiceException() {
      super();
   }

   public ImageServiceException(final Exception e) {
      super(e);
   }
}
