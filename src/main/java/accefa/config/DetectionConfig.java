package accefa.config;

public class DetectionConfig {
	private int lineY;
	private int lineH;
	private int greyscaleThreshold;
	private boolean greyscale;
	private int quality;
	private int cropX;
	private int contrast;

	public DetectionConfig() {
		super();
	}

	public int getLineY() {
		return lineY;
	}

	public void setLineY(int lineY) {
		this.lineY = lineY;
	}

	public int getLineH() {
		return lineH;
	}

	public void setLineH(int lineH) {
		this.lineH = lineH;
	}

	public int getGreyscaleThreshold() {
		return greyscaleThreshold;
	}

	public void setGreyscaleThreshold(int greyscaleThreshold) {
		this.greyscaleThreshold = greyscaleThreshold;
	}

	public boolean isGreyscale() {
		return greyscale;
	}

	public void setGreyscale(boolean greyscale) {
		this.greyscale = greyscale;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getCropX() {
		return cropX;
	}

	public void setCropX(int cropX) {
		this.cropX = cropX;
	}

	public int getContrast() {
		return contrast;
	}

	public void setContrast(int contrast) {
		this.contrast = contrast;
	}

}
