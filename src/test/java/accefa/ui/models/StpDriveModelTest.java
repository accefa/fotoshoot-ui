package accefa.ui.models;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
    public void testSetSteps() {
        final int steps = 50;
        model.setSteps(steps);
        assertEquals(steps, model.getSteps());
    }

    @Test
    public void testToString() {
        assertThat(model.toString(), instanceOf(String.class));
    }

}
