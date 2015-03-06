package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWorld extends Application {

   public static void main(final String[] args) {
      launch(args);
   }

   private static Tab createTab(final String text) {
      final Tab tab = new Tab();
      tab.setText(text);
      tab.setClosable(false);
      // tab.setContent(new Rectangle(200, 200, Color.LIGHTSTEELBLUE));
      return tab;
   }

   @Override
   public void start(final Stage primaryStage) {
      primaryStage.setTitle("Fotoshoot");

      final TabPane tabPane = new TabPane();
      final Tab tab = createTab("Pi");
      final Label lbl = new Label("00:00:00");
      final Button btnStarten = new Button("Starten");
      btnStarten.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
      btnStarten.setMinSize(120, 120);
      btnStarten.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
         @Override
         public void handle(final MouseEvent e) {
            for (int i = 0; i < 100; i++) {
               Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                     lbl.setText(String.valueOf(System.currentTimeMillis()));
                  }
               });

            }
         }
      });
      final Button btnStoppen = new Button("Stoppen");
      btnStoppen.setMinSize(120, 120);
      btnStoppen.setStyle("-fx-font: 22 arial; -fx-base: #ff0000; width:500");
      final VBox hbox = new VBox(8);
      hbox.getChildren().add(btnStarten);

      lbl.setStyle("-fx-font: 22 arial;");
      hbox.getChildren().add(lbl);
      hbox.getChildren().add(btnStoppen);
      hbox.setAlignment(Pos.CENTER);
      tab.setContent(hbox);
      tabPane.getTabs().add(tab);
      tabPane.getTabs().add(createTab("Kamera"));
      tabPane.getTabs().add(createTab("Motoren"));

      final Scene scene = new Scene(tabPane, 600, 600);
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}
