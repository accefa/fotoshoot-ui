package accefa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FotoShootProperties {

   private static final String PROPERTY_RASPI_URL = "RASPI_URL";

   private static final String PROPERTY_WEBSERVER_URL = "WEBSERVER_URL";

   private static final String FILE_RUNTIME = "runtime/fotoshoot-ui.properties";

   private static final String FILE_DEFAULT = "default.fotoshoot-ui.properties";

   private Properties clientProperties;

   private FotoShootProperties() {

   }

   private static FotoShootProperties instance;

   private static Object lock = new Object();

   public static FotoShootProperties getInstance() {
      synchronized (lock) {
         FotoShootProperties properties = instance;
         if (instance == null) {
            properties = new FotoShootProperties();
            properties.load();
         }
         return properties;
      }
   }

   /**
    * Lädt die Werte aus den Dateien. Falls keine Runtime-Properties existieren,
    * werden die Default Properties geladen.
    */
   public void load() {
      clientProperties = new Properties();
      try {
         InputStream stream = null;
         if (new File(FILE_RUNTIME).exists()) {
            stream = new FileInputStream(FILE_RUNTIME);
         } else {
            stream = this.getClass().getClassLoader().getResourceAsStream(FILE_DEFAULT);
         }
         if (stream != null) {
            clientProperties.load(stream);
         }
      } catch (final IOException io) {
         System.err.println("Could not load " + FILE_RUNTIME + " use default configuration.");
      }
   }

   /**
    * Persitiert die Werte.
    */
   public void save() {
      try {
         final File file = new File(FILE_RUNTIME);
         if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
         }
         final OutputStream out = new FileOutputStream(FILE_RUNTIME);
         clientProperties.store(out, null);
      } catch (final IOException e) {
         e.printStackTrace();
      }
   }

   public String getRaspiUrl() {
      return clientProperties.getProperty(PROPERTY_RASPI_URL);
   }

   public void setRaspiUrl(final String url) {
      clientProperties.setProperty(PROPERTY_RASPI_URL, url);
   }

   public String getWebserverUrl() {
      return clientProperties.getProperty(PROPERTY_WEBSERVER_URL);
   }

   public void setWebserverUrl(final String url) {
      clientProperties.setProperty(PROPERTY_WEBSERVER_URL, url);
   }

}
