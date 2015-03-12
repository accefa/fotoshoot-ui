package accefa.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import accefa.ui.tabs.EngineConfigTab;
import accefa.ui.tabs.ImageConfigTab;
import accefa.ui.tabs.PiTab;

public class FotoShootView extends Application {

   public void show(final String[] args) {
      launch(args);
   }

   @Override
   public void start(final Stage primaryStage) throws Exception {
      primaryStage.setTitle("Fotoshoot");

      final TabPane tabPane = new TabPane();

      final Tab tabPi = new PiTab();
      final Tab tabImageConfig = new ImageConfigTab();
      final Tab tagEngineConfig = new EngineConfigTab();

      tabPane.getTabs().add(tabPi);
      tabPane.getTabs().add(tabImageConfig);
      tabPane.getTabs().add(tagEngineConfig);

      final Scene scene = new Scene(tabPane, 600, 600);
      primaryStage.setScene(scene);
      primaryStage.show();
   }

}
