package accefa.ui.model;


public class BldcDriveModel {
    private int rpm;

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        if (rpm < 0) {
            rpm = 0;
        }
        this.rpm = rpm;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Rpm: " + getRpm() + "\n");
        return stringBuilder.toString();
    }

}
