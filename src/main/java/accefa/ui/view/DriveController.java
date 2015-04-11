package accefa.ui.view;

import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import accefa.event.ErrorEvent;
import accefa.service.drive.bldc.BldcDriveService;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class DriveController {

   private final BldcDriveService bldcDriveService;

   private final ExecutorService executor;

   private final EventBus eventBus;

   @FXML
   private Slider sliderBldc;

   @FXML
   private Button btnBldcOn;

   @FXML
   private Button btnBldcOff;

   @FXML
   private Button btnBldcReset;

   @FXML
   private Button btnDcForward;

   @FXML
   private Button btnDcReverse;

   @FXML
   private Button btnDcReset;

   @FXML
   private Slider sliderStp;

   @FXML
   private Button btnStpStart;

   @FXML
   private Button btnStpReset;

   @Inject
   public DriveController(final BldcDriveService bldcDriveService, final ExecutorService executor,
         final EventBus eventBus) {
      this.bldcDriveService = bldcDriveService;
      this.executor = executor;
      this.eventBus = eventBus;
   }

   @FXML
   private void initialize() {
      btnBldcOff.setDisable(true);
   }

   @FXML
   void btnBldcOnAction(final ActionEvent event) {
      startBldcDrive((int) sliderBldc.getValue());
      btnBldcOn.setDisable(true);
      btnBldcOff.setDisable(false);
      btnBldcReset.setDisable(true);
   }

   @FXML
   void btnBldcOffAction(final ActionEvent event) {
      stopBldcDrive();
      btnBldcOn.setDisable(false);
      btnBldcOff.setDisable(true);
      btnBldcReset.setDisable(false);
   }

   @FXML
   void btnBldcResetAction(final ActionEvent event) {
      resetBldcDrive();
   }

   @FXML
   void btnDcForwardAction(final ActionEvent event) {
      System.out.println(event.getSource().toString());

   }

   @FXML
   void btnDcReverseAction(final ActionEvent event) {
      System.out.println(event.getSource().toString());

   }

   @FXML
   void btnDcResetAction(final ActionEvent event) {
      System.out.println(event.getSource().toString());

   }

   @FXML
   void btnStpStartAction(final ActionEvent event) {
      System.out.println(event.getSource().toString());

   }

   @FXML
   void btnStpResetAction(final ActionEvent event) {
      System.out.println(event.getSource().toString());

   }

   private void startBldcDrive(final int rpm) {
      final Task<Void> task = new Task<Void>() {
         @Override
         protected Void call() {
            bldcDriveService.start(rpm);
            return null;
         }

         @Override
         protected void failed() {
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  eventBus.post(new ErrorEvent(exceptionProperty().get().getMessage()));
               }
            });
         }
      };
      executor.execute(task);
   }

   private void stopBldcDrive() {
      final Task<Void> task = new Task<Void>() {
         @Override
         protected Void call() {
            bldcDriveService.stop();
            return null;
         }

         @Override
         protected void failed() {
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  eventBus.post(new ErrorEvent(exceptionProperty().get().getMessage()));
               }
            });
         }
      };
      executor.execute(task);
   }

   private void resetBldcDrive() {
      final Task<Void> task = new Task<Void>() {
         @Override
         protected Void call() {
            bldcDriveService.reset();
            return null;
         }

         @Override
         protected void failed() {
            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  eventBus.post(new ErrorEvent(exceptionProperty().get().getMessage()));
               }
            });
         }
      };
      executor.execute(task);
   }

}
