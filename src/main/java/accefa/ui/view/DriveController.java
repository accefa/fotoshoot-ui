package accefa.ui.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import accefa.service.drive.bldc.BldcDriveService;

import com.google.inject.Inject;

public class DriveController {

    @Inject
    private BldcDriveService bldcDriveService;

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

    @FXML
    private void initialize() {
    }

    @FXML
    void btnBldcOnAction(final ActionEvent event) {
        bldcDriveService.start((int) sliderBldc.getValue());
    }

    @FXML
    void btnBldcOffAction(final ActionEvent event) {
        bldcDriveService.stop();
    }

    @FXML
    void btnBldcResetAction(final ActionEvent event) {
        bldcDriveService.reset();
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
