package accefa.ui.model;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogModel {

	private final ObjectProperty<LocalDateTime> time = new SimpleObjectProperty<>(LocalDateTime.now());
	
	private final StringProperty level = new SimpleStringProperty();
	
	private final StringProperty source = new SimpleStringProperty();
	
	private final StringProperty message = new SimpleStringProperty();

    public LogModel(final String message, String level, String source) {
       this.message.set(message);
       this.source.set(source);
       this.level.set(level);
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
	
}
