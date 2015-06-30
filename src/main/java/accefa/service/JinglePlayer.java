package accefa.service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import accefa.event.ProcessStoppedEvent;

import com.google.common.eventbus.Subscribe;

/**
 * Play a jingle if a event is triggered.
 *
 */
public class JinglePlayer {

    @Subscribe
    public void playJingle(final ProcessStoppedEvent event) {
        playJingle();
    }

    private void playJingle() {
        final String uri = this.getClass().getClassLoader().getResource("level-complete.mp3")
                .toString();
        final Media hit = new Media(uri);
        final MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }
}
