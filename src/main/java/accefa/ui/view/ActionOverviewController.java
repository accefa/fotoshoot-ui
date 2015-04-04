package accefa.ui.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import accefa.event.ErrorEvent;
import accefa.event.InfoEvent;
import accefa.event.ProcessStartedEvent;
import accefa.event.ProcessStoppedEvent;

import com.google.common.eventbus.Subscribe;

/**
 * Controller für die Item Overview Klasse.
 */
public class ActionOverviewController {

   class ActionModel {

      private final StringProperty message = new SimpleStringProperty();
      private final ObjectProperty<LocalDateTime> time = new SimpleObjectProperty<>(LocalDateTime.now());

      public ActionModel(final String message) {
         this.message.set(message);
      }

      public StringProperty messageProperty() {
         return message;
      }

      public ObjectProperty<LocalDateTime> timePropertY() {
         return time;
      }
   }

   @FXML
   private void initialize() {
      tableActions.setPlaceholder(new Text(""));
      tableActions.setItems(FXCollections.observableArrayList());

      // Custom rendering of the table cell.
      final DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
      colTime.setCellFactory(column -> {
         return new TableCell<ActionModel, LocalDateTime>() {
            @Override
            protected void updateItem(final LocalDateTime item, final boolean empty) {
               super.updateItem(item, empty);
               if (item != null && !empty) {
                  setText(myDateFormatter.format(item));
               }
            }
         };
      });

      colTime.setCellValueFactory(cellData -> cellData.getValue().timePropertY());
      colMessage.setCellValueFactory(cellData -> cellData.getValue().messageProperty());

      colTime.prefWidthProperty().set(150);
      colMessage.prefWidthProperty().bind(
            tableActions.widthProperty().subtract(colTime.prefWidthProperty()).subtract(2));
   }

   @FXML
   private TableView<ActionModel> tableActions;

   @FXML
   private TableColumn<ActionModel, LocalDateTime> colTime;

   @FXML
   private TableColumn<ActionModel, String> colMessage;

   @Subscribe
   public void recordErrorEvent(final ErrorEvent event) {
      tableActions.getItems().add(new ActionModel(event.getMessage()));
   }

   @Subscribe
   public void recordInfoEvent(final InfoEvent event) {
      tableActions.getItems().add(new ActionModel(event.getMessage()));
   }

   @Subscribe
   public void recordProcessStartedEvent(final ProcessStartedEvent event) {
      tableActions.getItems().add(new ActionModel("Prozess gestartet"));
   }

   @Subscribe
   public void recordProcessStoppedEvent(final ProcessStoppedEvent event) {
      tableActions.getItems().add(new ActionModel("Prozess beendet"));
   }

}
