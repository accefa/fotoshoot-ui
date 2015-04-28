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
		models.add(new LogModel("Server Log 1", "INFO", "Server"));
		models.add(new LogModel("Server Log 2", "INFO", "Server"));
		models.add(new LogModel("Server Log 3", "INFO", "Server"));
		models.add(new LogModel("Server Log 4", "INFO", "Server"));
		models.add(new LogModel("Server Log 5", "INFO", "Server"));

		LogModel model = new LogModel("Server Log 699", "Error", "Server");
		model.setTime(LocalDateTime.of(2010, 11, 6, 12, 56));
		models.add(model);

		return models;
	}

}
