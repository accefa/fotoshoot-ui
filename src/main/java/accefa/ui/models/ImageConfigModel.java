package accefa.ui.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ImageConfigModel {
	private IntegerProperty lineY = new SimpleIntegerProperty();
	private IntegerProperty lineH = new SimpleIntegerProperty();
	private IntegerProperty greyscaleThreshold = new SimpleIntegerProperty();
	private BooleanProperty greyscale = new SimpleBooleanProperty();
	private IntegerProperty quality = new SimpleIntegerProperty();
	private IntegerProperty cropX = new SimpleIntegerProperty();
	private IntegerProperty contrast = new SimpleIntegerProperty();

	public int getLineY() {
		return lineY.get();
	}

	public void setLineY(int lineY) {
		this.lineY.set(lineY);
	}

	public IntegerProperty lineYProperty() {
		return lineY;
	}

	public int getLineH() {
		return lineH.get();
	}

	public void setLineH(int lineH) {
		this.lineH.set(lineH);
	}

	public IntegerProperty lineHProperty() {
		return lineH;
	}

	public int getGreyscaleThreshold() {
		return greyscaleThreshold.get();
	}

	public void setGreyscaleThreshold(int greyscaleThreshold) {
		this.greyscaleThreshold.set(greyscaleThreshold);
	}

	public IntegerProperty greyscaleThresholdProperty() {
		return greyscaleThreshold;
	}

	public boolean isGreyscale() {
		return greyscale.get();
	}

	public void setGreyscale(boolean greyscale) {
		this.greyscale.set(greyscale);
	}

	public BooleanProperty greyscaleProperty() {
		return greyscale;
	}

	public int getQuality() {
		return quality.get();
	}

	public void setQuality(int quality) {
		this.quality.set(quality);
	}

	public IntegerProperty qualityProperty() {
		return quality;
	}

	public int getCropX() {
		return cropX.get();
	}

	public void setCropX(int cropX) {
		this.cropX.set(cropX);
	}

	public IntegerProperty cropXProperty() {
		return cropX;
	}

	public int getContrast() {
		return contrast.get();
	}

	public void setContrast(int contrast) {
		this.contrast.set(contrast);
	}

	public IntegerProperty contrastProperty() {
		return contrast;
	}

}
