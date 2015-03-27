package accefa.service;

public class RaspiUrlBuilder {

   private final String baseUrl;

   public RaspiUrlBuilder(final String baseUrl) {
      this.baseUrl = baseUrl;
   }

   public String camera() {
      return baseUrl + "/camera";
   }

}
