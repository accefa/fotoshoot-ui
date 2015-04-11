package accefa.service.drive.stp;

/**
 * Service um auf den STP-Motor vom Raspberry Pi zuzugreifen.
 */
public interface StpDriveService {

    /**
     * Schickt eine Anfrage um den Motor die übergebenen Anzahl Schritte laufen zu lassen.
     *
     * @param steps Anzahl Schritte
     */
    void start(int steps);

    /**
     * Schickt eine Anfrage um einen Reset beim Motor durchzuführen.
     */
    void reset();
}
