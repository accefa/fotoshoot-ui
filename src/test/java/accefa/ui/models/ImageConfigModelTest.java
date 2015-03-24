package accefa.ui.models;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
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
	public void setGetLineY() {
	    model.setLineY(DetectionDefault.LINE_Y);
	    assertEquals(DetectionDefault.LINE_Y, model.getLineY());
	}
	
	@Test
	public void lineYProperty() {
		assertThat(model.lineYProperty(), instanceOf(IntegerProperty.class));
	}
	
	@Test
	public void setGetLineH() {
	    model.setLineH(DetectionDefault.LINE_H);
	    assertEquals(DetectionDefault.LINE_H, model.getLineH());
	}
	
	@Test
	public void lineHProperty() {
		assertThat(model.lineHProperty(), instanceOf(IntegerProperty.class));
	}
	
	@Test
	public void setGetGreyscaleThreshold() {
	    model.setGreyscaleThreshold(DetectionDefault.GREYSCALE_THRESHOLD);
	    assertEquals(DetectionDefault.GREYSCALE_THRESHOLD, model.getGreyscaleThreshold());
	}
	
	@Test
	public void greyscaleThresholdProperty() {
		assertThat(model.greyscaleThresholdProperty(), instanceOf(IntegerProperty.class));
	}
	
	@Test
	public void setGetGreyscale() {
	    model.setGreyscale(DetectionDefault.GREYSCALE);
	    assertEquals(DetectionDefault.GREYSCALE, model.isGreyscale());
	}
	
	@Test
	public void greyscaleProperty() {
		assertThat(model.greyscaleProperty(), instanceOf(BooleanProperty.class));
	}
	
	@Test
	public void setGetQuality() {
	    model.setQuality(DetectionDefault.QUALITY);
	    assertEquals(DetectionDefault.QUALITY, model.getQuality());
	}
	
	@Test
	public void qualityProperty() {
		assertThat(model.qualityProperty(), instanceOf(IntegerProperty.class));
	}
	
	@Test
	public void setGetCropX() {
	    model.setCropX(DetectionDefault.CROP_X);
	    assertEquals(DetectionDefault.CROP_X, model.getCropX());
	}
	
	@Test
	public void cropXProperty() {
		assertThat(model.cropXProperty(), instanceOf(IntegerProperty.class));
	}
	
	@Test
	public void setGetContrast() {
	    model.setContrast(DetectionDefault.CONTRAST);
	    assertEquals(DetectionDefault.CONTRAST, model.getContrast());
	}
	
	@Test
	public void contrastProperty() {
		assertThat(model.contrastProperty(), instanceOf(IntegerProperty.class));
	}

}
