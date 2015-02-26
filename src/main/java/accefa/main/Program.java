package accefa.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Program extends Application {

   @Override
   public void start(final Stage primaryStage) {
      final Button btn = new Button();
      btn.setText("Wüthrich is on fire!");
      btn.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(final ActionEvent event) {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(primaryStage);
            final VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("you are fired!"));
            final Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
         }
      });

      final StackPane root = new StackPane(); 
      root.getChildren().add(btn);

      final Scene scene = new Scene(root, 300, 250);

      primaryStage.setTitle("ACCEFA!");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   public static void main(final String[] args) {
      launch(args);
   }
}
