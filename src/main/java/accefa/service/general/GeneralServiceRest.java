package accefa.service.general;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import accefa.service.RaspiClientFactory;
import accefa.service.ServiceException;
import accefa.ui.model.LogModel;
import accefa.ui.model.StartSignalModel;

import com.google.inject.Inject;

public class GeneralServiceRest implements GeneralService {

	private static final String RESOURCE_START = "start";

	private final RaspiClientFactory clientFactory;

	@Inject
	public GeneralServiceRest(final RaspiClientFactory raspiService) {
		this.clientFactory = raspiService;
	}

	@Override
	public void start() throws ServiceException {
		try {
			final StartSignalModel model = new StartSignalModel();
			final Response response = clientFactory.getRaspiTarget()
					.path(RESOURCE_START).request(MediaType.TEXT_PLAIN_TYPE)
					.put(Entity.entity(model, MediaType.APPLICATION_JSON_TYPE));
			handleStatusInfo(response.getStatusInfo());
		} catch (final RuntimeException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<LogModel> getLogs() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	private void handleStatusInfo(final StatusType status) {
        final int statusCode = status.getStatusCode();
        if (statusCode != Status.OK.getStatusCode()) {
            throw new RuntimeException("Error " + statusCode + " - " + status.getReasonPhrase());
        }
    }
	
}
