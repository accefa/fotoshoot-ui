package accefa.service;

import accefa.ui.models.ImageConfigModel;

/**
 * Zentrale Schnittstelle um vom UI auf die Services zuzugreifen.
 *
 */
public interface RaspiService {

   /**
    * Speichert die Bild Konfiguration.
    *
    * @param model
    *           Bild Konfiguration.
    */
   void saveImageConfigModel(ImageConfigModel model) throws RaspiServiceException;

   /**
    * Lädt die aktuelle Bild Konfiguration.
    *
    * @return Bild Konfiguration.
    */
   ImageConfigModel readImageConfigModel() throws RaspiServiceException;

   /**
    * Startet den Ballabwurf Prozesse.
    *
    */
   void startProcess() throws RaspiServiceException;

}
