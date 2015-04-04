package accefa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import accefa.guice.FotoShootModule;
import accefa.guice.FotoShootModuleFactory;
import accefa.server.RestServerController;
import accefa.ui.view.ImageConfigController;
import accefa.ui.view.PiController;
import accefa.ui.view.RootLayoutController;
import accefa.util.ApplicationCliParser;
import accefa.util.ApplicationFxmlLoader;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Einstiegspunkt.
 */
public class Program extends Application {

   private static Injector injector;

   public static void main(final String[] args) {
      final String defaultModule = FotoShootModule.class.getName();
      final String concreteModule = new ApplicationCliParser(args).getFqdnModule(defaultModule);
      injector = Guice.createInjector(new FotoShootModuleFactory(concreteModule).create());
      launch();
   }

   @Override
   public void start(final Stage primaryStage) {
      final BorderPane rootLayout = createRootlayout();
      rootLayout.setCenter(createTabPane());
      primaryStage.setTitle("Foto-Shoot");
      primaryStage.setScene(new Scene(rootLayout));
      maximize(primaryStage);
      primaryStage.show();

      injector.getInstance(RestServerController.class).start();
   }

   @Override
   public void stop() {
      injector.getInstance(RestServerController.class).shutdown();
      injector.getInstance(ExecutorService.class).shutdownNow();
      Platform.exit();
   }

   private BorderPane createRootlayout() {
      return (BorderPane) new ApplicationFxmlLoader().createLoader(injector, RootLayoutController.class,
            "RootLayout.fxml");
   }

   private Parent createImageConfig() {
      return new ApplicationFxmlLoader().createLoader(injector, ImageConfigController.class, "ImageConfig.fxml");
   }

   private Parent createPi() {
      return new ApplicationFxmlLoader().createLoader(injector, PiController.class, "Pi.fxml");
   }

   private TabPane createTabPane() {
      final TabPane tabPane = new TabPane();
      final List<Tab> tabs = new ArrayList<Tab>();
      tabs.add(createTabPi());
      tabs.add(createTabImageRecognition());
      for (final Tab tab : tabs) {
         tab.setClosable(false);
         tabPane.getTabs().add(tab);
      }
      return tabPane;
   }

   private Tab createTabPi() {
      final Tab tab = new Tab("Pi");
      tab.setContent(createPi());
      return tab;
   }

   private Tab createTabImageRecognition() {
      final Tab tab = new Tab("Bild-Erkennung");
      tab.setContent(createImageConfig());
      return tab;
   }

   private void maximize(final Stage stage) {
      final Screen screen = Screen.getPrimary();
      final Rectangle2D bounds = screen.getVisualBounds();
      stage.setX(bounds.getMinX());
      stage.setY(bounds.getMinY());
      stage.setWidth(bounds.getWidth());
      stage.setHeight(bounds.getHeight());
   }

}
