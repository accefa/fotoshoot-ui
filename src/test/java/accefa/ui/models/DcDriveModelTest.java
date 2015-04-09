package accefa.ui.models;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import javafx.beans.property.BooleanProperty;

import org.junit.Before;
import org.junit.Test;

import accefa.ui.model.DcDriveModel;

public class DcDriveModelTest {

	private DcDriveModel model;

	@Before
	public void setUp() {
		model = new DcDriveModel();
	}

	@Test
	public void testSetGetForward() {
		model.setForward(DcDriveDefault.FORWARD_NORMAL);
		assertEquals(DcDriveDefault.FORWARD_NORMAL, model.isForward());
	}

	@Test
	public void testForwardProperty() {
		assertThat(model.forwardProperty(), instanceOf(BooleanProperty.class));
	}

	@Test
	public void testSetGetReverse() {
		model.setReverse(DcDriveDefault.REVERT_NORMAL);
		assertEquals(DcDriveDefault.REVERT_NORMAL, model.isReverse());
	}

	@Test
	public void testReverseProperty() {
		assertThat(model.reverseProperty(), instanceOf(BooleanProperty.class));
	}

	@Test
	public void testSetReset() {
		model.setReset(DcDriveDefault.RESET_NORMAL);
		assertEquals(DcDriveDefault.RESET_NORMAL, model.isReset());
	}

	@Test
	public void testResetProperty() {
		assertThat(model.resetProperty(), instanceOf(BooleanProperty.class));
	}

	@Test
	public void testTotString() {
		assertThat(model.toString(), instanceOf(String.class));
	}

}
