package ninjaheroes;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ninjaheroes.gui.HeroSelectionScreen;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MainGUI extends Application {

    private MediaPlayer bgm;
    private Clip wavClip;

    @Override
    public void start(Stage stage) {
        playBackgroundMusic();

        // Tampilkan layar pemilihan hero lebih dulu
        HeroSelectionScreen selectScreen = new HeroSelectionScreen(stage);
        Scene scene = selectScreen.getScene();

        stage.setTitle("Ninja Heroes PBO - Pilih Hero");
        stage.setScene(scene);
        stage.show();
    }

    private void playBackgroundMusic() {
        // Prefer JavaFX Media on MP3/WAV/M4A; if gagal, fallback ke JavaSound (WAV).
        String[] fxCandidates = {
                "assets/songs/videoplayback.mp3",
                "assets/songs/videoplayback.wav",
                "assets/songs/videoplayback.m4a"
        };

        for (String path : fxCandidates) {
            try {
                Media media = new Media(new File(path).toURI().toString());
                bgm = new MediaPlayer(media);
                bgm.setCycleCount(MediaPlayer.INDEFINITE); // loop forever
                bgm.setVolume(0.25); // sedikit pelan
                bgm.play();
                return;
            } catch (Exception e) {
                System.err.println("Gagal memutar musik latar (JavaFX, " + path + "): " + e.getMessage());
            }
        }

        // Fallback: Java Sound pakai WAV PCM
        try {
            playWithJavaSound("assets/songs/videoplayback.wav");
        } catch (Exception e) {
            System.err.println("Tidak ada lagu yang bisa diputar. JavaSound gagal: " + e.getMessage());
        }
    }

    private void playWithJavaSound(String wavPath) throws Exception {
        File file = new File(wavPath);
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(file)) {
            wavClip = AudioSystem.getClip();
            wavClip.open(ais);
            wavClip.loop(Clip.LOOP_CONTINUOUSLY);
            wavClip.start();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
