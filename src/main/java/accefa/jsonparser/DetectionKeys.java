package accefa.jsonparser;

public enum DetectionKeys {
	CONFIG_KEY("config"),
	LINE_Y("line_y"),
	LINE_H("line_h"),
	GREYSCALE_THRESHOLD("greyscale_threshold"),
	GREYSCALE("greyscale"),
	QUALITY("quality"),
	CROP_X("crop_x"),
	CONTRAST("contrast"),
	IMAGE("image");
	

	private final String key;
	
	private DetectionKeys(String key) {
		this.key = key;
	}
	 
	@Override
    public String toString() {
        return key;
    }
}
