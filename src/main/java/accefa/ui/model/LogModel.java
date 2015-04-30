package accefa.ui.model;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.google.common.base.Objects;

public class LogModel {

	private final ObjectProperty<LocalDateTime> time = new SimpleObjectProperty<>(
			LocalDateTime.now());

	private final StringProperty level = new SimpleStringProperty();

	private final StringProperty source = new SimpleStringProperty();

	private final StringProperty message = new SimpleStringProperty();

	public LogModel(final String message, String level, String source) {
		this.message.set(message);
		this.source.set(source);
		this.level.set(level);
	}

	public void setTime(LocalDateTime localDateTime) {
		this.time.set(localDateTime);
	}

	public StringProperty messageProperty() {
		return message;
	}

	public StringProperty sourceProperty() {
		return source;
	}

	public StringProperty levelProperty() {
		return level;
	}

	public ObjectProperty<LocalDateTime> timePropertY() {
		return time;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(time.get(), level.get(), source.get(),
				message.get());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final LogModel other = (LogModel) obj;

		return Objects.equal(time.get(), other.time.get())
				&& Objects.equal(level.get(), other.level.get())
				&& Objects.equal(source.get(), other.source.get())
				&& Objects.equal(message.get(), other.message.get());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(time.get())
				.addValue(level.get()).addValue(source.get())
				.addValue(message.get()).toString();
	}

}
