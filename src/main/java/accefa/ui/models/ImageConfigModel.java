package accefa.ui.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ImageConfigModel {
	private IntegerProperty lineY = new SimpleIntegerProperty();
	private IntegerProperty lineH = new SimpleIntegerProperty();
	private IntegerProperty greyscaleThreshold = new SimpleIntegerProperty();
	private BooleanProperty greyscale = new SimpleBooleanProperty();
	private IntegerProperty quality = new SimpleIntegerProperty();
	private IntegerProperty cropX = new SimpleIntegerProperty();
	private IntegerProperty contrast = new SimpleIntegerProperty();
	private StringProperty image = new SimpleStringProperty();

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
	
	public String getImage() {
		return image.get();
	}
	
	public void setImage(String image) {
		this.image.set(image);
	}
	
	public StringProperty imageProperty() {
		return image;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Line Y: " + getLineY() + "\n");
		stringBuilder.append("Line H: " + getLineH() + "\n");
		stringBuilder.append("Greyscale Threshold: " + getGreyscaleThreshold()
				+ "\n");
		stringBuilder.append("Greyscale: " + isGreyscale() + "\n");
		stringBuilder.append("Quality: " + getQuality() + "\n");
		stringBuilder.append("Crop X: " + getCropX() + "\n");
		stringBuilder.append("Contrast: " + getContrast() + "\n");
		stringBuilder.append("Image: " + getImage() + "\n");
		return stringBuilder.toString();
	}

}
