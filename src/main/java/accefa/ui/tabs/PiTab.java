package accefa.ui.tabs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import org.apache.commons.validator.routines.UrlValidator;

import accefa.util.FotoShootProperties;

public class PiTab extends Tab {

	private final FotoShootProperties properties;
	private Timeline timeline;
	private final DoubleProperty timeSeconds = new SimpleDoubleProperty();
	private Duration time = Duration.ZERO;
	private final Button btnStoppen = new Button("Stoppen");
	private final Button btnStarten = new Button("Starten");
	private final Button btnSpeichern = new Button("Speichern");

	public PiTab() {
		super("Pi");

		setStartButton(true);
		setSpeichernButton(true);
		setStoppButton(false);
				
		properties = new FotoShootProperties();
		properties.load();

		final TextField txtURL = new TextField();
		final Label lbl = new Label("0.0");

		lbl.textProperty().bind(timeSeconds.asString());

		btnStarten.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						setStartButton(false);
						if (timeline != null) {
							time = Duration.ZERO;
							timeSeconds.set(time.toSeconds());
						} else {
							timeline = new Timeline(new KeyFrame(Duration
									.millis(100),
									new EventHandler<ActionEvent>() {
										@Override
										public void handle(final ActionEvent t) {
											final Duration duration = ((KeyFrame) t
													.getSource()).getTime();
											time = time.add(duration);
											timeSeconds.set(time.toSeconds());
										}
									}));
							timeline.setCycleCount(Timeline.INDEFINITE);
							timeline.play();
						}
					}
				});

		btnSpeichern.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						final String url = txtURL.getText();
						final String[] schemas = { "http" };
						final UrlValidator urlValidator = new UrlValidator(
								schemas);
						if (urlValidator.isValid(url)) {
							properties.setUrl(url);
							properties.save();
							txtURL.setStyle("-fx-base: #FFFFFF");

						} else {
							txtURL.setStyle("-fx-base: #ff0000");
						}

					}
				});

		final VBox hbox = new VBox(8);
		hbox.getChildren().add(btnStarten);
		lbl.setStyle("-fx-font: 22 arial;");
		hbox.getChildren().add(lbl);
		hbox.getChildren().add(btnStoppen);
		hbox.getChildren().add(txtURL);
		hbox.getChildren().add(btnSpeichern);
		hbox.setAlignment(Pos.CENTER);

		setContent(hbox);
		txtURL.setText(properties.getUrl());
	}

	public void setStartButton(boolean value) {
		btnStarten.setMinSize(120, 40);
		if (value) {// Enable
			btnStarten.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
			btnStarten.setDisable(false);
		} else {// Disable
			btnStarten.setStyle("-fx-font: 22 arial; -fx-base: #C0C0C0;");
			btnStarten.setDisable(true);
		}
	}

	public void setStoppButton(boolean value) {
		btnStoppen.setMinSize(120, 40);
		if (value) {// Enable
			btnStoppen.setStyle("-fx-font: 22 arial; -fx-base: #ff0000;");
			btnStoppen.setDisable(true);
		} else {// Disable
			btnStoppen
					.setStyle("-fx-font: 22 arial; -fx-base: #C0C0C0; width:500");
			btnStoppen.setDisable(true);
		}
	}
	
	public void setSpeichernButton(boolean value) {
		btnSpeichern.setMinSize(120, 40);
		if (value) {// Enable
			btnSpeichern.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
			btnSpeichern.setDisable(false);
		} else {// Disable
			btnSpeichern
					.setStyle("-fx-font: 22 arial; -fx-base: #C0C0C0; width:500");
			btnSpeichern.setDisable(true);
		}
	}
}
