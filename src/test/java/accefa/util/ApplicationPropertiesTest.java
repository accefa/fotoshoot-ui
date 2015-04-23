/**
 *
 */
package accefa.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.prefs.Preferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author Fabian Wüthrich
 *
 */
public class ApplicationPropertiesTest {

    private ApplicationPreferences applicationProperties;

    @Mock
    private Preferences preferences;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        applicationProperties = new ApplicationPreferences(preferences);
    }

    /**
     * Test method for {@link accefa.util.ApplicationPreferences#getRaspiUrl()}.
     */
    @Test
    public final void testGetRaspiUrl() {
        final String raspiUrl = "http://192.168.1.4:8090";
        when(
                preferences.get(ApplicationPreferences.RASPI_URL_KEY,
                        ApplicationPreferences.RASPI_URL_DEFAULT)).thenReturn(raspiUrl);
        assertEquals(raspiUrl, applicationProperties.getRaspiUrl());
    }

    /**
     * Test method for {@link accefa.util.ApplicationPreferences#setRaspiUrl(java.lang.String)}.
     */
    @Test
    public final void testSetRaspiUrl() {
        final String raspiUrl = "http://192.168.1.4:8090";
        applicationProperties.setRaspiUrl(raspiUrl);
        verify(preferences).put(ApplicationPreferences.RASPI_URL_KEY, raspiUrl);
    }

}
