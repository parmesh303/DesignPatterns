package com.parmesh.prepare.interviews.designpattern.structural;

/**
 * Facade Pattern Example
 * Demonstrates how to simplify complex subsystem interfaces
 */
public class FacadePattern {
    
    // Complex subsystem classes
    static class CPU {
        public void freeze() {
            System.out.println("CPU: Freezing...");
        }
        
        public void jump(long position) {
            System.out.println("CPU: Jumping to position " + position);
        }
        
        public void execute() {
            System.out.println("CPU: Executing...");
        }
    }
    
    static class Memory {
        public void load(long position, byte[] data) {
            System.out.println("Memory: Loading data at position " + position);
        }
    }
    
    static class HardDrive {
        public byte[] read(long lba, int size) {
            System.out.println("HardDrive: Reading " + size + " bytes from LBA " + lba);
            return new byte[size];
        }
    }
    
    // Facade
    static class ComputerFacade {
        private final CPU cpu;
        private final Memory memory;
        private final HardDrive hardDrive;
        
        private static final long BOOT_ADDRESS = 0x0000;
        private static final long BOOT_SECTOR = 0x0000;
        private static final int SECTOR_SIZE = 1024;
        
        public ComputerFacade() {
            this.cpu = new CPU();
            this.memory = new Memory();
            this.hardDrive = new HardDrive();
        }
        
        public void start() {
            System.out.println("=== Starting Computer ===");
            cpu.freeze();
            memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
            cpu.jump(BOOT_ADDRESS);
            cpu.execute();
            System.out.println("=== Computer Started ===");
        }
        
        public void shutdown() {
            System.out.println("=== Shutting Down Computer ===");
            // Perform shutdown operations
            System.out.println("=== Computer Shutdown Complete ===");
        }
    }
    
    // Another example: Home Theater Facade
    static class Amplifier {
        public void on() {
            System.out.println("Amplifier: Turning on");
        }
        
        public void setVolume(int level) {
            System.out.println("Amplifier: Setting volume to " + level);
        }
        
        public void off() {
            System.out.println("Amplifier: Turning off");
        }
    }
    
    static class Tuner {
        public void on() {
            System.out.println("Tuner: Turning on");
        }
        
        public void off() {
            System.out.println("Tuner: Turning off");
        }
    }
    
    static class StreamingPlayer {
        public void on() {
            System.out.println("Streaming Player: Turning on");
        }
        
        public void play(String movie) {
            System.out.println("Streaming Player: Playing " + movie);
        }
        
        public void off() {
            System.out.println("Streaming Player: Turning off");
        }
    }
    
    static class Projector {
        public void on() {
            System.out.println("Projector: Turning on");
        }
        
        public void wideScreenMode() {
            System.out.println("Projector: Setting widescreen mode");
        }
        
        public void off() {
            System.out.println("Projector: Turning off");
        }
    }
    
    static class HomeTheaterFacade {
        private final Amplifier amp;
        private final Tuner tuner;
        private final StreamingPlayer player;
        private final Projector projector;
        
        public HomeTheaterFacade() {
            this.amp = new Amplifier();
            this.tuner = new Tuner();
            this.player = new StreamingPlayer();
            this.projector = new Projector();
        }
        
        public void watchMovie(String movie) {
            System.out.println("=== Getting ready to watch " + movie + " ===");
            amp.on();
            amp.setVolume(5);
            player.on();
            player.play(movie);
            projector.on();
            projector.wideScreenMode();
        }
        
        public void endMovie() {
            System.out.println("=== Shutting down home theater ===");
            player.off();
            projector.off();
            amp.off();
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Computer Facade Example
        System.out.println("=== Computer Facade Example ===");
        ComputerFacade computer = new ComputerFacade();
        computer.start();
        computer.shutdown();
        
        // Home Theater Facade Example
        System.out.println("\n=== Home Theater Facade Example ===");
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
} 