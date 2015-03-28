package accefa.ui.view;

import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import accefa.service.RaspiService;
import accefa.service.RaspiServiceException;
import accefa.ui.models.ImageConfigModel;
import accefa.util.AlertException;

public class ImageConfigController {

	private RaspiService raspiService;

	private ImageConfigModel imageConfigModel;

	private ExecutorService executorService;

	@FXML
	private TitledPane titledPaneImage;

	@FXML
	private TitledPane titledPaneConfiguration;

	@FXML
	private Label lblKontrast;

	@FXML
	private Slider sliderContrast;

	@FXML
	private Label lblGreyscale;

	@FXML
	private CheckBox toogleGreyscale;

	@FXML
	private Label lblGreyscaleThreshold;

	@FXML
	private Slider sliderGreyscaleThreshold;

	@FXML
	private Label lblQuality;

	@FXML
	private Slider sliderQuality;

	@FXML
	private Label lblCropX;

	@FXML
	private Slider sliderCropX;

	@FXML
	private Label lblLineY;

	@FXML
	private Slider sliderLineY;

	@FXML
	private Label lblLineH;

	@FXML
	private Slider sliderLineH;

	@FXML
	private Button btnReloadImage;

	@FXML
	private ImageView imageView;

	public void setRaspiService(final RaspiService service) {
		this.raspiService = service;
	}

	public void setExecutorService(final ExecutorService service) {
		this.executorService = service;
	}

	public void loadData() {
		imageConfigModel = new ImageConfigModel();
		bindProperties(imageConfigModel);
		loadImageConfigModel();
		imageView.setImage(getDefaultImage());
	}

	private void loadImage(final String url) {
		Image image = new Image(url);
		if (image.isError()) {
			image = getDefaultImage();
		}
		imageView.setImage(image);
	}

	private Image getDefaultImage() {
		return new Image(this.getClass().getClassLoader()
				.getResourceAsStream("404.jpg"));
	}

	/**
	 * Lädt Daten von Server.
	 */
	private void loadImageConfigModel() {
		if (raspiService == null || executorService == null) {
			return;
		}

		final Task<ImageConfigModel> task = new Task<ImageConfigModel>() {
			@Override
			protected ImageConfigModel call() throws RaspiServiceException {
				return raspiService.readImageConfigModel();
			}

			@Override
			protected void succeeded() {
				super.failed();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						final ImageConfigModel model = valueProperty().get();
						if (valueProperty().get() != null) {
							imageConfigModel = model;
							bindProperties(model);
							loadImage(raspiService.getImageUrl());
						}
					}
				});
			}
		};
		executorService.execute(task);
	}

	private void bindProperties(final ImageConfigModel model) {
		lblKontrast.textProperty().bind(
				Bindings.concat("Kontrast (")
						.concat(model.contrastProperty().asString())
						.concat(")"));
		sliderContrast.valueProperty().bindBidirectional(
				model.contrastProperty());

		toogleGreyscale.selectedProperty().bindBidirectional(
				model.greyscaleProperty());

		lblGreyscaleThreshold.textProperty().bind(
				Bindings.concat("Graustufen Schwellwert (")
						.concat(model.greyscaleThresholdProperty().asString())
						.concat(")"));
		sliderGreyscaleThreshold.valueProperty().bindBidirectional(
				model.greyscaleThresholdProperty());

		lblQuality
				.textProperty()
				.bind(Bindings.concat("Qualität (")
						.concat(model.qualityProperty().asString()).concat(")"));
		sliderQuality.valueProperty()
				.bindBidirectional(model.qualityProperty());

		lblCropX.textProperty().bind(
				Bindings.concat("Zuschnitt X (")
						.concat(model.cropXProperty().asString()).concat(")"));
		sliderCropX.valueProperty().bindBidirectional(model.cropXProperty());

		lblLineY.textProperty().bind(
				Bindings.concat("Linie Y (")
						.concat(model.lineYProperty().asString()).concat(")"));
		sliderLineY.valueProperty().bindBidirectional(model.lineYProperty());

		lblLineH.textProperty().bind(
				Bindings.concat("Linie H (")
						.concat(model.lineHProperty().asString()).concat(")"));
		sliderLineH.valueProperty().bindBidirectional(model.lineHProperty());
	}

	@FXML
	private void initialize() {
		btnReloadImage.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(final MouseEvent event) {
				saveImageConfigModel();
				loadImageConfigModel();
			}
		});
	}

	/**
	 * Sendet die Daten an den Server.
	 */
	private void saveImageConfigModel() {
		if (raspiService == null || executorService == null) {
			return;
		}
		titledPaneConfiguration.setDisable(true);
		final Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws RaspiServiceException {
				raspiService.saveImageConfigModel(imageConfigModel);
				return null;
			}

			@Override
			protected void failed() {
				super.failed();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						new AlertException(exceptionProperty().get()).show();
						titledPaneConfiguration.setDisable(false);
					}
				});
			}

			@Override
			protected void succeeded() {
				super.failed();
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						titledPaneConfiguration.setDisable(false);
					}
				});
			}
		};
		executorService.execute(task);
	}

}
