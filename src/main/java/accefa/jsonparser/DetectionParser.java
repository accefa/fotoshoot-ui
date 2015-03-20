package accefa.jsonparser;

import org.json.JSONException;
import org.json.JSONObject;

import accefa.config.DetectionConfig;

public class DetectionParser {

	private static JSONObject parsedJson;

	private DetectionParser() {
	};

	public static DetectionConfig parseJSON(String rawJson) {
		try {
			parsedJson = new JSONObject(rawJson);
			
			if (!hasConfigKey()) {
				throw new IllegalArgumentException("Kein Config Key gefunden");
			}

			JSONObject config = parsedJson
					.optJSONObject(DetectionKeys.CONFIG_KEY.toString());

			return getDetectionConfig(config);
		} catch (JSONException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	private static boolean hasConfigKey() {
		return parsedJson.has(DetectionKeys.CONFIG_KEY.toString());
	}
	
	private static DetectionConfig getDetectionConfig(JSONObject config) {
		DetectionConfig detectionConfig = new DetectionConfig();
		detectionConfig.setContrast(config.getInt(DetectionKeys.CONTRAST
				.toString()));
		detectionConfig.setCropX(config.getInt(DetectionKeys.CROP_X
				.toString()));
		detectionConfig.setGreyscale(config
				.getBoolean(DetectionKeys.GREYSCALE.toString()));
		detectionConfig.setGreyscaleThreshold(config
				.getInt(DetectionKeys.GREYSCALE_THRESHOLD.toString()));
		detectionConfig.setLineH(config.getInt(DetectionKeys.LINE_H
				.toString()));
		detectionConfig.setLineY(config.getInt(DetectionKeys.LINE_Y
				.toString()));
		detectionConfig.setQuality(config.getInt(DetectionKeys.QUALITY
				.toString()));
		return detectionConfig;
	}

}
