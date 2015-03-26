package accefa.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import accefa.ui.models.ImageConfigModel;
import accefa.ui.presenter.ImageConfigPresenter;
import accefa.ui.tabs.EngineConfigTab;
import accefa.ui.tabs.PiTab;
import accefa.ui.view.ImageConfigView;

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
      final ImageConfigView view = new ImageConfigView();
      final ImageConfigModel model = new ImageConfigModel();
      final ImageConfigPresenter presenter = new ImageConfigPresenter(model, view);
      presenter.load();
      final Tab tab = new Tab("Bild-Erkennung");
      tab.setContent(view.getNode());
      return tab;
   }

   private void configureTabs() {
      for (final Tab tab : tabs) {
         tab.setClosable(false);
         tabPane.getTabs().add(tab);
      }
   }

}
