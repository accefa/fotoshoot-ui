package accefa.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import accefa.ui.tabs.EngineConfigTab;
import accefa.ui.tabs.ImageConfigTab;
import accefa.ui.tabs.PiTab;

public class FotoShootView extends Application {

   private TabPane tabPane;

   private List<Tab> tabs;

   public void show(final String[] args) {
      launch(args);
   }

   @Override
   public void start(final Stage primaryStage) throws Exception {
      primaryStage.setTitle("Fotoshoot");

      tabPane = new TabPane();
      tabs = new ArrayList<Tab>();
      tabs.add(new PiTab());
      tabs.add(new ImageConfigTab());
      tabs.add(new EngineConfigTab());

      configureTabs();

      final Scene scene = new Scene(tabPane, 600, 600);
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   private void configureTabs() {
      for (final Tab tab : tabs) {
         tab.setClosable(false);
         tabPane.getTabs().add(tab);
      }
   }

}
