package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Jede Java-FX Applikation muss Application extenden
public class HelloWorld extends Application {

   public static void main(final String[] args) {
      launch(args);
   }

   // Startpunkt für jede Java FX Applikation
   // Stage - Toplevel JavaFx Container
   // Scene - Container für all Content
   @Override
   public void start(final Stage primaryStage) {
      primaryStage.setTitle("Hello World!");
      final Button btn = new Button();
      btn.setText("Say 'Hello World'");
      btn.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(final ActionEvent event) {
            System.out.println("Hello World!");
         }
      });

      final StackPane root = new StackPane();
      root.getChildren().add(btn);
      primaryStage.setScene(new Scene(root, 300, 250));
      primaryStage.show();
   }
}
