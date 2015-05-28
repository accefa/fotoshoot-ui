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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import accefa.guice.FotoShootModule;
import accefa.guice.FotoShootModuleFactory;
import accefa.ui.view.ActionOverviewController;
import accefa.ui.view.ImageConfigController;
import accefa.ui.view.PiController;
import accefa.ui.view.RootLayoutController;
import accefa.ui.view.drive.DriveController;
import accefa.util.ApplicationCliParser;
import accefa.util.ApplicationFxmlLoader;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Einstiegspunkt.
 */
public class Program extends Application {

   private static final String DRIVES_LAYOUT = "Drives.fxml";
   private static final String DRIVES_TITLE = "Motoren";
   private static final String ACTION_LOG_LAYOUT = "ActionOverview.fxml";
   private static final String ACTION_LOG_TITLE = "Log";
   private static final String IMAGE_CONFIG_LAYOUT = "ImageConfig.fxml";
   private static final String IMAGE_CONFIG_TITLE = "Bild-Erkennung";
   private static final String PI_LAYOUT = "Pi.fxml";
   private static final String PI_TITLE = "Ballwurf";
   private static final String ROOT_LAYOUT = "RootLayout.fxml";
   private static final String ROOT_TITLE = "Foto-Shoot";

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
      primaryStage.setTitle(ROOT_TITLE);
      primaryStage.setScene(new Scene(rootLayout));
      primaryStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("Tennisball.png")));
      maximize(primaryStage);
      primaryStage.show();
   }

   @Override
   public void stop() {
      injector.getInstance(ExecutorService.class).shutdownNow();
      Platform.exit();
   }

   private BorderPane createRootlayout() {
      return (BorderPane) new ApplicationFxmlLoader().createLoader(injector, RootLayoutController.class, ROOT_LAYOUT);
   }

   private TabPane createTabPane() {
      final TabPane tabPane = new TabPane();
      final List<Tab> tabs = new ArrayList<Tab>();
      tabs.add(createTabPi());
      tabs.add(createTabImageRecognition());
      tabs.add(createTabDrives());
      tabs.add(createTabActionLog());
      for (final Tab tab : tabs) {
         tab.setClosable(false);
         tabPane.getTabs().add(tab);
      }
      return tabPane;
   }

   private Tab createTabPi() {
      final Tab tab = new Tab(PI_TITLE);
      tab.setContent(createNodePi());
      return tab;
   }

   private Tab createTabImageRecognition() {
      final Tab tab = new Tab(IMAGE_CONFIG_TITLE);
      tab.setContent(createNodeImageConfig());
      return tab;
   }

   private Tab createTabActionLog() {
      final Tab tab = new Tab(ACTION_LOG_TITLE);
      tab.setContent(createNodeActionLog());
      return tab;
   }

   private Tab createTabDrives() {
      final Tab tab = new Tab(DRIVES_TITLE);
      tab.setContent(createNodeDrives());
      return tab;
   }

   private Parent createNodeImageConfig() {
      return new ApplicationFxmlLoader().createLoader(injector, ImageConfigController.class, IMAGE_CONFIG_LAYOUT);
   }

   private Parent createNodePi() {
      return new ApplicationFxmlLoader().createLoader(injector, PiController.class, PI_LAYOUT);
   }

   private Parent createNodeActionLog() {
      return new ApplicationFxmlLoader().createLoader(injector, ActionOverviewController.class, ACTION_LOG_LAYOUT);
   }

   private Parent createNodeDrives() {
      return new ApplicationFxmlLoader().createLoader(injector, DriveController.class, DRIVES_LAYOUT);
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
