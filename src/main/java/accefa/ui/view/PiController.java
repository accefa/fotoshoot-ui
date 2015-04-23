package accefa.ui.view;

import java.util.concurrent.ExecutorService;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import org.apache.commons.validator.routines.UrlValidator;

import accefa.event.ErrorEvent;
import accefa.event.InfoEvent;
import accefa.event.ProcessStartedEvent;
import accefa.event.ProcessStoppedEvent;
import accefa.service.image.ImageService;
import accefa.service.image.ImageServiceException;
import accefa.util.ApplicationPreferences;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

public class PiController {

    private final ImageService service;

    private final ExecutorService executor;

    private final ApplicationPreferences properties;

    private final EventBus eventBus;

    @FXML
    private Button btnStart;

    @FXML
    private Label lblTime;

    @FXML
    private TextField txtRaspiUrl;

    @FXML
    private Button btnSaveRaspiUrl;

    private final BooleanProperty processRunningProperty = new SimpleBooleanProperty(false);

    private final StringProperty raspiUrlProperty = new SimpleStringProperty();

    private final StringProperty stopWatchTimeMinutesProperty = new SimpleStringProperty("0m");

    private final StringProperty stopWatchTimeSecondsProperty = new SimpleStringProperty("0s");

    private Timeline stopWatchTimeline;

    @Inject
    public PiController(final ImageService service, final ExecutorService executor,
            final ApplicationPreferences properties, final EventBus eventBus) {
        this.service = service;
        this.executor = executor;
        this.properties = properties;
        this.eventBus = eventBus;
    }

    @FXML
    private void initialize() {
        bindDisableProperties();
        bindUrlProperties();

        lblTime.textProperty().bind(
                Bindings.concat(stopWatchTimeMinutesProperty).concat(" ")
                        .concat(stopWatchTimeSecondsProperty));

        setUpEventHandling();
    }

    private void bindDisableProperties() {
        btnStart.disableProperty().bind(processRunningProperty);
        btnSaveRaspiUrl.disableProperty().bind(processRunningProperty);
        btnStart.disableProperty().bind(processRunningProperty);
        btnSaveRaspiUrl.disableProperty().bind(processRunningProperty);
        txtRaspiUrl.disableProperty().bind(processRunningProperty);
    }

    private void bindUrlProperties() {
        raspiUrlProperty.set(properties.getRaspiUrl());
        txtRaspiUrl.textProperty().bindBidirectional(raspiUrlProperty);
    }

    private void setUpEventHandling() {
        btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            private Duration time = Duration.ZERO;

            @Override
            public void handle(final MouseEvent e) {
                try {
                    processRunningProperty.set(true);
                    time = Duration.ZERO;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            eventBus.post(new ProcessStartedEvent());
                        }
                    });

                    stopWatchTimeline = new Timeline(new KeyFrame(Duration.millis(100),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(final ActionEvent t) {
                                    final Duration duration = ((KeyFrame) t.getSource()).getTime();
                                    time = time.add(duration);

                                    double timeInSeconds = time.toSeconds();
                                    timeInSeconds = timeInSeconds
                                            - ((long) ((timeInSeconds / 60)) * 60);
                                    stopWatchTimeMinutesProperty.set(String.valueOf((long) time
                                            .toMinutes()) + "m");
                                    stopWatchTimeSecondsProperty.set(String.format("%.1f",
                                            timeInSeconds) + "s");
                                }
                            }));
                    stopWatchTimeline.setCycleCount(Timeline.INDEFINITE);
                    stopWatchTimeline.play();

                    final Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws ImageServiceException {
                            service.startProcess();
                            return null;
                        }

                        @Override
                        protected void failed() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    eventBus.post(new ErrorEvent(exceptionProperty().get()
                                            .getMessage()));
                                    exceptionProperty().get().printStackTrace();
                                }
                            });
                        }
                        
                        @Override
                        protected void succeeded() {
                        	Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    eventBus.post(new ProcessStoppedEvent());
                                }
                            });
                        }
                    };
                    executor.execute(task);
                } catch (final Exception ex) {
                    // TODO Reset UI in this case
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            eventBus.post(new ErrorEvent(ex.getMessage()));
                            ex.printStackTrace();
                            eventBus.post(new ProcessStoppedEvent());
                        }
                    });
                }
            }
        });
    }

    @FXML
    void saveRaspiUrl(final ActionEvent event) {
        final String raspiUrl = raspiUrlProperty.get();

        if (isUrlValid(raspiUrl)) {
            properties.setRaspiUrl(raspiUrl);
            saveUrlSuccessful(txtRaspiUrl);
        } else {
            saveUrlError(txtRaspiUrl);
        }
    }

    private boolean isUrlValid(final String url) {
        final String[] schemas = { "http" };
        return new UrlValidator(schemas, UrlValidator.ALLOW_LOCAL_URLS).isValid(url);
    }

    private void saveUrlSuccessful(final TextField textField) {
        textField.setStyle("-fx-base: #ffffff");
        eventBus.post(new InfoEvent("Die URL wurde gespeichert."));
    }

    private void saveUrlError(final TextField textField) {
        textField.setStyle("-fx-base: #ff0000");
        eventBus.post(new ErrorEvent("Die eingetragenen URL entspricht keinem gültigen URL-Format."));
    }

    @Subscribe
    public void recordProcessStoppedEvent(final ProcessStoppedEvent event) {
        if (stopWatchTimeline != null) {
            stopWatchTimeline.stop();
        }
        processRunningProperty.set(false);
    }

}
