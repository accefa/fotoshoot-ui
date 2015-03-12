package accefa.ui.tabs;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ImageConfigTab extends Tab {

	public ImageConfigTab() {
		super("Bilder");

		Label lblKontrast = new Label(" Kontrast");
		Label lblGraustufen = new Label(" Graustufen");
		Label lblQualitaet = new Label(" Qualität");
		Label lblZuschnitt = new Label(" Zuschnitt");
		Label lblPixelLinie = new Label(" PixelLinie");
		Label lblGradAnzeige = new Label(" GradAnzeige");

		TextField txtKontrast = new TextField();
		TextField txtGraustufen = new TextField();
		TextField txtQualitaet = new TextField();
		TextField txtZuschnitt = new TextField();
		TextField txtPixelLinie = new TextField();

		final Button btnSenden = new Button("Senden");
		btnSenden.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		btnSenden.setMinSize(120, 40);
		btnSenden.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						System.out.print(txtKontrast.getText());
						System.out.print(txtGraustufen.getText());
						System.out.print(txtQualitaet.getText());
						System.out.print(txtZuschnitt.getText());
						System.out.print(txtPixelLinie.getText());
					}
				});

		GridPane gridPane = new GridPane();

		gridPane.getColumnConstraints().add(new ColumnConstraints(120));
		gridPane.getColumnConstraints().add(new ColumnConstraints(120));

		gridPane.add(lblKontrast, 0, 1);
		gridPane.add(lblGraustufen, 0, 2);
		gridPane.add(lblQualitaet, 0, 3);
		gridPane.add(lblZuschnitt, 0, 4);
		gridPane.add(lblPixelLinie, 0, 5);

		gridPane.add(txtKontrast, 1, 1);
		gridPane.add(txtGraustufen, 1, 2);
		gridPane.add(txtQualitaet, 1, 3);
		gridPane.add(txtZuschnitt, 1, 4);
		gridPane.add(txtPixelLinie, 1, 5);

		gridPane.add(btnSenden, 0, 6);
		gridPane.add(lblGradAnzeige, 1, 6);

		gridPane.setGridLinesVisible(true);

		ImageView ivKorbView = new ImageView();
		ivKorbView.setFitWidth(500);
		ivKorbView.setPreserveRatio(true);
		ivKorbView.setSmooth(true);
		ivKorbView.setCache(true);
		Image iKorbBild = new Image(
				"http://foto.mein-schoener-garten.de/userimages/3499/or/2038793/baum-location-scout-04323805788.jpg");
		ivKorbView.setImage(iKorbBild);

		HBox hbox = new HBox();
		hbox.getChildren().addAll(gridPane, ivKorbView);

		setContent(hbox);
	}

}
