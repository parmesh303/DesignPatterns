package com.parmesh.prepare.interviews.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern Example
 * Demonstrates the publisher-subscriber model
 */
public class ObserverPattern {
    
    // Subject (Publisher)
    interface Subject {
        void registerObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers();
    }
    
    // Observer (Subscriber)
    interface Observer {
        void update(String message);
    }
    
    // Concrete Subject
    static class NewsAgency implements Subject {
        private List<Observer> observers;
        private String news;
        
        public NewsAgency() {
            this.observers = new ArrayList<>();
        }
        
        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }
        
        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }
        
        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(news);
            }
        }
        
        public void setNews(String news) {
            this.news = news;
            notifyObservers();
        }
    }
    
    // Concrete Observers
    static class NewsChannel implements Observer {
        private String name;
        
        public NewsChannel(String name) {
            this.name = name;
        }
        
        @Override
        public void update(String news) {
            System.out.println(name + " received news: " + news);
        }
    }
    
    // Another example: Stock Market
    interface StockObserver {
        void update(String stockSymbol, double price);
    }
    
    static class StockMarket {
        private List<StockObserver> observers = new ArrayList<>();
        private String stockSymbol;
        private double price;
        
        public StockMarket(String stockSymbol, double price) {
            this.stockSymbol = stockSymbol;
            this.price = price;
        }
        
        public void registerObserver(StockObserver observer) {
            observers.add(observer);
        }
        
        public void removeObserver(StockObserver observer) {
            observers.remove(observer);
        }
        
        public void notifyObservers() {
            for (StockObserver observer : observers) {
                observer.update(stockSymbol, price);
            }
        }
        
        public void setPrice(double price) {
            this.price = price;
            notifyObservers();
        }
    }
    
    static class StockTrader implements StockObserver {
        private String name;
        
        public StockTrader(String name) {
            this.name = name;
        }
        
        @Override
        public void update(String stockSymbol, double price) {
            System.out.println(name + " received update for " + stockSymbol + ": $" + price);
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // News Agency Example
        System.out.println("=== News Agency Example ===");
        NewsAgency newsAgency = new NewsAgency();
        
        NewsChannel channel1 = new NewsChannel("CNN");
        NewsChannel channel2 = new NewsChannel("BBC");
        NewsChannel channel3 = new NewsChannel("Fox News");
        
        newsAgency.registerObserver(channel1);
        newsAgency.registerObserver(channel2);
        newsAgency.registerObserver(channel3);
        
        newsAgency.setNews("Breaking: New technology breakthrough!");
        
        newsAgency.removeObserver(channel2);
        newsAgency.setNews("Update: Market trends for 2024");
        
        // Stock Market Example
        System.out.println("\n=== Stock Market Example ===");
        StockMarket appleStock = new StockMarket("AAPL", 150.0);
        
        StockTrader trader1 = new StockTrader("John");
        StockTrader trader2 = new StockTrader("Alice");
        StockTrader trader3 = new StockTrader("Bob");
        
        appleStock.registerObserver(trader1);
        appleStock.registerObserver(trader2);
        appleStock.registerObserver(trader3);
        
        appleStock.setPrice(155.0);
        
        appleStock.removeObserver(trader2);
        appleStock.setPrice(160.0);
    }
} 