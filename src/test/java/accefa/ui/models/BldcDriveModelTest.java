package accefa.ui.models;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import accefa.ui.model.BldcDriveModel;

public class BldcDriveModelTest {

    private BldcDriveModel model;

    @Before
    public void setUp() {
        model = new BldcDriveModel();
    }

    @Test
    public void testSetGetRpm() {
        final int rpm = 1000;
        model.setRpm(rpm);
        assertEquals(rpm, model.getRpm());
    }

    @Test
    public void testSetGetRpmWithNegativNumber() {
        final int negativ_rpm = -100;
        model.setRpm(negativ_rpm);
        assertEquals(0, model.getRpm());
    }

    @Test
    public void testTotString() {
        assertThat(model.toString(), instanceOf(String.class));
    }

}
