package accefa.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.util.Callback;

import com.google.inject.Injector;

public class ApplicationFxmlLoader {

   /**
    * Gibt den Parent des FXML File zurück. ControllerClazz und FMXL-File müssen
    * im gleichen Package sein.
    *
    * @param injector
    *           Guice-Injector.
    * @param controllerClazz
    *           Controller-Class.
    * @param fxmlFile
    *           FXML-File.
    * @return Parent-Node.
    */
   public Parent createLoader(final Injector injector, final Class<?> controllerClazz, final String fxmlFile) {
      try {
         final Callback<Class<?>, Object> guiceControllerFactory = new Callback<Class<?>, Object>() {
            @Override
            public Object call(final Class<?> clazz) {
               return injector.getInstance(clazz);
            }
         };

         final FXMLLoader loader = new FXMLLoader(controllerClazz.getResource(fxmlFile), null,
               new JavaFXBuilderFactory(), guiceControllerFactory);

         return loader.load();
      } catch (final IOException e) {
         throw new RuntimeException(e);
      }
   }

}
