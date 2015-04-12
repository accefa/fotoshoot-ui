package accefa.util;

import java.util.prefs.Preferences;

public class ApplicationPreferences {

    static final String RASPI_URL_KEY = "RASPI_URL";
    static final String RASPI_URL_DEFAULT = "http://192.168.1.3:8080/";

    static final String WEBSERVER_URL_KEY = "WEBSERVER_URL";
    static final String WEBSERVER_URL_DEFAULT = "http://localhost:8080/";

    private final Preferences preferences;

    public ApplicationPreferences() {
        this(Preferences.userRoot());
    }

    ApplicationPreferences(final Preferences preferences) {
        this.preferences = preferences;
    }

    public String getRaspiUrl() {
        return preferences.get(RASPI_URL_KEY, RASPI_URL_DEFAULT);
    }

    public void setRaspiUrl(final String raspiUrl) {
        preferences.put(RASPI_URL_KEY, raspiUrl);
    }

    public String getWebserverUrl() {
        return preferences.get(WEBSERVER_URL_KEY, WEBSERVER_URL_DEFAULT);
    }

    public void setWebserverUrl(final String webserverUrl) {
        preferences.put(WEBSERVER_URL_KEY, webserverUrl);
    }

}
