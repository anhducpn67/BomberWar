package input;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;

public class Sound extends Application {
    public static AudioClip backgroundSound = Sound.playSound("Area1");
    public static AudioClip stageCleared = Sound.playSound("StageCleared");
    static {
        stageCleared.stop();
        backgroundSound.stop();
    }

    @Override
    public void start (Stage primaryStage) {
    }

    public static AudioClip playSound(String action) {
        String path = "resources/sound/" + action + ".wav";
        Media media = new Media(new File(path).toURI().toString());
        AudioClip audioClip = new AudioClip(media.getSource());
        audioClip.play();
        return audioClip;
    }
}