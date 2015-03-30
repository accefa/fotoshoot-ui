package accefa.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import accefa.server.RestServerController;
import accefa.service.RaspiService;
import accefa.service.RaspiServiceMock;
import accefa.ui.tabs.EngineConfigTab;
import accefa.ui.view.ImageConfigController;
import accefa.ui.view.PiController;

public class Program extends Application {

   private RaspiService raspiService;

   private ExecutorService executorService;

   private RestServerController restServerController;

   public static void main(final String[] args) {
      launch();
   }

   @Override
   public void start(final Stage stage) throws Exception {
      // this.raspiService = new RaspiServiceRest();
      raspiService = new RaspiServiceMock();
      executorService = Executors.newSingleThreadExecutor();

      stage.setTitle("Fotoshoot");
      stage.setScene(new Scene(createTabPane()));
      maximize(stage);
      stage.show();
   }

   @Override
   public void stop() {
      restServerController.shutdown();
      executorService.shutdownNow();
   }

   private TabPane createTabPane() {
      final TabPane tabPane = new TabPane();
      final List<Tab> tabs = new ArrayList<Tab>();
      tabs.add(createTabPi());
      tabs.add(createTabImageRecognition());
      tabs.add(new EngineConfigTab());
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

   private Parent createPi() {
      try {
         final FXMLLoader loader = new FXMLLoader();
         loader.setLocation(PiController.class.getResource("Pi.fxml"));
         final Parent parent = loader.load();
         final PiController controller = loader.getController();
         controller.setRaspiService(raspiService);
         controller.setExecutorService(executorService);

         restServerController = new RestServerController(controller);
         restServerController.start();

         return parent;
      } catch (final IOException e) {
         throw new RuntimeException(e);
      }
   }

   private Parent createImageConfig() {
      try {
         final FXMLLoader loader = new FXMLLoader();
         loader.setLocation(ImageConfigController.class.getResource("ImageConfig.fxml"));
         final Parent parent = loader.load();
         final ImageConfigController controller = loader.getController();
         controller.setRaspiService(raspiService);
         controller.setExecutorService(executorService);
         controller.loadData();
         return parent;
      } catch (final IOException e) {
         throw new RuntimeException(e);
      }
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
