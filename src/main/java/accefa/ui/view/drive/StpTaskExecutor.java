package accefa.ui.view.drive;

import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.concurrent.Task;
import accefa.event.ErrorEvent;
import accefa.service.drive.stp.StpDriveService;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class StpTaskExecutor {

    private final StpDriveService stpDriveService;

    private final ExecutorService executor;

    private final EventBus eventBus;

    @Inject
    public StpTaskExecutor(final StpDriveService stpDriveService, final ExecutorService executor,
            final EventBus eventBus) {
        this.stpDriveService = stpDriveService;
        this.executor = executor;
        this.eventBus = eventBus;
    }

    void start(final int steps) {
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                stpDriveService.start(steps);
                return null;
            }

            @Override
            protected void failed() {
                postErrorEvent(exceptionProperty().get().getMessage());
            }
        };
        executor.execute(task);
    }

    void reset() {
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                stpDriveService.reset();
                return null;
            }

            @Override
            protected void failed() {
                postErrorEvent(exceptionProperty().get().getMessage());
            }
        };
        executor.execute(task);
    }

    private void postErrorEvent(final String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                eventBus.post(new ErrorEvent(message));
            }
        });
    }

}
