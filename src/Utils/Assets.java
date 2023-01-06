package Utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Assets {
    private static final String FOLDER_NAME = "assets/";
    public static final Image BOMB_IMG = readImage("bomb.png");
    public static final Image FLAG_IMG = readImage("flag.png");

    private static Image readImage(String fileName) {
        try {
            return ImageIO.read(new File(FOLDER_NAME + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AudioInputStream getWinSound() {
        return readAudio("win.wav");
    }

    public static AudioInputStream getLoseSound() {
        return readAudio("lose.wav");
    }

    private static AudioInputStream readAudio(String fileName) {
        try {
            File file = new File(FOLDER_NAME + fileName).getAbsoluteFile();
            return AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
