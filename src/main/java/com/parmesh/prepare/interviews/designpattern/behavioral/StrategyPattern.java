package com.parmesh.prepare.interviews.designpattern.behavioral;

/**
 * Strategy Pattern Example
 * Demonstrates different payment strategies
 */
public class StrategyPattern {
    
    // Strategy Interface
    interface PaymentStrategy {
        void pay(int amount);
    }
    
    // Concrete Strategies
    static class CreditCardStrategy implements PaymentStrategy {
        private String cardNumber;
        private String cvv;
        private String dateOfExpiry;
        
        public CreditCardStrategy(String cardNumber, String cvv, String dateOfExpiry) {
            this.cardNumber = cardNumber;
            this.cvv = cvv;
            this.dateOfExpiry = dateOfExpiry;
        }
        
        @Override
        public void pay(int amount) {
            System.out.println(amount + " paid with credit card: " + cardNumber);
        }
    }
    
    static class PayPalStrategy implements PaymentStrategy {
        private String email;
        private String password;
        
        public PayPalStrategy(String email, String password) {
            this.email = email;
            this.password = password;
        }
        
        @Override
        public void pay(int amount) {
            System.out.println(amount + " paid using PayPal account: " + email);
        }
    }
    
    static class CryptoStrategy implements PaymentStrategy {
        private String walletAddress;
        
        public CryptoStrategy(String walletAddress) {
            this.walletAddress = walletAddress;
        }
        
        @Override
        public void pay(int amount) {
            System.out.println(amount + " paid using cryptocurrency from wallet: " + walletAddress);
        }
    }
    
    // Context
    static class ShoppingCart {
        private PaymentStrategy paymentStrategy;
        
        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.paymentStrategy = strategy;
        }
        
        public void checkout(int amount) {
            paymentStrategy.pay(amount);
        }
    }
    
    // Another example: Sorting Strategies
    interface SortingStrategy {
        void sort(int[] array);
    }
    
    static class BubbleSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            System.out.println("Sorting using Bubble Sort");
            // Implementation of bubble sort
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }
    
    static class QuickSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            System.out.println("Sorting using Quick Sort");
            quickSort(array, 0, array.length - 1);
        }
        
        private void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pi = partition(array, low, high);
                quickSort(array, low, pi - 1);
                quickSort(array, pi + 1, high);
            }
        }
        
        private int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = (low - 1);
            
            for (int j = low; j < high; j++) {
                if (array[j] <= pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            
            return i + 1;
        }
    }
    
    static class Sorter {
        private SortingStrategy strategy;
        
        public void setStrategy(SortingStrategy strategy) {
            this.strategy = strategy;
        }
        
        public void sortArray(int[] array) {
            strategy.sort(array);
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Payment Strategy Example
        System.out.println("=== Payment Strategy Example ===");
        ShoppingCart cart = new ShoppingCart();
        
        // Pay with credit card
        cart.setPaymentStrategy(new CreditCardStrategy("1234-5678-9012-3456", "123", "12/25"));
        cart.checkout(100);
        
        // Pay with PayPal
        cart.setPaymentStrategy(new PayPalStrategy("user@example.com", "password123"));
        cart.checkout(200);
        
        // Pay with cryptocurrency
        cart.setPaymentStrategy(new CryptoStrategy("0x1234567890abcdef"));
        cart.checkout(300);
        
        // Sorting Strategy Example
        System.out.println("\n=== Sorting Strategy Example ===");
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        
        Sorter sorter = new Sorter();
        
        // Use Bubble Sort
        sorter.setStrategy(new BubbleSortStrategy());
        sorter.sortArray(array.clone());
        
        // Use Quick Sort
        sorter.setStrategy(new QuickSortStrategy());
        sorter.sortArray(array.clone());
    }
} 