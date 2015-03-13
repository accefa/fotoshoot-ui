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
import javafx.scene.text.TextAlignment;

public class ImageConfigTab extends Tab {

	// Attribute
	Label lblKontrast = new Label(" Kontrast");
	Label lblGraustufen = new Label(" Graustufen");
	Label lblQualitaet = new Label(" Qualität");
	Label lblZuschnittRechts = new Label(" Zuschnitt Rechts");
	Label lblZuschnittLinks = new Label(" Zuschnitt Links");
	Label lblPixelLinie = new Label(" PixelLinie");
	Label lblGradAnzeige = new Label(" GradAnzeige");

	TextField txtKontrast = new TextField();
	TextField txtGraustufen = new TextField();
	TextField txtQualitaet = new TextField();
	TextField txtZuschnittRechts = new TextField();
	TextField txtZuschnittLinks = new TextField();
	TextField txtPixelLinie = new TextField();

	final Button btnSenden = new Button("Senden");
	GridPane gridPane = new GridPane();
	ImageView ivKorbView = new ImageView();
	Image iKorbBild;
	HBox hbox = new HBox();

	// Konstruktor
	public ImageConfigTab() {
		super("Bilder");

		btnSenden.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		btnSenden.setMinSize(120, 40);
		btnSenden.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						System.out.println(txtKontrast.getText());
						System.out.println(txtGraustufen.getText());
						System.out.println(txtQualitaet.getText());
						System.out.println(txtZuschnittRechts.getText());
						System.out.println(txtZuschnittLinks.getText());
						System.out.println(txtPixelLinie.getText());
					}
				});

		gridPane.getColumnConstraints().add(new ColumnConstraints(120));
		gridPane.getColumnConstraints().add(new ColumnConstraints(120));

		gridPane.add(lblKontrast, 0, 1);
		gridPane.add(lblGraustufen, 0, 2);
		gridPane.add(lblQualitaet, 0, 3);
		gridPane.add(lblZuschnittRechts, 0, 4);
		gridPane.add(lblZuschnittLinks, 0, 5);
		gridPane.add(lblPixelLinie, 0, 6);
		gridPane.add(btnSenden, 0, 7);

		gridPane.add(txtKontrast, 1, 1);
		gridPane.add(txtGraustufen, 1, 2);
		gridPane.add(txtQualitaet, 1, 3);
		gridPane.add(txtZuschnittRechts, 1, 4);
		gridPane.add(txtZuschnittLinks, 1, 5);
		gridPane.add(txtPixelLinie, 1, 6);
		gridPane.add(lblGradAnzeige, 1, 7);

		gridPane.setGridLinesVisible(true);

		ivKorbView.setFitWidth(500);
		ivKorbView.setPreserveRatio(true);
		ivKorbView.setSmooth(true);
		ivKorbView.setCache(true);

		loadImage();
		ivKorbView.setImage(iKorbBild);

		hbox.getChildren().addAll(gridPane, ivKorbView);
		setContent(hbox);

	}

	public final void loadImage() {
		iKorbBild = new Image(
				"http://foto.mein-schoener-garten.de/userimages/3499/or/2038793/baum-location-scout-04323805788.jpg");
		if (iKorbBild.isError()) {
			System.out.println("error");
			iKorbBild = new Image(
					"file:///C:/Users/Adrian/git/fotoshoot-ui/src/main/java/accefa/ui/tabs/test.png");
		} else {
			System.out.println("no error");
		}
	}

	public final void setGradAnzeige(int value) {
		lblGradAnzeige.setText(Integer.toString(value));

	}
}
