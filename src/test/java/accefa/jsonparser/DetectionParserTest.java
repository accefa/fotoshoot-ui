package accefa.jsonparser;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import accefa.config.DetectionConfig;

public class DetectionParserTest {

	private static final int LINE_Y_DEFAULT = 23;
	private static final int LINE_H_DEFAULT = 5;
	private static final int GREYSCALE_THRESHOLD_DEFAULT = 50;
	private static final boolean GREYSCALE_DEFAULT = true;
	private static final int QUALITY_DEFAULT = 90;
	private static final int CROP_X_DEFAULT = 12;
	private static final int CONTRAST_DEFAULT = 10;

	private static final String IMAGE_DEFAULT = "http://10.11.4.15/image/basket.jpeg";

	private static final String VALID_JSON_WITHOUT_CONFIG = "{\"testkey\": \"testvalue\"}";
	private static final String INVALID_JSON = "{123}";

	@Test
	public void parseWithConfigKey() {
		DetectionConfig config = DetectionParser
				.parseJSON(getRawJsonWithConfig());
		assertEquals(LINE_Y_DEFAULT, config.getLineY());
		assertEquals(LINE_H_DEFAULT, config.getLineH());
		assertEquals(GREYSCALE_THRESHOLD_DEFAULT,
				config.getGreyscaleThreshold());
		assertEquals(GREYSCALE_DEFAULT, config.isGreyscale());
		assertEquals(QUALITY_DEFAULT, config.getQuality());
		assertEquals(CROP_X_DEFAULT, config.getCropX());
		assertEquals(CONTRAST_DEFAULT, config.getContrast());
	}

	@Test(expected = IllegalArgumentException.class)
	public void parseWithoutConfigKey() {
		DetectionParser.parseJSON(VALID_JSON_WITHOUT_CONFIG);
	}

	@Test(expected = IllegalArgumentException.class)
	public void parseWithInvalidJson() {
		DetectionParser.parseJSON(INVALID_JSON);
	}

	public String getRawJsonWithConfig() {
		JSONObject rootJsonObject = new JSONObject();
		rootJsonObject
				.put(DetectionKeys.CONFIG_KEY.toString(), getConfigJson());
		rootJsonObject.put(DetectionKeys.IMAGE.toString(), IMAGE_DEFAULT);
		return rootJsonObject.toString();
	}

	private JSONObject getConfigJson() {
		JSONObject configJsonObject = new JSONObject();
		configJsonObject.put(DetectionKeys.LINE_Y.toString(),
				String.valueOf(LINE_Y_DEFAULT));
		configJsonObject.put(DetectionKeys.LINE_H.toString(),
				String.valueOf(LINE_H_DEFAULT));
		configJsonObject.put(DetectionKeys.GREYSCALE_THRESHOLD.toString(),
				String.valueOf(GREYSCALE_THRESHOLD_DEFAULT));
		configJsonObject.put(DetectionKeys.GREYSCALE.toString(),
				String.valueOf(GREYSCALE_DEFAULT));
		configJsonObject.put(DetectionKeys.QUALITY.toString(),
				String.valueOf(QUALITY_DEFAULT));
		configJsonObject.put(DetectionKeys.CROP_X.toString(),
				String.valueOf(CROP_X_DEFAULT));
		configJsonObject.put(DetectionKeys.CONTRAST.toString(),
				String.valueOf(CONTRAST_DEFAULT));
		return configJsonObject;
	}

}
