package accefa.jsonparser;

import org.json.JSONException;
import org.json.JSONObject;

import accefa.ui.models.ImageConfigModel;

public class DetectionParser {

    private static JSONObject parsedJson;

    private static ImageConfigModel imageConfig = new ImageConfigModel();

    private DetectionParser() {
    };

    public static ImageConfigModel parseJSON(String rawJson) {
	try {
	    parsedJson = new JSONObject(rawJson);

	    if (!hasConfigKey()) {
		throw new IllegalArgumentException("Kein Config Key gefunden");
	    }

	    JSONObject config = parsedJson
		    .optJSONObject(DetectionKeys.CONFIG_KEY);

	    return getDetectionConfig(config);
	} catch (JSONException e) {
	    throw new IllegalArgumentException(e);
	}
    }

    private static boolean hasConfigKey() {
	return parsedJson.has(DetectionKeys.CONFIG_KEY);
    }

    private static ImageConfigModel getDetectionConfig(JSONObject config) {
	imageConfig.setContrast(config.getInt(DetectionKeys.CONTRAST));
	imageConfig.setCropX(config.getInt(DetectionKeys.CROP_X));
	imageConfig.setGreyscale(config.getBoolean(DetectionKeys.GREYSCALE));
	imageConfig.setGreyscaleThreshold(config
		.getInt(DetectionKeys.GREYSCALE_THRESHOLD));
	imageConfig.setLineH(config.getInt(DetectionKeys.LINE_H));
	imageConfig.setLineY(config.getInt(DetectionKeys.LINE_Y));
	imageConfig.setQuality(config.getInt(DetectionKeys.QUALITY));
	return imageConfig;
    }

    public static void setImageConfig(ImageConfigModel imageConfig) {
        DetectionParser.imageConfig = imageConfig;
    }

}
