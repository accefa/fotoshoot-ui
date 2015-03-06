package application;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
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


public class MainApp extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @FXML private TextField txtKontrast = new TextField();
    @FXML private TextField txtGraustufen = new TextField();
    @FXML private TextField txtQualitaet = new TextField();
    @FXML private TextField txtZuschnitt = new TextField();
    @FXML private TextField txtPixelLinie = new TextField();
    
    @FXML private Button btnStart = new Button();
    @FXML private Button btnStop = new Button();
    @FXML private Button btnReload = new Button();
    
    @FXML private Label lblGradAnzeige = new Label();
    
    Image KorbBild = new Image("http://foto.mein-schoener-garten.de/userimages/3499/or/2038793/baum-location-scout-04323805788.jpg");
    @FXML private ImageView KorbView = new ImageView();
    
    //These work too, just in case
    //javafx.scene.image.Image KorbBild = new javafx.scene.image.Image(getClass().getResource("test.png").toExternalForm());
    //Image KorbBild = new Image(new File("test.png").toURI().toString());

    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overview in RootLayout anzeigen
     */
    public void loadOverview() {
        try {        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Overview.fxml"));
            AnchorPane overview = (AnchorPane) loader.load();
            rootLayout.setCenter(overview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadImageView(){
        KorbView.setImage(KorbBild);
        //KorbView.setFitWidth(330);
        //KorbView.setPreserveRatio(true);
        //KorbView.setSmooth(true);
        //KorbView.setCache(true);
        //KorbView.setVisible(true);
    	
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    
    public void StartPressed(){
    	//Start the whole process
    	System.out.println("Start");
    	
    }
    public void ReloadPressed(){
    	System.out.println(txtKontrast.getText());
    	System.out.println(txtGraustufen.getText());
    	System.out.println(txtQualitaet.getText());
    	System.out.println(txtZuschnitt.getText());
    	System.out.println(txtPixelLinie.getText());
    }
    public void StopPressed(){
    	//end the whole process
    	System.out.println("Stop");
    }
}