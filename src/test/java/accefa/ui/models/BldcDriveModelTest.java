package accefa.ui.models;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

import org.junit.Before;
import org.junit.Test;

public class BldcDriveModelTest {

	private BldcDriveModel model;

	@Before
	public void setUp() {
		model = new BldcDriveModel();
	}

	@Test
	public void testSetGetOn() {
		model.setOn(BldcDriveDefault.ON_NORMAL);
		assertEquals(BldcDriveDefault.ON_NORMAL, model.isOn());
	}

	@Test
	public void testOnProperty() {
		assertThat(model.onProperty(), instanceOf(BooleanProperty.class));
	}

	@Test
	public void testSetGetOff() {
		model.setOff(BldcDriveDefault.OFF_NORMAL);
		assertEquals(BldcDriveDefault.OFF_NORMAL, model.isOff());
	}

	@Test
	public void testOffProperty() {
		assertThat(model.offProperty(), instanceOf(BooleanProperty.class));
	}

	@Test
	public void testSetGetReset() {
		model.setReset(BldcDriveDefault.RESET_NORMAL);
		assertEquals(BldcDriveDefault.RESET_NORMAL, model.isReset());
	}

	@Test
	public void testResetProperty() {
		assertThat(model.resetProperty(), instanceOf(BooleanProperty.class));
	}

	@Test
	public void testSetGetRpm() {
		model.setRpm(BldcDriveDefault.RPM_NORMAL);
		assertEquals(BldcDriveDefault.RPM_NORMAL, model.getRpm());
	}

	@Test
	public void testSetGetRpmWithNegativNumber() {
		model.setRpm(BldcDriveDefault.RPM_NEGATIV);
		assertEquals(0, model.getRpm());
	}

	@Test
	public void testRpmProperty() {
		assertThat(model.rpmProperty(), instanceOf(IntegerProperty.class));
	}

}
