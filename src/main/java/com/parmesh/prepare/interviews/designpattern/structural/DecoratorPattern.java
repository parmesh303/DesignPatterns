package com.parmesh.prepare.interviews.designpattern.structural;

/**
 * Decorator Pattern Example
 * Demonstrates dynamic behavior addition to objects
 */
public class DecoratorPattern {
    
    // Component Interface
    interface Coffee {
        double getCost();
        String getDescription();
    }
    
    // Concrete Component
    static class SimpleCoffee implements Coffee {
        @Override
        public double getCost() {
            return 1.0;
        }
        
        @Override
        public String getDescription() {
            return "Simple coffee";
        }
    }
    
    // Base Decorator
    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee coffee;
        
        public CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
        
        @Override
        public double getCost() {
            return coffee.getCost();
        }
        
        @Override
        public String getDescription() {
            return coffee.getDescription();
        }
    }
    
    // Concrete Decorators
    static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }
        
        @Override
        public double getCost() {
            return super.getCost() + 0.5;
        }
        
        @Override
        public String getDescription() {
            return super.getDescription() + " with milk";
        }
    }
    
    static class SugarDecorator extends CoffeeDecorator {
        public SugarDecorator(Coffee coffee) {
            super(coffee);
        }
        
        @Override
        public double getCost() {
            return super.getCost() + 0.2;
        }
        
        @Override
        public String getDescription() {
            return super.getDescription() + " with sugar";
        }
    }
    
    static class CaramelDecorator extends CoffeeDecorator {
        public CaramelDecorator(Coffee coffee) {
            super(coffee);
        }
        
        @Override
        public double getCost() {
            return super.getCost() + 0.7;
        }
        
        @Override
        public String getDescription() {
            return super.getDescription() + " with caramel";
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Simple coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost: $" + coffee.getCost() + "; Description: " + coffee.getDescription());
        
        // Coffee with milk
        Coffee coffeeWithMilk = new MilkDecorator(new SimpleCoffee());
        System.out.println("Cost: $" + coffeeWithMilk.getCost() + "; Description: " + coffeeWithMilk.getDescription());
        
        // Coffee with milk and sugar
        Coffee coffeeWithMilkAndSugar = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println("Cost: $" + coffeeWithMilkAndSugar.getCost() + "; Description: " + coffeeWithMilkAndSugar.getDescription());
        
        // Coffee with milk, sugar, and caramel
        Coffee coffeeWithMilkSugarAndCaramel = new CaramelDecorator(
                new SugarDecorator(
                        new MilkDecorator(
                                new SimpleCoffee())));
        System.out.println("Cost: $" + coffeeWithMilkSugarAndCaramel.getCost() + 
                "; Description: " + coffeeWithMilkSugarAndCaramel.getDescription());
    }
} 