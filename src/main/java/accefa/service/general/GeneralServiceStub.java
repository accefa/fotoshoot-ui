package accefa.service.general;

import java.time.LocalDateTime;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;
import accefa.service.ServiceException;
import accefa.ui.model.LogModel;

public class GeneralServiceStub implements GeneralService {

	@Override
	public void start() throws ServiceException {

	}

	@Override
	public List<LogModel> getLogs() throws ServiceException {
		List<LogModel> models = Lists.newArrayList();
		models.add(new LogModel("Ballwurf starten", "INFO", "Server"));
		models.add(new LogModel("Korb erkennen", "INFO", "Server"));
		models.add(new LogModel("Stepper 14320 Schritte nach links", "INFO", "Server"));
		models.add(new LogModel("Schwungrad in Schwung bringen", "INFO", "Server"));
		models.add(new LogModel("Ballnachschub aktivieren", "INFO", "Server"));
		models.add(new LogModel("Ballwurf beendet", "INFO", "Server"));
		for (LogModel model : models) {
			model.setTime(LocalDateTime.of(2015, 5, 22, 12, 56));
		}
		return models;
	}

}
