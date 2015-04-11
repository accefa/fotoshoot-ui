package accefa.ui.view.drive;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import com.google.inject.Inject;

public class DriveController {

    private final BldcTaskExecutor bldcTaskExecutor;

    private final DcTaskExecutor dcTaskExecutor;

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
    private Button btnDcBackward;

    @FXML
    private Button btnDcReset;

    @FXML
    private Slider sliderStp;

    @FXML
    private Button btnStpStart;

    @FXML
    private Button btnStpReset;

    @Inject
    public DriveController(final BldcTaskExecutor bldcTaskExecutor,
            final DcTaskExecutor dcTaskExecutor) {
        this.bldcTaskExecutor = bldcTaskExecutor;
        this.dcTaskExecutor = dcTaskExecutor;
    }

    @FXML
    private void initialize() {
        btnBldcOff.setDisable(true);
    }

    @FXML
    void btnBldcOnAction(final ActionEvent event) {
        bldcTaskExecutor.start((int) sliderBldc.getValue());
        btnBldcOn.setDisable(true);
        btnBldcOff.setDisable(false);
        btnBldcReset.setDisable(true);
    }

    @FXML
    void btnBldcOffAction(final ActionEvent event) {
        bldcTaskExecutor.stop();
        btnBldcOn.setDisable(false);
        btnBldcOff.setDisable(true);
        btnBldcReset.setDisable(false);
    }

    @FXML
    void btnBldcResetAction(final ActionEvent event) {
        bldcTaskExecutor.reset();
    }

    @FXML
    void btnDcForwardAction(final ActionEvent event) {
        dcTaskExecutor.forward();
    }

    @FXML
    void btnDcBackwardAction(final ActionEvent event) {
        dcTaskExecutor.backward();
    }

    @FXML
    void btnDcResetAction(final ActionEvent event) {
        dcTaskExecutor.reset();
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
