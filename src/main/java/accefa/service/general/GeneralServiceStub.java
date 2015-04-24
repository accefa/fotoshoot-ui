package accefa.service.general;

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
		return Lists.newArrayList();
	}

}
