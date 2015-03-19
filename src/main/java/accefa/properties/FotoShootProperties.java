package accefa.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FotoShootProperties {

   private static final String PROPERTY_URL = "URL";

   private static final String FILENAME = "runtime/fotoshoot-ui.properties";

   private Properties clientProperties;

   public void load() {
      clientProperties = new Properties();
      try {
         final InputStream stream = new FileInputStream(FILENAME);
         if (stream != null) {
            clientProperties.load(stream);
         }
      } catch (final IOException io) {
         System.err.println("Could not load " + FILENAME + " use default configuration.");
      }
   }

   public void save() {
      try {
         final OutputStream out = new FileOutputStream(FILENAME);
         clientProperties.store(out, null);
      } catch (final IOException e) {
         e.printStackTrace();
      }
   }

   public String getUrl() {
      return clientProperties.getProperty(PROPERTY_URL);
   }

   public void setUrl(final String url) {
      clientProperties.setProperty(PROPERTY_URL, url);
   }

}
