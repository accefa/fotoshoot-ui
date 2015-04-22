package accefa.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import accefa.util.ApplicationPreferences;

public class RaspiClientFactory {

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
        return raspiTarget;
    }

}
