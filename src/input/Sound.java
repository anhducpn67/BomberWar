package input;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;

public class Sound extends Application {
    @Override
    public void start (Stage primaryStage) {
    }

    public static AudioClip playSound(String action) {
        String path = "res/sound/" + action + ".wav";
        Media media = new Media(new File(path).toURI().toString());
        AudioClip audioClip = new AudioClip(media.getSource());
        audioClip.play();
        return audioClip;
    }
}