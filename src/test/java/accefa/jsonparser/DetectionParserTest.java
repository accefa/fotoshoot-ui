package accefa.jsonparser;

import static org.mockito.Mockito.verify;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import accefa.ui.models.DetectionDefault;
import accefa.ui.models.ImageConfigModel;

public class DetectionParserTest {

    private static final String IMAGE_DEFAULT = "http://10.11.4.15/image/basket.jpeg";

    private static final String VALID_JSON_WITHOUT_CONFIG = "{\"testkey\": \"testvalue\"}";
    private static final String INVALID_JSON = "{123}";

    @Mock
    private ImageConfigModel configMock;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	DetectionParser.setImageConfig(configMock);
    }

    @Test
    public void parseWithConfigKey() {
	DetectionParser.parseJSON(getRawJsonWithConfig());      
	verify(configMock).setLineY(DetectionDefault.LINE_Y);
	verify(configMock).setLineH(DetectionDefault.LINE_H);
	verify(configMock).setGreyscaleThreshold(DetectionDefault.GREYSCALE_THRESHOLD);	
	verify(configMock).setGreyscale(DetectionDefault.GREYSCALE);
	verify(configMock).setQuality(DetectionDefault.QUALITY);
	verify(configMock).setCropX(DetectionDefault.CROP_X);
	verify(configMock).setContrast(DetectionDefault.CONTRAST);
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
	rootJsonObject.put(DetectionKeys.CONFIG_KEY, getConfigJson());
	rootJsonObject.put(DetectionKeys.IMAGE, IMAGE_DEFAULT);
	return rootJsonObject.toString();
    }

    private JSONObject getConfigJson() {
	JSONObject configJsonObject = new JSONObject();
	configJsonObject.put(DetectionKeys.LINE_Y,
		String.valueOf(DetectionDefault.LINE_Y));
	configJsonObject.put(DetectionKeys.LINE_H,
		String.valueOf(DetectionDefault.LINE_H));
	configJsonObject.put(DetectionKeys.GREYSCALE_THRESHOLD,
		String.valueOf(DetectionDefault.GREYSCALE_THRESHOLD));
	configJsonObject.put(DetectionKeys.GREYSCALE,
		String.valueOf(DetectionDefault.GREYSCALE));
	configJsonObject.put(DetectionKeys.QUALITY,
		String.valueOf(DetectionDefault.QUALITY));
	configJsonObject.put(DetectionKeys.CROP_X,
		String.valueOf(DetectionDefault.CROP_X));
	configJsonObject.put(DetectionKeys.CONTRAST,
		String.valueOf(DetectionDefault.CONTRAST));
	return configJsonObject;
    }

}
