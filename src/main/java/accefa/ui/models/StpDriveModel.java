package accefa.ui.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class StpDriveModel {
	private BooleanProperty reset = new SimpleBooleanProperty();
	private IntegerProperty steps = new SimpleIntegerProperty();

	public boolean isReset() {
		return reset.get();
	}

	public void setReset(boolean reset) {
		this.reset.set(reset);
	}

	public BooleanProperty resetProperty() {
		return reset;
	}

	public int getSteps() {
		return steps.get();
	}

	public void setSteps(int steps) {
		this.steps.set(steps);
	}

	public IntegerProperty stepsProperty() {
		return steps;
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Reset: " + isReset() + "\n");
		stringBuilder.append("Steps: " + getSteps() + "\n");
		return stringBuilder.toString();
	}
}
