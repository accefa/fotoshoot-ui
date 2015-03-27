package accefa.ui.models;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

import org.junit.Before;
import org.junit.Test;

public class ImageConfigModelTest {

	private ImageConfigModel model;

	@Before
	public void setUp() {
		model = new ImageConfigModel();
	}

	@Test
	public void testSetGetLineY() {
		model.setLineY(DetectionDefault.LINE_Y);
		assertEquals(DetectionDefault.LINE_Y, model.getLineY());
	}

	@Test
	public void testLineYProperty() {
		assertThat(model.lineYProperty(), instanceOf(IntegerProperty.class));
	}

	@Test
	public void testSetGetLineH() {
		model.setLineH(DetectionDefault.LINE_H);
		assertEquals(DetectionDefault.LINE_H, model.getLineH());
	}

	@Test
	public void testLineHProperty() {
		assertThat(model.lineHProperty(), instanceOf(IntegerProperty.class));
	}

	@Test
	public void testSetGetGreyscaleThreshold() {
		model.setGreyscaleThreshold(DetectionDefault.GREYSCALE_THRESHOLD);
		assertEquals(DetectionDefault.GREYSCALE_THRESHOLD,
				model.getGreyscaleThreshold());
	}

	@Test
	public void testGreyscaleThresholdProperty() {
		assertThat(model.greyscaleThresholdProperty(),
				instanceOf(IntegerProperty.class));
	}

	@Test
	public void testSetGetGreyscale() {
		model.setGreyscale(DetectionDefault.GREYSCALE);
		assertEquals(DetectionDefault.GREYSCALE, model.isGreyscale());
	}

	@Test
	public void testGreyscaleProperty() {
		assertThat(model.greyscaleProperty(), instanceOf(BooleanProperty.class));
	}

	@Test
	public void testSetGetQuality() {
		model.setQuality(DetectionDefault.QUALITY);
		assertEquals(DetectionDefault.QUALITY, model.getQuality());
	}

	@Test
	public void testQualityProperty() {
		assertThat(model.qualityProperty(), instanceOf(IntegerProperty.class));
	}

	@Test
	public void testSetGetCropX() {
		model.setCropX(DetectionDefault.CROP_X);
		assertEquals(DetectionDefault.CROP_X, model.getCropX());
	}

	@Test
	public void testCropXProperty() {
		assertThat(model.cropXProperty(), instanceOf(IntegerProperty.class));
	}

	@Test
	public void testSetGetContrast() {
		model.setContrast(DetectionDefault.CONTRAST);
		assertEquals(DetectionDefault.CONTRAST, model.getContrast());
	}

	@Test
	public void testContrastProperty() {
		assertThat(model.contrastProperty(), instanceOf(IntegerProperty.class));
	}

}
