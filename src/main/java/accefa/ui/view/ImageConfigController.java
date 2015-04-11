package accefa.ui.view;

import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import accefa.event.ErrorEvent;
import accefa.event.InfoEvent;
import accefa.service.RaspiService;
import accefa.service.RaspiServiceException;
import accefa.ui.model.ImageConfigModel;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class ImageConfigController {

   private final RaspiService service;

   private final ExecutorService executor;

   private final EventBus eventBus;

   private ImageConfigModel imageConfigModel;

   @FXML
   private TitledPane titledPaneImage;

   @FXML
   private TitledPane titledPaneConfiguration;

   @FXML
   private Label lblKontrast;

   @FXML
   private Slider sliderContrast;

   @FXML
   private Label lblGreyscale;

   @FXML
   private CheckBox toogleGreyscale;

   @FXML
   private Label lblGreyscaleThreshold;

   @FXML
   private Slider sliderGreyscaleThreshold;

   @FXML
   private Label lblQuality;

   @FXML
   private Slider sliderQuality;

   @FXML
   private Label lblCropX;

   @FXML
   private Slider sliderCropX;

   @FXML
   private Label lblLineY;

   @FXML
   private Slider sliderLineY;

   @FXML
   private Label lblLineH;

   @FXML
   private Slider sliderLineH;

   @FXML
   private Button btnReloadImage;

   @FXML
   private ImageView imageView;

   @Inject
   public ImageConfigController(final RaspiService service, final ExecutorService executor, final EventBus eventBus) {
      this.service = service;
      this.executor = executor;
      this.eventBus = eventBus;
   }

   @FXML
   private void initialize() {
      loadData();
   }

   private void loadData() {
      imageConfigModel = new ImageConfigModel();
      bindProperties(imageConfigModel);
      loadImageConfigModel();
      imageView.setImage(getDefaultImage());

      btnReloadImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
         @Override
         public void handle(final MouseEvent event) {
            saveImageConfigModel();
            loadImageConfigModel();
         }
      });
   }

   private void loadImage(final String url) {
      Image image = new Image(url);
      if (image.isError()) {
         image = getDefaultImage();
      }
      imageView.setImage(image);
   }

   private Image getDefaultImage() {
      return new Image(this.getClass().getClassLoader().getResourceAsStream("404.jpg"));
   }

   /**
    * Lädt Daten von Server.
    */
   private void loadImageConfigModel() {
      final Task<ImageConfigModel> task = new Task<ImageConfigModel>() {
         @Override
         protected ImageConfigModel call() throws RaspiServiceException {
            return service.readImageConfigModel();
         }

         @Override
         protected void succeeded() {
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  final ImageConfigModel model = valueProperty().get();
                  if (valueProperty().get() != null) {
                     imageConfigModel = model;
                     bindProperties(model);
                     loadImage(service.getImageUrl());
                     eventBus.post(new InfoEvent("Konfiguration wurde geladen."));
                  }
               }
            });
         }
      };
      executor.execute(task);
   }

   private void bindProperties(final ImageConfigModel model) {
      lblKontrast.textProperty().bind(
            Bindings.concat("Kontrast (").concat(model.contrastProperty().asString()).concat(")"));
      sliderContrast.valueProperty().bindBidirectional(model.contrastProperty());

      toogleGreyscale.selectedProperty().bindBidirectional(model.greyscaleProperty());

      lblGreyscaleThreshold.textProperty().bind(
            Bindings.concat("Graustufen Schwellwert (").concat(model.greyscaleThresholdProperty().asString())
                  .concat(")"));
      sliderGreyscaleThreshold.valueProperty().bindBidirectional(model.greyscaleThresholdProperty());

      lblQuality.textProperty().bind(
            Bindings.concat("Qualität (").concat(model.qualityProperty().asString()).concat(")"));
      sliderQuality.valueProperty().bindBidirectional(model.qualityProperty());

      lblCropX.textProperty().bind(
            Bindings.concat("Zuschnitt X (").concat(model.cropXProperty().asString()).concat(")"));
      sliderCropX.valueProperty().bindBidirectional(model.cropXProperty());

      lblLineY.textProperty().bind(Bindings.concat("Linie Y (").concat(model.lineYProperty().asString()).concat(")"));
      sliderLineY.valueProperty().bindBidirectional(model.lineYProperty());

      lblLineH.textProperty().bind(Bindings.concat("Linie H (").concat(model.lineHProperty().asString()).concat(")"));
      sliderLineH.valueProperty().bindBidirectional(model.lineHProperty());
   }

   /**
    * Sendet die Daten an den Server.
    */
   private void saveImageConfigModel() {
      titledPaneConfiguration.setDisable(true);
      final Task<Void> task = new Task<Void>() {
         @Override
         protected Void call() throws RaspiServiceException {
            service.saveImageConfigModel(imageConfigModel);
            return null;
         }

         @Override
         protected void failed() {
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  eventBus.post(new ErrorEvent("Es ist ein Fehler aufgetreten: "
                        + exceptionProperty().get().getMessage()));
                  exceptionProperty().get().printStackTrace();
                  titledPaneConfiguration.setDisable(false);
               }
            });
         }

         @Override
         protected void succeeded() {
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  titledPaneConfiguration.setDisable(false);
                  eventBus.post(new InfoEvent("Konfiguration wurde gespeichert"));
               }
            });
         }
      };
      executor.execute(task);
   }

}
