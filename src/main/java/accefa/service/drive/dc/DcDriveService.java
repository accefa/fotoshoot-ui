package accefa.service.drive.dc;

/**
 * Service um auf den DC-Motor vom Raspberry Pi zuzugreifen.
 */
public interface DcDriveService {

    /**
     * Schickt eine Anfrage um den Motor vorwärts laufen zu lassen.
     */
    void forward();

    /**
     * Schickt eine Anfrage um den Motor rückwärts laufen zu lassen.
     */
    void backward();

    /**
     * Schickt eine Anfrage um einen Reset beim Motor durchzuführen.
     */
    void reset();
}
