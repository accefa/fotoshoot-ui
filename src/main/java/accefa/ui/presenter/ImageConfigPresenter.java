package accefa.ui.presenter;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import accefa.service.RaspiService;
import accefa.service.RaspiServiceImpl;
import accefa.ui.models.ImageConfigModel;
import accefa.ui.view.ImageConfigView;

public class ImageConfigPresenter {

   private ImageConfigModel model;

   private final ImageConfigView view;

   public ImageConfigPresenter(final ImageConfigModel model, final ImageConfigView view) {
      this.model = model;
      this.view = view;

      bindProperties();
      setupEventHandling();
   }

   public void load() {
      loadData();
   }

   private void bindProperties() {
      view.contrastProperty().bindBidirectional(model.contrastProperty());
      view.greyscaleProperty().bindBidirectional(model.greyscaleProperty());
      view.lineYProperty().bindBidirectional(model.lineYProperty());
      view.lineHProeprty().bindBidirectional(model.lineHProperty());
      view.greyscaleThresholdProperty().bindBidirectional(model.greyscaleThresholdProperty());
      view.qualityProperty().bindBidirectional(model.qualityProperty());
      view.cropXProperty().bindBidirectional(model.cropXProperty());
   }

   private void setupEventHandling() {
      this.view.addEventHandlerReloadImage(new EventHandler<MouseEvent>() {
         @Override
         public void handle(final MouseEvent event) {
            saveData();
            loadData();
         }
      });
   }

   /**
    * Lädt Daten von Server.
    */
   private void loadData() {
      final RaspiService service = new RaspiServiceImpl();
      model = service.readImageConfigModel();
      bindProperties();
      // TODO Eventuell Bestehende Bindings unbinden (vom alten Model)
   }

   /**
    * Sendet die Daten an den Server.
    */
   private void saveData() {

   }

}
