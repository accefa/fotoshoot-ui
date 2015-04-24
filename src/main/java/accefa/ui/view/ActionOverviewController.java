package accefa.ui.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import accefa.ui.model.LogModel;

import com.google.common.eventbus.Subscribe;

/**
 * Controller für die Item Overview Klasse.
 */
public class ActionOverviewController {

   @FXML
   private TableView<LogModel> tableActions;

   @FXML
   private TableColumn<LogModel, LocalDateTime> colTime;

   @FXML
   private TableColumn<LogModel, String> colMessage;
   
   @FXML
   private TableColumn<LogModel, String> colLevel;
   
   @FXML
   private TableColumn<LogModel, String> colSource;
   
   @FXML
   private void initialize() {
      tableActions.setPlaceholder(new Text(""));
      tableActions.setItems(FXCollections.observableArrayList());

      // Custom rendering of the table cell.
      final DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
      colTime.setCellFactory(column -> {
         return new TableCell<LogModel, LocalDateTime>() {
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
      colLevel.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
      colSource.setCellValueFactory(cellData -> cellData.getValue().sourceProperty());

      colTime.prefWidthProperty().set(150);
      colMessage.prefWidthProperty().bind(
            tableActions.widthProperty().subtract(colTime.prefWidthProperty()).subtract(2));
   }

   @Subscribe
   public void recordErrorEvent(final ErrorEvent event) {
      tableActions.getItems().add(new LogModel(event.getMessage(), "ERROR", "Client"));
   }

   @Subscribe
   public void recordInfoEvent(final InfoEvent event) {
      tableActions.getItems().add(new LogModel(event.getMessage(), "ERROR", "Client"));
   }

   @Subscribe
   public void recordProcessStartedEvent(final ProcessStartedEvent event) {
      tableActions.getItems().add(new LogModel("Prozess gestartet", "INFO", "Client"));
   }

   @Subscribe
   public void recordProcessStoppedEvent(final ProcessStoppedEvent event) {
      tableActions.getItems().add(new LogModel("Prozess beendet", "INFO", "Client"));
   }

}
