package accefa.service.image;

import accefa.ui.model.ImageConfigModel;

/**
 * Zentrale Schnittstelle um vom UI auf die Services zuzugreifen.
 *
 */
public interface ImageService {

	/**
	 * Speichert die Bild Konfiguration.
	 *
	 * @param model
	 *            Bild Konfiguration.
	 */
	void saveImageConfigModel(ImageConfigModel model)
			throws ImageServiceException;

	/**
	 * Lädt die aktuelle Bild Konfiguration.
	 *
	 * @return Bild Konfiguration.
	 */
	ImageConfigModel readImageConfigModel() throws ImageServiceException;

	/**
	 * Startet den Ballabwurf Prozesse.
	 *
	 */
	void startProcess() throws ImageServiceException;

	/**
	 * Gibt die URL des Bildes zurück.
	 *
	 * @return Bild Url.
	 */
	String getImageUrl();

	/**
	 * Macht ein Foto.
	 */
	void shoot() throws ImageServiceException;

}
