package accefa.ui.model;


public class StpDriveModel {
    private int steps;

    public int getSteps() {
        return steps;
    }

    public void setSteps(final int steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Steps: " + getSteps() + "\n");
        return stringBuilder.toString();
    }
}
