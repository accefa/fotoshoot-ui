package accefa.ui.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class DriveController {

   @FXML
   private Button btnDcForward;

   @FXML
   private Button btnStpStart;

   @FXML
   private Slider sliderStp;

   @FXML
   private Button btnBldcOn;

   @FXML
   private Button btnBldcOff;

   @FXML
   private Button btnBldcReset;

   @FXML
   private Slider sliderBldc;

   @FXML
   private Button btnStpReset;

   @FXML
   private Button btnDcReverse;

   @FXML
   private Button btnDcReset;

   @FXML
   void btnBldcOnAction(final ActionEvent event) {
      System.out.println(event.getSource().toString());
   }

   @FXML
   void btnBldcOffAction(final ActionEvent event) {
      System.out.println(event.getSource().toString());

   }

   @FXML
   void btnBldcResetAction(final ActionEvent event) {
      System.out.println(event.getSource().toString());

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
