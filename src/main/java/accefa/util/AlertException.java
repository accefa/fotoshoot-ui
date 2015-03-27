package accefa.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AlertException {

   private final String title = "Fehler";

   private final String headerText = "Es ist ein Fehler aufgetreten.";

   private String contentText = "";

   private final Exception exception;

   public AlertException(final Exception exception) {
      this.exception = exception;
      this.contentText = exception.getMessage();
   }

   public void show() {
      final Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle(title);
      alert.setHeaderText(headerText);
      alert.setContentText(contentText);

      // Create expandable Exception.
      final StringWriter sw = new StringWriter();
      final PrintWriter pw = new PrintWriter(sw);
      exception.printStackTrace(pw);
      final String exceptionText = sw.toString();

      final Label label = new Label("Detailierte Fehlermeldung:");

      final TextArea textArea = new TextArea(exceptionText);
      textArea.setEditable(false);
      textArea.setWrapText(true);

      textArea.setMaxWidth(Double.MAX_VALUE);
      textArea.setMaxHeight(Double.MAX_VALUE);
      GridPane.setVgrow(textArea, Priority.ALWAYS);
      GridPane.setHgrow(textArea, Priority.ALWAYS);

      final GridPane expContent = new GridPane();
      expContent.setMaxWidth(Double.MAX_VALUE);
      expContent.add(label, 0, 0);
      expContent.add(textArea, 0, 1);

      alert.getDialogPane().setExpandableContent(expContent);

      alert.showAndWait();
   }

}
