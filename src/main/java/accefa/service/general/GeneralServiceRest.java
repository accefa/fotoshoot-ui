package accefa.service.general;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import jersey.repackaged.com.google.common.collect.Lists;
import accefa.service.RaspiClientFactory;
import accefa.service.ServiceException;
import accefa.ui.model.LogModel;
import accefa.ui.model.StartSignalModel;

import com.google.common.collect.Sets;
import com.google.inject.Inject;

public class GeneralServiceRest implements GeneralService {

	private static final String RESOURCE_START = "start";

	private static final String RESOURCE_LOGGER = "logger";

	private final RaspiClientFactory clientFactory;

	private final Set<LogModel> currentLogOnClient = Sets.newHashSet();

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
		try {
			final String log = clientFactory.getRaspiTarget()
					.path(RESOURCE_LOGGER).request(MediaType.TEXT_PLAIN_TYPE)
					.get(String.class);

			List<LogModel> currentLogOnServer = parseText(log);
			List<LogModel> newLogsForClient = Lists.newArrayList();
			for (LogModel serverLogModel : currentLogOnServer) {

				if (!currentLogOnClient.contains(serverLogModel)) {
					currentLogOnClient.add(serverLogModel);
					newLogsForClient.add(serverLogModel);
				}
			}
			return newLogsForClient;
		} catch (final RuntimeException e) {
			throw new ServiceException(e);
		}
	}

	private void handleStatusInfo(final StatusType status) {
		final int statusCode = status.getStatusCode();
		if (statusCode != Status.OK.getStatusCode()) {
			throw new RuntimeException("Error " + statusCode + " - "
					+ status.getReasonPhrase());
		}
	}

	private List<LogModel> parseText(String text) {
		Set<LogModel> models = Sets.newHashSet();
		String lines[] = text.split("\\r?\\n");
		for (String line : lines) {
			try {
				String words[] = line.split("\\s+");
				String date = words[0];
				String time = words[1];
				String level = words[2];
				String message = "";
				for (int i = 3; i < words.length; i++) {
					message += words[i] + " ";
				}

				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern("dd/MM/yyyy HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(date + " " + time,
						formatter);

				LogModel model = new LogModel(message.trim(), level.trim(),
						"Server");
				model.setTime(dateTime);
				models.add(model);
			} catch (RuntimeException e) {
				models.add(new LogModel(
						"Nachricht konnte nicht geladen werden", "INFO",
						"Server"));
			}
		}
		return Lists.newArrayList(models);
	}

}
