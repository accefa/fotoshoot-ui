package accefa.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.util.ApplicationPreferences;

public class RaspiClientFactory {

    private static final int CONNECTION_TIMEOUT_MILLISECONDS = 0;
    
    private static final int READ_TIMEOUT_MILLISECONDS = 0;

	/**
     * Liefert ein WebTarget mit der aktuellen URL vom Raspberry Pi zurück. Es wird jedes mal eine
     * neue Client-Instanz erzeugt. Dadurch wird garantiert dass die URL während dem Betrieb
     * geändert werden kann.
     *
     * @return WebTarget mit Raspberry Pi URL
     */
    public WebTarget getRaspiTarget() {
        final ClientConfig clientConfig = new ClientConfig().register(new JacksonFeature());
        final Client client = ClientBuilder.newClient(clientConfig);
        final WebTarget raspiTarget = client.target(new ApplicationPreferences().getRaspiUrl());
        client.property(ClientProperties.CONNECT_TIMEOUT, CONNECTION_TIMEOUT_MILLISECONDS);
        client.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT_MILLISECONDS);
        return raspiTarget;
    }

}
