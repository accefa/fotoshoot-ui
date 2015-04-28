package accefa.ui.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import accefa.event.ErrorEvent;
import accefa.event.InfoEvent;
import accefa.event.ProcessStartedEvent;
import accefa.event.ProcessStoppedEvent;
import accefa.service.ServiceException;
import accefa.service.general.GeneralService;
import accefa.ui.model.LogModel;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

/**
 * Controller für die Item Overview Klasse.
 */
public class ActionOverviewController {

	private GeneralService service;

	@FXML
	private Button btnReload;

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

	@Inject
	public ActionOverviewController(final GeneralService service) {
		this.service = service;
	}

	@FXML
	public void reloadLog(ActionEvent event) {
		loadData();
	}

	@FXML
	private void initialize() {
		tableActions.setItems(FXCollections.observableArrayList());

		// Custom rendering of the table cell.
		final DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		colTime.setCellFactory(column -> {
			return new TableCell<LogModel, LocalDateTime>() {
				@Override
				protected void updateItem(final LocalDateTime item,
						final boolean empty) {
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
		colMessage.prefWidthProperty().bind(tableActions.widthProperty().subtract(colTime.prefWidthProperty()).subtract(2));

		tableActions.getSortOrder().add(colTime);

		loadData();
	}

	@Subscribe
	public void recordErrorEvent(final ErrorEvent event) {
		addNewLog(event.getMessage(), "ERROR", "Client");
	}

	@Subscribe
	public void recordInfoEvent(final InfoEvent event) {
		addNewLog(event.getMessage(), "ERROR", "Client");
	}

	@Subscribe
	public void recordProcessStartedEvent(final ProcessStartedEvent event) {
		addNewLog("Prozess gestartet", "INFO", "Client");
	}

	@Subscribe
	public void recordProcessStoppedEvent(final ProcessStoppedEvent event) {
		addNewLog("Prozess beendet", "INFO", "Client");
	}
	
	private void addNewLog(String message, String level, String source) {
		tableActions.getItems().add(new LogModel(message, level, source));
		resort();
	}

	private void loadData() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					tableActions.getItems().addAll(service.getLogs());
					resort();
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void resort() {
		tableActions.getSortOrder().add(colTime);
		colTime.setSortType(TableColumn.SortType.DESCENDING);
		colTime.setSortable(true);
	}

}
