package com.parmesh.prepare.interviews.designpattern.creational;

/**
 * Factory Pattern Example
 * Demonstrates Simple Factory, Factory Method, and Abstract Factory patterns
 */
public class FactoryPattern {
    
    // Product Interface
    interface Notification {
        void notifyUser();
    }
    
    // Concrete Products
    static class EmailNotification implements Notification {
        @Override
        public void notifyUser() {
            System.out.println("Sending an email notification");
        }
    }
    
    static class SMSNotification implements Notification {
        @Override
        public void notifyUser() {
            System.out.println("Sending an SMS notification");
        }
    }
    
    static class PushNotification implements Notification {
        @Override
        public void notifyUser() {
            System.out.println("Sending a push notification");
        }
    }
    
    // 1. Simple Factory
    static class SimpleNotificationFactory {
        public Notification createNotification(String channel) {
            if (channel == null || channel.isEmpty())
                return null;
            switch (channel) {
                case "SMS":
                    return new SMSNotification();
                case "EMAIL":
                    return new EmailNotification();
                case "PUSH":
                    return new PushNotification();
                default:
                    throw new IllegalArgumentException("Unknown channel " + channel);
            }
        }
    }
    
    // 2. Factory Method
    abstract static class NotificationCreator {
        abstract Notification createNotification();
        
        public void sendNotification() {
            Notification notification = createNotification();
            notification.notifyUser();
        }
    }
    
    static class EmailNotificationCreator extends NotificationCreator {
        @Override
        Notification createNotification() {
            return new EmailNotification();
        }
    }
    
    static class SMSNotificationCreator extends NotificationCreator {
        @Override
        Notification createNotification() {
            return new SMSNotification();
        }
    }
    
    // 3. Abstract Factory
    interface NotificationAbstractFactory {
        Notification createNotification();
        NotificationTemplate createTemplate();
    }
    
    interface NotificationTemplate {
        String getTemplate();
    }
    
    static class EmailTemplate implements NotificationTemplate {
        @Override
        public String getTemplate() {
            return "Email Template";
        }
    }
    
    static class SMSTemplate implements NotificationTemplate {
        @Override
        public String getTemplate() {
            return "SMS Template";
        }
    }
    
    static class EmailNotificationFactory implements NotificationAbstractFactory {
        @Override
        public Notification createNotification() {
            return new EmailNotification();
        }
        
        @Override
        public NotificationTemplate createTemplate() {
            return new EmailTemplate();
        }
    }
    
    static class SMSNotificationFactory implements NotificationAbstractFactory {
        @Override
        public Notification createNotification() {
            return new SMSNotification();
        }
        
        @Override
        public NotificationTemplate createTemplate() {
            return new SMSTemplate();
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // 1. Simple Factory
        System.out.println("=== Simple Factory ===");
        SimpleNotificationFactory simpleFactory = new SimpleNotificationFactory();
        Notification email = simpleFactory.createNotification("EMAIL");
        email.notifyUser();
        
        // 2. Factory Method
        System.out.println("\n=== Factory Method ===");
        NotificationCreator emailCreator = new EmailNotificationCreator();
        emailCreator.sendNotification();
        
        // 3. Abstract Factory
        System.out.println("\n=== Abstract Factory ===");
        NotificationAbstractFactory emailFactory = new EmailNotificationFactory();
        Notification emailNotification = emailFactory.createNotification();
        NotificationTemplate emailTemplate = emailFactory.createTemplate();
        emailNotification.notifyUser();
        System.out.println("Using template: " + emailTemplate.getTemplate());
    }
} 