package accefa.ui.tabs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PiTab extends Tab {

	Properties props = new Properties();

	public PiTab() throws IOException {
		super("Pi");

		final TextField txtURL = new TextField();
		final Label lbl = new Label("00:00:00");

		final Button btnStarten = new Button("Starten");
		btnStarten.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		btnStarten.setMinSize(120, 40);
		btnStarten.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						for (int i = 0; i < 100; i++) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									lbl.setText(String.valueOf(System
											.currentTimeMillis()));
								}
							});
						}
					}
				});

		final Button btnSpeichern = new Button("Speichern");
		btnSpeichern.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		btnSpeichern.setMinSize(120, 40);
		btnSpeichern.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						try {
							saveProperties(txtURL.getText());
						} catch (IOException | URISyntaxException e1) {
							e1.printStackTrace();
						}
					}
				});

		final Button btnStoppen = new Button("Stoppen");
		btnStoppen.setMinSize(120, 40);
		// Grau
		btnStoppen.setStyle("-fx-font: 22 arial; -fx-base: #C0C0C0; width:500");
		// btnStoppen.setStyle("-fx-font: 22 arial; -fx-base: #ff0000; width:500");
		// Rot
		btnStoppen.setDisable(true);

		final VBox hbox = new VBox(8);
		hbox.getChildren().add(btnStarten);
		lbl.setStyle("-fx-font: 22 arial;");
		hbox.getChildren().add(lbl);
		hbox.getChildren().add(btnStoppen);
		hbox.getChildren().add(txtURL);
		hbox.getChildren().add(btnSpeichern);
		hbox.setAlignment(Pos.CENTER);

		setContent(hbox);
		txtURL.setText(loadProperties());
	}


	public final void saveProperties(String value) throws IOException,
			URISyntaxException {
		try {
			props.setProperty("URL", value);
			URL out = getClass().getClassLoader().getResource("properties.txt");
			FileWriter file = new FileWriter(new File(out.toURI()));
			props.store(file, null);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public final String loadProperties() throws IOException {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(
					"properties.txt");
			props.load(in);
			in.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props.getProperty("URL");
	}

}
