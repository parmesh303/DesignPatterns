package com.parmesh.prepare.interviews.designpattern.creational;

/**
 * Builder Pattern Example
 * Demonstrates how to build complex objects step by step
 */
public class BuilderPattern {
    
    // Product class
    static class Computer {
        // Required parameters
        private final String CPU;
        private final String RAM;
        
        // Optional parameters
        private final String storage;
        private final String graphicsCard;
        private final boolean isBluetoothEnabled;
        private final boolean isWifiEnabled;
        
        private Computer(ComputerBuilder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.graphicsCard = builder.graphicsCard;
            this.isBluetoothEnabled = builder.isBluetoothEnabled;
            this.isWifiEnabled = builder.isWifiEnabled;
        }
        
        @Override
        public String toString() {
            return "Computer{" +
                    "CPU='" + CPU + '\'' +
                    ", RAM='" + RAM + '\'' +
                    ", storage='" + storage + '\'' +
                    ", graphicsCard='" + graphicsCard + '\'' +
                    ", isBluetoothEnabled=" + isBluetoothEnabled +
                    ", isWifiEnabled=" + isWifiEnabled +
                    '}';
        }
        
        // Builder class
        public static class ComputerBuilder {
            // Required parameters
            private final String CPU;
            private final String RAM;
            
            // Optional parameters
            private String storage = "HDD";
            private String graphicsCard = "Integrated";
            private boolean isBluetoothEnabled = false;
            private boolean isWifiEnabled = false;
            
            public ComputerBuilder(String CPU, String RAM) {
                this.CPU = CPU;
                this.RAM = RAM;
            }
            
            public ComputerBuilder storage(String storage) {
                this.storage = storage;
                return this;
            }
            
            public ComputerBuilder graphicsCard(String graphicsCard) {
                this.graphicsCard = graphicsCard;
                return this;
            }
            
            public ComputerBuilder bluetoothEnabled(boolean isBluetoothEnabled) {
                this.isBluetoothEnabled = isBluetoothEnabled;
                return this;
            }
            
            public ComputerBuilder wifiEnabled(boolean isWifiEnabled) {
                this.isWifiEnabled = isWifiEnabled;
                return this;
            }
            
            public Computer build() {
                return new Computer(this);
            }
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Building a basic computer
        Computer basicComputer = new Computer.ComputerBuilder("Intel i5", "8GB")
                .build();
        System.out.println("Basic Computer: " + basicComputer);
        
        // Building a gaming computer
        Computer gamingComputer = new Computer.ComputerBuilder("Intel i9", "32GB")
                .storage("1TB SSD")
                .graphicsCard("NVIDIA RTX 3080")
                .bluetoothEnabled(true)
                .wifiEnabled(true)
                .build();
        System.out.println("Gaming Computer: " + gamingComputer);
        
        // Building a workstation
        Computer workstation = new Computer.ComputerBuilder("AMD Threadripper", "64GB")
                .storage("2TB NVMe SSD")
                .graphicsCard("NVIDIA Quadro RTX 5000")
                .wifiEnabled(true)
                .build();
        System.out.println("Workstation: " + workstation);
    }
} 