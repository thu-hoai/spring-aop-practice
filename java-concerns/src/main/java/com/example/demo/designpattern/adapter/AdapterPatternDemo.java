package com.example.demo.designpattern.adapter;

// "Target" interface that the client expects
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// "Adaptee" class with an incompatible interface
class AdvancedMediaPlayer {
    void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// "Adapter" class implementing the "Target" interface and composing an instance of the "Adaptee"
class AdvancedMediaPlayerAdapter implements MediaPlayer {
    private final AdvancedMediaPlayer advancedMediaPlayer;

    public AdvancedMediaPlayerAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            this.advancedMediaPlayer = new AdvancedMediaPlayer();
        } else {
            throw new UnsupportedOperationException("Invalid audio type: " + audioType);
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}

// "Client" that uses the "Target" interface
class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            // Use adapter to play vlc or mp4 files
            AdvancedMediaPlayerAdapter adapter = new AdvancedMediaPlayerAdapter(audioType);
            adapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}

// Demo class to test the adapter pattern
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "file1.mp3");
        audioPlayer.play("vlc", "file2.vlc");
        audioPlayer.play("mp4", "file3.mp4");
        audioPlayer.play("avi", "file4.avi");
    }
}