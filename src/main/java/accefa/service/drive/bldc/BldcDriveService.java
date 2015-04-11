package accefa.service.drive.bldc;

/**
 * Service um auf den BLDC-Motor vom Raspberry Pi zuzugreifen.
 */
public interface BldcDriveService {

    /**
     * Schickt eine Anfrage um den Motor mit der übergebenen Drehzahl zu starten.
     *
     * @param rpm Drehzahl des Motors
     */
    void start(int rpm);

    /**
     * Schickt eine Anfrage um den Motor zu stoppen.
     */
    void stop();

    /**
     * Schickt eine Anfrage um einen Reset beim Motor durchzuführen.
     */
    void reset();
}
