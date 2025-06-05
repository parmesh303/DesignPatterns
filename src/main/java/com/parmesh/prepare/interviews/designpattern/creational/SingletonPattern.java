package com.parmesh.prepare.interviews.designpattern.creational;

/**
 * Singleton Pattern Example
 * Different implementations of Singleton pattern
 */
public class SingletonPattern {
    
    // 1. Eager Initialization
    public static class EagerSingleton {
        private static final EagerSingleton instance = new EagerSingleton();
        
        private EagerSingleton() {}
        
        public static EagerSingleton getInstance() {
            return instance;
        }
    }
    
    // 2. Lazy Initialization
    public static class LazySingleton {
        private static LazySingleton instance;
        
        private LazySingleton() {}
        
        public static synchronized LazySingleton getInstance() {
            if (instance == null) {
                instance = new LazySingleton();
            }
            return instance;
        }
    }
    
    // 3. Double-Checked Locking
    public static class DoubleCheckedSingleton {
        private static volatile DoubleCheckedSingleton instance;
        
        private DoubleCheckedSingleton() {}
        
        public static DoubleCheckedSingleton getInstance() {
            if (instance == null) {
                synchronized (DoubleCheckedSingleton.class) {
                    if (instance == null) {
                        instance = new DoubleCheckedSingleton();
                    }
                }
            }
            return instance;
        }
    }
    
    // 4. Bill Pugh Singleton (Recommended)
    public static class BillPughSingleton {
        private BillPughSingleton() {}
        
        private static class SingletonHelper {
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }
        
        public static BillPughSingleton getInstance() {
            return SingletonHelper.INSTANCE;
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Testing Bill Pugh Singleton
        BillPughSingleton instance1 = BillPughSingleton.getInstance();
        BillPughSingleton instance2 = BillPughSingleton.getInstance();
        
        System.out.println("Are instances same? " + (instance1 == instance2));
    }
} 