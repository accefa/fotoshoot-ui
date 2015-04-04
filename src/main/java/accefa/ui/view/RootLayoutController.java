package accefa.ui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import accefa.event.ErrorEvent;
import accefa.event.InfoEvent;
import accefa.event.ProcessStartedEvent;
import accefa.event.ProcessStoppedEvent;

import com.google.common.eventbus.Subscribe;

/**
 * Controller für die Item Overview Klasse.
 */
public class RootLayoutController {

   @FXML
   private Label stateLabel;

   @FXML
   private BorderPane borderPane;

   @Subscribe
   public void recordErrorEvent(final ErrorEvent event) {
      stateLabel.setText(event.getMessage());
   }

   @Subscribe
   public void recordInfoEvent(final InfoEvent event) {
      stateLabel.setText(event.getMessage());
   }

   @Subscribe
   public void recordProcessStartedEvent(final ProcessStartedEvent event) {
      borderPane.setDisable(true);
   }

   @Subscribe
   public void recordProcessStoppedEvent(final ProcessStoppedEvent event) {
      borderPane.setDisable(false);
   }

}
