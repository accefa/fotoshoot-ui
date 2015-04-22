package accefa.ui.view.drive;

import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.concurrent.Task;
import accefa.event.ErrorEvent;
import accefa.service.drive.dc.DcDriveService;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

class DcTaskExecutor {

    private final DcDriveService dcDriveService;

    private final ExecutorService executor;

    private final EventBus eventBus;

    @Inject
    public DcTaskExecutor(final DcDriveService dcDriveService, final ExecutorService executor,
            final EventBus eventBus) {
        this.dcDriveService = dcDriveService;
        this.executor = executor;
        this.eventBus = eventBus;
    }

    void forward() {
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                dcDriveService.forward();
                return null;
            }

            @Override
            protected void failed() {
                postErrorEvent(exceptionProperty().get().getMessage());
            }
        };
        executor.execute(task);
    }

    void backward() {
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                dcDriveService.backward();
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
                dcDriveService.stop();
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
                dcDriveService.reset();
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
