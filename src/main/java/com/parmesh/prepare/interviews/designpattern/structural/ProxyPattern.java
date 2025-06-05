package com.parmesh.prepare.interviews.designpattern.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * Proxy Pattern Example
 * Demonstrates different types of proxies: Virtual, Protection, and Remote
 */
public class ProxyPattern {
    
    // Subject Interface
    interface Image {
        void display();
    }
    
    // Real Subject
    static class RealImage implements Image {
        private String fileName;
        
        public RealImage(String fileName) {
            this.fileName = fileName;
            loadFromDisk(fileName);
        }
        
        @Override
        public void display() {
            System.out.println("Displaying " + fileName);
        }
        
        private void loadFromDisk(String fileName) {
            System.out.println("Loading " + fileName);
        }
    }
    
    // 1. Virtual Proxy
    static class VirtualProxyImage implements Image {
        private RealImage realImage;
        private String fileName;
        
        public VirtualProxyImage(String fileName) {
            this.fileName = fileName;
        }
        
        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }
    
    // 2. Protection Proxy
    interface User {
        String getRole();
    }
    
    static class RealUser implements User {
        private String role;
        
        public RealUser(String role) {
            this.role = role;
        }
        
        @Override
        public String getRole() {
            return role;
        }
    }
    
    static class ProtectionProxyImage implements Image {
        private RealImage realImage;
        private User user;
        private String fileName;
        
        public ProtectionProxyImage(String fileName, User user) {
            this.fileName = fileName;
            this.user = user;
        }
        
        @Override
        public void display() {
            if (user.getRole().equals("admin")) {
                if (realImage == null) {
                    realImage = new RealImage(fileName);
                }
                realImage.display();
            } else {
                System.out.println("Access denied: User does not have permission to display " + fileName);
            }
        }
    }
    
    // 3. Remote Proxy (Simplified example)
    static class RemoteProxyImage implements Image {
        private String fileName;
        private Map<String, String> cache = new HashMap<>();
        
        public RemoteProxyImage(String fileName) {
            this.fileName = fileName;
        }
        
        @Override
        public void display() {
            // Check cache first
            if (cache.containsKey(fileName)) {
                System.out.println("Displaying from cache: " + cache.get(fileName));
                return;
            }
            
            // Simulate remote call
            String imageData = fetchFromRemoteServer(fileName);
            cache.put(fileName, imageData);
            System.out.println("Displaying: " + imageData);
        }
        
        private String fetchFromRemoteServer(String fileName) {
            // Simulate network delay
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Remote image data for " + fileName;
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // 1. Virtual Proxy
        System.out.println("=== Virtual Proxy ===");
        Image virtualProxy = new VirtualProxyImage("test.jpg");
        // Image will be loaded only when display() is called
        virtualProxy.display();
        // Second call will use the already loaded image
        virtualProxy.display();
        
        // 2. Protection Proxy
        System.out.println("\n=== Protection Proxy ===");
        User admin = new RealUser("admin");
        User regularUser = new RealUser("user");
        
        Image protectionProxy = new ProtectionProxyImage("sensitive.jpg", admin);
        protectionProxy.display(); // Will display
        
        protectionProxy = new ProtectionProxyImage("sensitive.jpg", regularUser);
        protectionProxy.display(); // Will deny access
        
        // 3. Remote Proxy
        System.out.println("\n=== Remote Proxy ===");
        Image remoteProxy = new RemoteProxyImage("remote.jpg");
        remoteProxy.display(); // First call - slow
        remoteProxy.display(); // Second call - fast (from cache)
    }
} 