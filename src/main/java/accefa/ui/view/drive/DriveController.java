package accefa.ui.view.drive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import com.google.inject.Inject;

public class DriveController {

   private final BldcTaskExecutor bldcTaskExecutor;

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
   public DriveController(final BldcTaskExecutor bldcTaskExecutor) {
      this.bldcTaskExecutor = bldcTaskExecutor;
   }

   @FXML
   private void initialize() {
      btnBldcOff.setDisable(true);
   }

   @FXML
   void btnBldcOnAction(final ActionEvent event) {
      bldcTaskExecutor.startBldcDrive((int) sliderBldc.getValue());
      btnBldcOn.setDisable(true);
      btnBldcOff.setDisable(false);
      btnBldcReset.setDisable(true);
   }

   @FXML
   void btnBldcOffAction(final ActionEvent event) {
      bldcTaskExecutor.stopBldcDrive();
      btnBldcOn.setDisable(false);
      btnBldcOff.setDisable(true);
      btnBldcReset.setDisable(false);
   }

   @FXML
   void btnBldcResetAction(final ActionEvent event) {
      bldcTaskExecutor.resetBldcDrive();
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

}
