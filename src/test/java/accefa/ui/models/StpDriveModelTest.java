package accefa.ui.models;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

import org.junit.Before;
import org.junit.Test;

import accefa.ui.model.StpDriveModel;

public class StpDriveModelTest {

	private StpDriveModel model;

	@Before
	public void setUp() throws Exception {
		model = new StpDriveModel();
	}

	@Test
	public void testSetReset() {
		model.setReset(StpDriveDefault.RESET_NORMAL);
		assertEquals(StpDriveDefault.RESET_NORMAL, model.isReset());
	}

	@Test
	public void testResetProperty() {
		assertThat(model.resetProperty(), instanceOf(BooleanProperty.class));
	}

	@Test
	public void testSetSteps() {
		model.setSteps(StpDriveDefault.STEPS_NORMAL);
		assertEquals(StpDriveDefault.STEPS_NORMAL, model.getSteps());
	}

	@Test
	public void testStepsProperty() {
		assertThat(model.stepsProperty(), instanceOf(IntegerProperty.class));
	}

	@Test
	public void testToString() {
		assertThat(model.toString(), instanceOf(String.class));
	}

}
