package accefa.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import accefa.service.RaspiService;
import accefa.service.RaspiServiceRest;
import accefa.ui.tabs.EngineConfigTab;
import accefa.ui.view.ImageConfigController;
import accefa.ui.view.PiController;
import accefa.util.FotoShootProperties;

public class FotoShootUi extends Application {

	private TabPane tabPane;

	private List<Tab> tabs;

	private final RaspiService raspiService;

	private final ExecutorService executorService = Executors
			.newSingleThreadExecutor();

	@Override
	public void stop() {
		executorService.shutdownNow();
	}

	public FotoShootUi() {
		final FotoShootProperties properties = new FotoShootProperties();
		properties.load();
		this.raspiService = new RaspiServiceRest(properties.getUrl());
		// this.raspiService = new RaspiServiceMock();
	}

	public void show() {
		launch();
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Fotoshoot");

		tabPane = new TabPane();
		tabs = new ArrayList<Tab>();
		tabs.add(createTabPi());
		tabs.add(createTabImageRecognition());
		tabs.add(new EngineConfigTab());

		configureTabs();

		final Scene scene = new Scene(tabPane, 600, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Tab createTabPi() {
		final Tab tab = new Tab("Pi");
		tab.setContent(createPi());
		return tab;
	}

	private Tab createTabImageRecognition() {
		final Tab tab = new Tab("Bild-Erkennung");
		tab.setContent(createImageConfig());
		return tab;
	}

	private Parent createPi() {
		try {
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ImageConfigController.class
					.getResource("Pi.fxml"));
			final Parent parent = loader.load();
			final PiController controller = loader.getController();
			// controller.setRaspiService(raspiService);
			// controller.setExecutorService(executorService);
			// controller.loadData();
			return parent;
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Parent createImageConfig() {
		try {
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ImageConfigController.class
					.getResource("ImageConfig.fxml"));
			final Parent parent = loader.load();
			final ImageConfigController controller = loader.getController();
			controller.setRaspiService(raspiService);
			controller.setExecutorService(executorService);
			controller.loadData();
			return parent;
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void configureTabs() {
		for (final Tab tab : tabs) {
			tab.setClosable(false);
			tabPane.getTabs().add(tab);
		}
	}

}
