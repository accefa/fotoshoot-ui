package accefa.service.general;

import java.util.List;

import accefa.service.ServiceException;
import accefa.ui.model.LogModel;

public interface GeneralService {

	void start() throws ServiceException;

	List<LogModel> getLogs() throws ServiceException;

}
