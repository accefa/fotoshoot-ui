package accefa.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import accefa.service.RaspiService;
import accefa.service.RaspiServiceImpl;
import accefa.service.RaspiUrlBuilder;
import accefa.ui.presenter.ImageConfigPresenter;
import accefa.ui.tabs.EngineConfigTab;
import accefa.ui.tabs.PiTab;

public class FotoShootUi extends Application {

   private TabPane tabPane;

   private List<Tab> tabs;

   public void show() {
      launch();
   }

   @Override
   public void start(final Stage primaryStage) throws Exception {
      primaryStage.setTitle("Fotoshoot");

      tabPane = new TabPane();
      tabs = new ArrayList<Tab>();
      tabs.add(new PiTab());
      tabs.add(createTabImageRecognition());
      tabs.add(new EngineConfigTab());

      configureTabs();

      final Scene scene = new Scene(tabPane, 1000, 600);
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   private Tab createTabImageRecognition() {
      final RaspiService service = new RaspiServiceImpl(new RaspiUrlBuilder("http://localhost:8080"));
      final ImageConfigPresenter presenter = new ImageConfigPresenter(service);
      presenter.load();

      final Tab tab = new Tab("Bild-Erkennung");
      tab.setContent(presenter.getView());
      return tab;
   }

   private void configureTabs() {
      for (final Tab tab : tabs) {
         tab.setClosable(false);
         tabPane.getTabs().add(tab);
      }
   }

}
