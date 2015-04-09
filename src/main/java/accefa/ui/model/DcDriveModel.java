package accefa.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class DcDriveModel {
	private BooleanProperty forward = new SimpleBooleanProperty();
	private BooleanProperty reverse = new SimpleBooleanProperty();
	private BooleanProperty reset = new SimpleBooleanProperty();

	public boolean isForward() {
		return forward.get();
	}

	public void setForward(boolean forward) {
		this.forward.set(forward);
	}

	public BooleanProperty forwardProperty() {
		return forward;
	}

	public boolean isReverse() {
		return reverse.get();
	}

	public void setReverse(boolean reverse) {
		this.reverse.set(reverse);
	}

	public BooleanProperty reverseProperty() {
		return reverse;
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

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Forward: " + isForward() + "\n");
		stringBuilder.append("Reverse: " + isReverse() + "\n");
		stringBuilder.append("Reset: " + isReset() + "\n");
		return stringBuilder.toString();
	}

}
