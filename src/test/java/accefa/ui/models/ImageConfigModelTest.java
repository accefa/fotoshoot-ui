package accefa.ui.models;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

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
      model.setLineY(ImageConfigDefault.LINE_Y);
      assertEquals(ImageConfigDefault.LINE_Y, model.getLineY());
   }

   @Test
   public void testLineYProperty() {
      assertThat(model.lineYProperty(), instanceOf(IntegerProperty.class));
   }

   @Test
   public void testSetGetLineH() {
      model.setLineH(ImageConfigDefault.LINE_H);
      assertEquals(ImageConfigDefault.LINE_H, model.getLineH());
   }

   @Test
   public void testLineHProperty() {
      assertThat(model.lineHProperty(), instanceOf(IntegerProperty.class));
   }

   @Test
   public void testSetGetGreyscaleThreshold() {
      model.setGreyscaleThreshold(ImageConfigDefault.GREYSCALE_THRESHOLD);
      assertEquals(ImageConfigDefault.GREYSCALE_THRESHOLD, model.getGreyscaleThreshold());
   }

   @Test
   public void testGreyscaleThresholdProperty() {
      assertThat(model.greyscaleThresholdProperty(), instanceOf(IntegerProperty.class));
   }

   @Test
   public void testSetGetGreyscale() {
      model.setGreyscale(ImageConfigDefault.GREYSCALE);
      assertEquals(ImageConfigDefault.GREYSCALE, model.isGreyscale());
   }

   @Test
   public void testGreyscaleProperty() {
      assertThat(model.greyscaleProperty(), instanceOf(BooleanProperty.class));
   }

   @Test
   public void testSetGetQuality() {
      model.setQuality(ImageConfigDefault.QUALITY);
      assertEquals(ImageConfigDefault.QUALITY, model.getQuality());
   }

   @Test
   public void testQualityProperty() {
      assertThat(model.qualityProperty(), instanceOf(IntegerProperty.class));
   }

   @Test
   public void testSetGetCropX() {
      model.setCropX(ImageConfigDefault.CROP_X);
      assertEquals(ImageConfigDefault.CROP_X, model.getCropX());
   }

   @Test
   public void testCropXProperty() {
      assertThat(model.cropXProperty(), instanceOf(IntegerProperty.class));
   }

   @Test
   public void testSetGetContrast() {
      model.setContrast(ImageConfigDefault.CONTRAST);
      assertEquals(ImageConfigDefault.CONTRAST, model.getContrast());
   }

   @Test
   public void testContrastProperty() {
      assertThat(model.contrastProperty(), instanceOf(IntegerProperty.class));
   }

   @Test
   public void testSetGetImage() {
      model.setImage(ImageConfigDefault.IMAGE);
      assertEquals(ImageConfigDefault.IMAGE, model.getImage());
   }

   @Test
   public void testImageProperty() {
      assertThat(model.imageProperty(), instanceOf(StringProperty.class));
   }

   @Test
   public void testTotString() {
      assertThat(model.toString(), instanceOf(String.class));
   }

}
