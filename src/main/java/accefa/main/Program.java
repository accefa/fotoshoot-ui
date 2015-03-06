package accefa.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Program extends Application {

   @Override
   public void start(final Stage primaryStage) {
      
	  Label label = new Label("a label");
	  final Button btn = new Button();
      btn.setText("Wüthrich is on fire!");

      final StackPane root = new StackPane(); 
      root.getChildren().add(btn);
      root.getChildren().add(label);
      

      final Scene scene = new Scene(root, 350, 300);

      primaryStage.setTitle("Ballwurfmaschine UI");
      primaryStage.setResizable(false);
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   public static void main(final String[] args) {
      launch(args);
   }
}
