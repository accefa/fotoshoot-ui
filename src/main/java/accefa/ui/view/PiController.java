package accefa.ui.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.validator.routines.UrlValidator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.ui.models.ImageConfigModel;
import accefa.util.FotoShootProperties;

public class PiController {

	private FotoShootProperties properties;

	@FXML
	private Button btnStart;

	@FXML
	private Label lblTime;

	@FXML
	private TextField txtUrl;

	@FXML
	private Button btnSaveUrl;

	@FXML
	private void initialize() {
		properties = new FotoShootProperties();
		properties.load();
		urlProperty.set(properties.getUrl());
		btnStart.disableProperty().bind(processRunningProperty);
		btnSaveUrl.disableProperty().bind(processRunningProperty);
		lblTime.textProperty().bind(
				Bindings.concat(stopWatchTimeMinutesProperty).concat(" ")
						.concat(stopWatchTimeSecondsProperty));
		txtUrl.textProperty().bindBidirectional(urlProperty);
		setUpEventHandling();
	}

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

	private void setUpEventHandling() {
		btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					private Duration time = Duration.ZERO;

					@Override
					public void handle(final MouseEvent e) {
						try {
							// startwebservice("http://localhost:8080/",
					// "camera");

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
						} catch (final Exception ex) {

					final Alert alert = new Alert(AlertType.WARNING);
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

	private void startwebservice(final String URL, final String Befehl) {
		final String url = URL + Befehl;
		final ClientConfig clientConfig = new ClientConfig()
				.register(new JacksonFeature());
		final Client client = ClientBuilder.newClient(clientConfig);
		final WebTarget target = client.target(url);
		final Response response = target.request(
				MediaType.APPLICATION_JSON_TYPE).get();
		final ImageConfigModel imageConfigModel = response
				.readEntity(ImageConfigModel.class);

		System.out.println(imageConfigModel);
		System.out.println("Aufgerufen");
		response.close();

	}
}
