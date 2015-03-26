package accefa.ui.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ImageConfigView {

   private final Node node;

   private GridPane gridPane;

   private Label lblKontrast;
   private Slider sliderContrast;

   private Label lblGreyscale;
   private ToggleButton toogleGreyscale;

   private Label lblGreyscaleThreshold;
   private Slider sliderGreyscaleThreshold;

   private Label lblQuality;
   private Slider sliderQuality;

   private Label lblCropX;
   private Slider sliderCropX;

   private Label lblLineY;
   private Slider sliderLineY;

   private Label lblLineH;
   private Slider sliderLineH;

   private Button btnReloadImage;

   ImageView korbView = new ImageView();
   Image korbBild;

   public ImageConfigView() {
      createUIComponents();
      setUpGridPane();

      korbView.setFitWidth(500);
      korbView.setPreserveRatio(true);
      korbView.setSmooth(true);
      korbView.setCache(true);

      loadImage();
      korbView.setImage(korbBild);

      final HBox hbox = new HBox();
      hbox.getChildren().addAll(gridPane, korbView);
      this.node = hbox;
   }

   public Node getNode() {
      return node;
   }

   public void addEventHandlerReloadImage(final EventHandler<MouseEvent> eventHandler) {
      btnReloadImage.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
   }

   public DoubleProperty contrastProperty() {
      return sliderContrast.valueProperty();
   }

   public BooleanProperty greyscaleProperty() {
      return toogleGreyscale.selectedProperty();
   }

   public DoubleProperty greyscaleThresholdProperty() {
      return sliderGreyscaleThreshold.valueProperty();
   }

   public DoubleProperty lineYProperty() {
      return sliderLineY.valueProperty();
   }

   public DoubleProperty lineHProeprty() {
      return sliderLineH.valueProperty();
   }

   public DoubleProperty qualityProperty() {
      return sliderQuality.valueProperty();
   }

   public DoubleProperty cropXProperty() {
      return sliderCropX.valueProperty();
   }

   public final void loadImage() {
      korbBild = new Image("http://foto"
            + ".mein-schoener-garten.de/userimages/3499/or/2038793/baum-location-scout-04323805788.jpg");
      if (korbBild.isError()) {
         korbBild = new Image(this.getClass().getClassLoader().getResourceAsStream("404.jpg"));
      }
   }

   private void createUIComponents() {
      lblKontrast = createLabel("Kontrast");
      sliderContrast = createSlider(-100, 100);

      lblGreyscale = createLabel("Graustufen");
      toogleGreyscale = createToggleButton();

      lblGreyscaleThreshold = createLabel("Graustufen-Schwellwert");
      sliderGreyscaleThreshold = createSlider(0, 255);

      lblQuality = createLabel("Qualität");
      sliderQuality = createSlider(0, 100);

      lblCropX = createLabel("Zuschnitt Links/Rechts");
      sliderCropX = createSlider(0, 300);

      lblLineY = createLabel("Linie Y");
      sliderLineY = createSlider(0, 100);

      lblLineH = createLabel("Linie H");
      sliderLineH = createSlider(0, 50);

      btnReloadImage = createButton("Speichern");
   }

   private void setUpGridPane() {
      gridPane = new GridPane();

      gridPane.getColumnConstraints().add(new ColumnConstraints(120));
      gridPane.getColumnConstraints().add(new ColumnConstraints(120));

      gridPane.add(lblKontrast, 0, 1);
      gridPane.add(sliderContrast, 1, 1);

      gridPane.add(lblGreyscale, 0, 2);
      gridPane.add(toogleGreyscale, 1, 2);

      gridPane.add(lblGreyscaleThreshold, 0, 3);
      gridPane.add(sliderGreyscaleThreshold, 1, 3);

      gridPane.add(lblQuality, 0, 4);
      gridPane.add(sliderQuality, 1, 4);

      gridPane.add(lblCropX, 0, 5);
      gridPane.add(sliderCropX, 1, 5);

      gridPane.add(lblLineY, 0, 6);
      gridPane.add(sliderLineY, 1, 6);

      gridPane.add(lblLineH, 0, 7);
      gridPane.add(sliderLineH, 1, 7);

      gridPane.add(btnReloadImage, 1, 8);

      gridPane.setGridLinesVisible(true);
   }

   private Button createButton(final String text) {
      final Button button = new Button(text);
      button.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
      button.setMinSize(120, 40);
      return button;
   }

   private Slider createSlider(final double min, final double max) {
      final Slider slider = new Slider();
      slider.setMin(min);
      slider.setMax(max);
      slider.setShowTickMarks(true);
      slider.setShowTickLabels(true);
      return slider;
   }

   private Label createLabel(final String text) {
      return new Label(text);
   }

   private ToggleButton createToggleButton() {
      return new ToggleButton();
   }

}
