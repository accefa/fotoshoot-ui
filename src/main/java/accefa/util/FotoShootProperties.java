package accefa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FotoShootProperties {

	private static final String PROPERTY_URL = "URL";

	private static final String FILE_RUNTIME = "runtime/fotoshoot-ui.properties";

	private static final String FILE_DEFAULT = "default.fotoshoot-ui.properties";

	private Properties clientProperties;

	/**
	 * Lädt die Werte aus den Dateien. Falls keine Runtime-Properties
	 * existieren, werden die Default Properties geladen.
	 */
	public void load() {
		clientProperties = new Properties();
		try {
			InputStream stream = null;
			if (new File(FILE_RUNTIME).exists()) {
				stream = new FileInputStream(FILE_RUNTIME);
			} else {
				stream = this.getClass().getClassLoader()
						.getResourceAsStream(FILE_DEFAULT);
			}
			if (stream != null) {
				clientProperties.load(stream);
			}
		} catch (final IOException io) {
			System.err.println("Could not load " + FILE_RUNTIME
					+ " use default configuration.");
		}
	}

	/**
	 * Persitiert die Werte.
	 */
	public void save() {
		try {
			final File file = new File(FILE_RUNTIME);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			final OutputStream out = new FileOutputStream(FILE_RUNTIME);
			clientProperties.store(out, null);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gibt den Wert der URL zurück.
	 *
	 * @return URL.
	 */
	public String getUrl() {
		return clientProperties.getProperty(PROPERTY_URL);
	}

	/**
	 * Setzt die URL.
	 *
	 * @param url
	 *            Url.
	 */
	public void setUrl(final String url) {
		clientProperties.setProperty(PROPERTY_URL, url);
	}

}
