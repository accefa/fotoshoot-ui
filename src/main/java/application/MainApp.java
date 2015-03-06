package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

   private Stage primaryStage;
   private BorderPane rootLayout;

   @FXML
   private final TextField txtKontrast = new TextField();
   @FXML
   private final TextField txtGraustufen = new TextField();
   @FXML
   private final TextField txtQualitaet = new TextField();
   @FXML
   private final TextField txtZuschnitt = new TextField();
   @FXML
   private final TextField txtPixelLinie = new TextField();

   @FXML
   private final Button btnStart = new Button();
   @FXML
   private final Button btnStop = new Button();
   @FXML
   private final Button btnReload = new Button();

   @FXML
   private final Label lblGradAnzeige = new Label();

   Image KorbBild = new Image(
         "http://foto.mein-schoener-garten.de/userimages/3499/or/2038793/baum-location-scout-04323805788.jpg");
   @FXML
   private final ImageView KorbView = new ImageView();

   // These work too, just in case
   // javafx.scene.image.Image KorbBild = new
   // javafx.scene.image.Image(getClass().getResource("test.png").toExternalForm());
   // Image KorbBild = new Image(new File("test.png").toURI().toString());

   public static void main(final String[] args) {
      launch(args);
   }

   @Override
   public void start(final Stage primaryStage) {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("Ballwurf");
      this.primaryStage.setResizable(false);

      loadRootLayout();
      loadOverview();
      loadImageView();
   }

   /**
    * RootLayout erzeugen
    */
   public void loadRootLayout() {
      try {
         // Load root layout from fxml file.
         final FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
         rootLayout = (BorderPane) loader.load();

         // Show the scene containing the root layout.
         final Scene scene = new Scene(rootLayout);
         primaryStage.setScene(scene);

         primaryStage.show();
      } catch (final IOException e) {
         e.printStackTrace();
      }
   }

   /**
    * Overview in RootLayout anzeigen
    */
   public void loadOverview() {
      try {
         final FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource("Overview.fxml"));
         final AnchorPane overview = (AnchorPane) loader.load();
         rootLayout.setCenter(overview);
      } catch (final IOException e) {
         e.printStackTrace();
      }
   }

   public void loadImageView() {
      KorbView.setImage(KorbBild);
      // KorbView.setFitWidth(330);
      // KorbView.setPreserveRatio(true);
      // KorbView.setSmooth(true);
      // KorbView.setCache(true);
      // KorbView.setVisible(true);

   }

   /**
    * Returns the main stage.
    * 
    * @return
    */
   public Stage getPrimaryStage() {
      return primaryStage;
   }

   public void StartPressed() {
      // Start the whole process
      System.out.println("Start");

   }

   public void ReloadPressed() {
      System.out.println(txtKontrast.getText());
      System.out.println(txtGraustufen.getText());
      System.out.println(txtQualitaet.getText());
      System.out.println(txtZuschnitt.getText());
      System.out.println(txtPixelLinie.getText());
   }

   public void StopPressed() {
      // end the whole process
      System.out.println("Stop");
   }
}