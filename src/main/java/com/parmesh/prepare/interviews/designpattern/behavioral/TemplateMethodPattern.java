package com.parmesh.prepare.interviews.designpattern.behavioral;

/**
 * Template Method Pattern Example
 * Demonstrates algorithm skeleton definition with customizable steps
 */
public class TemplateMethodPattern {
    
    // Abstract Class with Template Method
    abstract static class DataMiner {
        // Template Method
        public final void mine() {
            openFile();
            extractData();
            parseData();
            analyzeData();
            generateReport();
            closeFile();
        }
        
        // Common operations
        private void openFile() {
            System.out.println("Opening file...");
        }
        
        private void closeFile() {
            System.out.println("Closing file...");
        }
        
        // Operations to be implemented by subclasses
        protected abstract void extractData();
        protected abstract void parseData();
        protected abstract void analyzeData();
        protected abstract void generateReport();
    }
    
    // Concrete Classes
    static class PDFDataMiner extends DataMiner {
        @Override
        protected void extractData() {
            System.out.println("Extracting data from PDF file...");
        }
        
        @Override
        protected void parseData() {
            System.out.println("Parsing PDF data...");
        }
        
        @Override
        protected void analyzeData() {
            System.out.println("Analyzing PDF data...");
        }
        
        @Override
        protected void generateReport() {
            System.out.println("Generating PDF report...");
        }
    }
    
    static class CSVDataMiner extends DataMiner {
        @Override
        protected void extractData() {
            System.out.println("Extracting data from CSV file...");
        }
        
        @Override
        protected void parseData() {
            System.out.println("Parsing CSV data...");
        }
        
        @Override
        protected void analyzeData() {
            System.out.println("Analyzing CSV data...");
        }
        
        @Override
        protected void generateReport() {
            System.out.println("Generating CSV report...");
        }
    }
    
    // Another example: Beverage Preparation
    abstract static class Beverage {
        // Template Method
        public final void prepareBeverage() {
            boilWater();
            brew();
            pourInCup();
            if (customerWantsCondiments()) {
                addCondiments();
            }
        }
        
        // Common operations
        private void boilWater() {
            System.out.println("Boiling water...");
        }
        
        private void pourInCup() {
            System.out.println("Pouring into cup...");
        }
        
        // Operations to be implemented by subclasses
        protected abstract void brew();
        protected abstract void addCondiments();
        
        // Hook
        protected boolean customerWantsCondiments() {
            return true;
        }
    }
    
    static class Coffee extends Beverage {
        @Override
        protected void brew() {
            System.out.println("Dripping coffee through filter...");
        }
        
        @Override
        protected void addCondiments() {
            System.out.println("Adding sugar and milk...");
        }
    }
    
    static class Tea extends Beverage {
        private boolean wantsCondiments;
        
        public Tea(boolean wantsCondiments) {
            this.wantsCondiments = wantsCondiments;
        }
        
        @Override
        protected void brew() {
            System.out.println("Steeping the tea...");
        }
        
        @Override
        protected void addCondiments() {
            System.out.println("Adding lemon...");
        }
        
        @Override
        protected boolean customerWantsCondiments() {
            return wantsCondiments;
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Data Mining Example
        System.out.println("=== Data Mining Example ===");
        DataMiner pdfMiner = new PDFDataMiner();
        System.out.println("Mining PDF data:");
        pdfMiner.mine();
        
        System.out.println("\nMining CSV data:");
        DataMiner csvMiner = new CSVDataMiner();
        csvMiner.mine();
        
        // Beverage Preparation Example
        System.out.println("\n=== Beverage Preparation Example ===");
        System.out.println("Preparing coffee:");
        Beverage coffee = new Coffee();
        coffee.prepareBeverage();
        
        System.out.println("\nPreparing tea with condiments:");
        Beverage teaWithCondiments = new Tea(true);
        teaWithCondiments.prepareBeverage();
        
        System.out.println("\nPreparing tea without condiments:");
        Beverage teaWithoutCondiments = new Tea(false);
        teaWithoutCondiments.prepareBeverage();
    }
} 