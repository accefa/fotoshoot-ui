package accefa.ui.view.drive;

import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.concurrent.Task;
import accefa.event.ErrorEvent;
import accefa.service.drive.bldc.BldcDriveService;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class BldcTaskExecutor {

    private final BldcDriveService bldcDriveService;

    private final ExecutorService executor;

    private final EventBus eventBus;

    @Inject
    public BldcTaskExecutor(final BldcDriveService bldcDriveService,
            final ExecutorService executor, final EventBus eventBus) {
        this.bldcDriveService = bldcDriveService;
        this.executor = executor;
        this.eventBus = eventBus;
    }

    void start(final int rpm) {
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                bldcDriveService.start(rpm);
                return null;
            }

            @Override
            protected void failed() {
                postErrorEvent(exceptionProperty().get().getMessage());
            }
        };
        executor.execute(task);
    }

    void stop() {
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                bldcDriveService.stop();
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
                bldcDriveService.reset();
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
