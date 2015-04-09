package accefa.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BldcDriveModel {
	private BooleanProperty on = new SimpleBooleanProperty();
	private BooleanProperty off = new SimpleBooleanProperty();
	private BooleanProperty reset = new SimpleBooleanProperty();
	private IntegerProperty rpm = new SimpleIntegerProperty();

	public boolean isOn() {
		return on.get();
	}

	public void setOn(boolean on) {
		this.on.set(on);
	}

	public BooleanProperty onProperty() {
		return on;
	}

	public boolean isOff() {
		return off.get();
	}

	public void setOff(boolean off) {
		this.off.set(off);
	}

	public BooleanProperty offProperty() {
		return off;
	}

	public boolean isReset() {
		return reset.get();
	}

	public void setReset(boolean reset) {
		this.reset.set(reset);
	}

	public BooleanProperty resetProperty() {
		return reset;
	}

	public int getRpm() {
		return rpm.get();
	}

	public void setRpm(int rpm) {
		if (rpm < 0) {
			rpm = 0;
		}
		this.rpm.set(rpm);
	}

	public IntegerProperty rpmProperty() {
		return rpm;
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("On: " + isOn() + "\n");
		stringBuilder.append("Off: " + isOff() + "\n");
		stringBuilder.append("Reset: " + isReset() + "\n");
		stringBuilder.append("Rpm: " + getRpm() + "\n");
		return stringBuilder.toString();
	}

}
