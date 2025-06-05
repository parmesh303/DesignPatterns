package com.parmesh.prepare.interviews.designpattern.structural;

/**
 * Adapter Pattern Example
 * Demonstrates both Class Adapter and Object Adapter patterns
 */
public class AdapterPattern {
    
    // Target Interface
    interface MediaPlayer {
        void play(String audioType, String fileName);
    }
    
    // Adaptee Interface
    interface AdvancedMediaPlayer {
        void playVlc(String fileName);
        void playMp4(String fileName);
    }
    
    // Concrete Adaptee 1
    static class VlcPlayer implements AdvancedMediaPlayer {
        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file: " + fileName);
        }
        
        @Override
        public void playMp4(String fileName) {
            // Do nothing
        }
    }
    
    // Concrete Adaptee 2
    static class Mp4Player implements AdvancedMediaPlayer {
        @Override
        public void playVlc(String fileName) {
            // Do nothing
        }
        
        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file: " + fileName);
        }
    }
    
    // 1. Class Adapter
    static class MediaAdapter implements MediaPlayer {
        private AdvancedMediaPlayer advancedMusicPlayer;
        
        public MediaAdapter(String audioType) {
            if (audioType.equalsIgnoreCase("vlc")) {
                advancedMusicPlayer = new VlcPlayer();
            } else if (audioType.equalsIgnoreCase("mp4")) {
                advancedMusicPlayer = new Mp4Player();
            }
        }
        
        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("vlc")) {
                advancedMusicPlayer.playVlc(fileName);
            } else if (audioType.equalsIgnoreCase("mp4")) {
                advancedMusicPlayer.playMp4(fileName);
            }
        }
    }
    
    // 2. Object Adapter
    static class MediaPlayerAdapter implements MediaPlayer {
        private final AdvancedMediaPlayer advancedMediaPlayer;
        
        public MediaPlayerAdapter(AdvancedMediaPlayer advancedMediaPlayer) {
            this.advancedMediaPlayer = advancedMediaPlayer;
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
    
    // Concrete Target
    static class AudioPlayer implements MediaPlayer {
        MediaAdapter mediaAdapter;
        
        @Override
        public void play(String audioType, String fileName) {
            // Inbuilt support for mp3
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("Playing mp3 file: " + fileName);
            }
            // MediaAdapter is providing support for other formats
            else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
                mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
            } else {
                System.out.println("Invalid media type: " + audioType);
            }
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Using Class Adapter
        System.out.println("=== Using Class Adapter ===");
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("vlc", "movie.vlc");
        audioPlayer.play("mp4", "video.mp4");
        
        // Using Object Adapter
        System.out.println("\n=== Using Object Adapter ===");
        MediaPlayer vlcPlayer = new MediaPlayerAdapter(new VlcPlayer());
        vlcPlayer.play("vlc", "movie.vlc");
        
        MediaPlayer mp4Player = new MediaPlayerAdapter(new Mp4Player());
        mp4Player.play("mp4", "video.mp4");
    }
} 