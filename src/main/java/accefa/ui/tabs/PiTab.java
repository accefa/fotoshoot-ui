package accefa.ui.tabs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.apache.commons.validator.routines.UrlValidator;

import accefa.util.AlertException;
import accefa.util.FotoShootProperties;
import accefa.ui.models.ImageConfigModel;

/**
 * Pi Tab. Zustaendig fuer den Start- und Stop des Ballwurfprozesses.
 */
public class PiTab extends Tab {

	private final FotoShootProperties properties;

	private final Button btnStart;
	private final Label lblTimeMinutes;
	private final Label lblTimeSeconds;
	private final Label lblStopp;

	private final TextField txtUrl;
	private final Button btnSaveUrl;

	/**
	 * Definiert ob der Prozess laeuft.
	 */
	private final BooleanProperty processRunningProperty = new SimpleBooleanProperty(
			false);

	/**
	 * Url.
	 */
	private final StringProperty urlProperty = new SimpleStringProperty();

	/**
	 * Stoppuhr Minuten.
	 */
	private final StringProperty stopWatchTimeMinutesProperty = new SimpleStringProperty(
			"0m");

	/**
	 * Stoppuhr Sekunden.
	 */
	private final StringProperty stopWatchTimeSecondsProperty = new SimpleStringProperty(
			"0s");

	public PiTab() {
		super("Pi");

		properties = new FotoShootProperties();
		properties.load();
		urlProperty.set(properties.getUrl());

		btnStart = createButton("Starten");
		btnStart.disableProperty().bind(processRunningProperty);

		btnSaveUrl = createButton("Url Speichern");
		btnSaveUrl.disableProperty().bind(processRunningProperty);

		lblTimeMinutes = createLabel();
		lblTimeMinutes.textProperty().bind(stopWatchTimeMinutesProperty);

		lblTimeSeconds = createLabel();
		lblTimeSeconds.textProperty().bind(stopWatchTimeSecondsProperty);

		lblStopp = createLabel("Stopp");

		txtUrl = new TextField();
		txtUrl.disableProperty().bind(processRunningProperty);
		txtUrl.textProperty().bindBidirectional(urlProperty);

		setUpEventHandling();

		final VBox vbox = new VBox(8);
		vbox.getChildren().add(btnStart);
		final HBox hbox = new HBox(8);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(lblTimeMinutes);
		hbox.getChildren().add(lblTimeSeconds);
		vbox.getChildren().add(hbox);
		vbox.getChildren().add(lblStopp);
		vbox.getChildren().add(txtUrl);
		vbox.getChildren().add(btnSaveUrl);
		vbox.setAlignment(Pos.CENTER);
		setContent(vbox);
	}

	private Button createButton(final String title) {
		final Button button = new Button(title);
		button.setMinSize(120, 40);
		button.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		return button;
	}

	private Label createLabel() {
		return createLabel(null);
	}

	private Label createLabel(final String title) {
		final Label label = new Label(title);
		label.setStyle("-fx-font: 22 arial; -fx-base: #ff00ff;");
		return label;
	}

	private void startwebservice(String URL, String Befehl) {
		String url = URL + Befehl;
		ClientConfig clientConfig = new ClientConfig()
				.register(new JacksonFeature());
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget target = client.target(url);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.get();
		ImageConfigModel imageConfigModel = response
				.readEntity(ImageConfigModel.class);

		System.out.println(imageConfigModel);
		System.out.println("Aufgerufen");
		response.close();

	}

	private void setUpEventHandling() {
		btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					private Duration time = Duration.ZERO;

					@Override
					public void handle(final MouseEvent e) {
						try {
							startwebservice("http://localhost:8080/", "camera");

							processRunningProperty.set(true);
							final Timeline timeline = new Timeline(
									new KeyFrame(Duration.millis(100),
											new EventHandler<ActionEvent>() {
												@Override
												public void handle(
														final ActionEvent t) {
													final Duration duration = ((KeyFrame) t
															.getSource())
															.getTime();
													time = time.add(duration);

													double timeInSeconds = time
															.toSeconds();
													timeInSeconds = timeInSeconds
															- ((long) ((timeInSeconds / 60)) * 60);
													stopWatchTimeMinutesProperty.set(String
															.valueOf((long) time
																	.toMinutes())
															+ "m");
													stopWatchTimeSecondsProperty.set(String
															.format("%.1f",
																	timeInSeconds)
															+ "s");
												}
											}));
							timeline.setCycleCount(Timeline.INDEFINITE);
							timeline.play();
						} catch (Exception ex) {
							
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Warnung");
							alert.setHeaderText("Fehler aufgetreten!");
							alert.setContentText("Es konnte keine Verbindung hergestellt werden!");

							alert.showAndWait();
						}
					}
				});

		btnSaveUrl.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						final String[] schemas = { "http" };
						final UrlValidator urlValidator = new UrlValidator(
								schemas);
						if (urlValidator.isValid(urlProperty.get())) {
							properties.setUrl(urlProperty.get());
							properties.save();
							txtUrl.setStyle("-fx-base: #ffffff");
						} else {
							txtUrl.setStyle("-fx-base: #ff0000");
						}
					}
				});
	}
}
