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
import accefa.service.RaspiService;
import accefa.service.RaspiServiceException;
import accefa.util.ApplicationProperties;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

public class PiController {

   private final RaspiService service;

   private final ExecutorService executor;

   private final ApplicationProperties properties;

   private final EventBus eventBus;

   @FXML
   private Button btnStart;

   @FXML
   private Label lblTime;

   @FXML
   private TextField txtUrl;

   @FXML
   private Button btnSaveUrl;

   private final BooleanProperty processRunningProperty = new SimpleBooleanProperty(false);

   private final StringProperty urlProperty = new SimpleStringProperty();

   private final StringProperty stopWatchTimeMinutesProperty = new SimpleStringProperty("0m");

   private final StringProperty stopWatchTimeSecondsProperty = new SimpleStringProperty("0s");

   private Timeline stopWatchTimeline;

   @Inject
   public PiController(final RaspiService service, final ExecutorService executor,
         final ApplicationProperties properties, final EventBus eventBus) {
      this.service = service;
      this.executor = executor;
      this.properties = properties;
      this.eventBus = eventBus;
   }

   @FXML
   private void initialize() {
      urlProperty.set(properties.getRaspiUrl());
      btnStart.disableProperty().bind(processRunningProperty);
      btnSaveUrl.disableProperty().bind(processRunningProperty);
      lblTime.textProperty().bind(
            Bindings.concat(stopWatchTimeMinutesProperty).concat(" ").concat(stopWatchTimeSecondsProperty));
      txtUrl.textProperty().bindBidirectional(urlProperty);

      txtUrl.disableProperty().bind(processRunningProperty);
      btnStart.disableProperty().bind(processRunningProperty);
      btnSaveUrl.disableProperty().bind(processRunningProperty);

      setUpEventHandling();
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

               stopWatchTimeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(final ActionEvent t) {
                     final Duration duration = ((KeyFrame) t.getSource()).getTime();
                     time = time.add(duration);

                     double timeInSeconds = time.toSeconds();
                     timeInSeconds = timeInSeconds - ((long) ((timeInSeconds / 60)) * 60);
                     stopWatchTimeMinutesProperty.set(String.valueOf((long) time.toMinutes()) + "m");
                     stopWatchTimeSecondsProperty.set(String.format("%.1f", timeInSeconds) + "s");
                  }
               }));
               stopWatchTimeline.setCycleCount(Timeline.INDEFINITE);
               stopWatchTimeline.play();

               final Task<Void> task = new Task<Void>() {
                  @Override
                  protected Void call() throws RaspiServiceException {
                     service.startProcess();
                     return null;
                  }

                  @Override
                  protected void failed() {
                     Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                           eventBus.post(new ErrorEvent("Es ist ein Fehler aufgetreten: "
                                 + exceptionProperty().get().getMessage()));
                           exceptionProperty().get().printStackTrace();
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
                     eventBus.post(new ErrorEvent("Es ist ein Fehler aufgetreten: " + ex.getMessage()));
                     ex.printStackTrace();
                  }
               });
            }
         }
      });

      btnSaveUrl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
         @Override
         public void handle(final MouseEvent e) {
            final String[] schemas = { "http" };
            final UrlValidator urlValidator = new UrlValidator(schemas);
            if (urlValidator.isValid(urlProperty.get())) {
               properties.setRaspiUrl(urlProperty.get());
               txtUrl.setStyle("-fx-base: #ffffff");
               eventBus.post(new InfoEvent("Die URL wurde gespeichert."));
            } else {
               txtUrl.setStyle("-fx-base: #ff0000");
               eventBus.post(new ErrorEvent("Die eingetragenen URL entspricht keinem gültigen URL-Format."));
            }
         }
      });
   }

   @Subscribe
   public void recordProcessStoppedEvent(final ProcessStoppedEvent event) {
      if (stopWatchTimeline != null) {
         stopWatchTimeline.stop();
      }
      processRunningProperty.set(false);
   }

}
