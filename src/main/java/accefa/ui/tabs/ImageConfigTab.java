package accefa.ui.tabs;

import javafx.event.EventHandler;
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

	final Button senden = new Button("Senden");
	GridPane gridPane = new GridPane();
	ImageView korbView = new ImageView();
	Image korbBild;
	HBox hbox = new HBox();

	// Konstruktor
	public ImageConfigTab() {
		super("Bilder");

		senden.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		senden.setMinSize(120, 40);
		senden.addEventHandler(MouseEvent.MOUSE_CLICKED,
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
		gridPane.add(senden, 0, 7);

		gridPane.add(txtKontrast, 1, 1);
		gridPane.add(txtGraustufen, 1, 2);
		gridPane.add(txtQualitaet, 1, 3);
		gridPane.add(txtZuschnittRechts, 1, 4);
		gridPane.add(txtZuschnittLinks, 1, 5);
		gridPane.add(txtPixelLinie, 1, 6);
		gridPane.add(lblGradAnzeige, 1, 7);

		gridPane.setGridLinesVisible(true);

		korbView.setFitWidth(500);
		korbView.setPreserveRatio(true);
		korbView.setSmooth(true);
		korbView.setCache(true);

		loadImage();
		korbView.setImage(korbBild);

		hbox.getChildren().addAll(gridPane, korbView);
		setContent(hbox);
	}

	public final void loadImage() {
		korbBild = new Image(
				"http://foto"
				+ ".mein-schoener-garten.de/userimages/3499/or/2038793/baum-location-scout-04323805788.jpg");
		if (korbBild.isError()) {
			korbBild = new Image(this.getClass().getClassLoader()
					.getResourceAsStream("404.jpg"));
		}
	}

	public final void setGradAnzeige(int value) {
		lblGradAnzeige.setText(Integer.toString(value));

	}
}
