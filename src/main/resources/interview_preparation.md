# Java Backend Developer Interview Preparation Guide
## Focus: Design Patterns & Microservice Patterns

## 1. Design Patterns

### Creational Patterns
1. **Singleton Pattern**
   - Ensures a class has only one instance
   - Provides global access point
   - Common implementations:
     - Eager initialization: Creates instance at class loading time
     ```java
     public class EagerSingleton {
         private static final EagerSingleton instance = new EagerSingleton();
         private EagerSingleton() {}
         public static EagerSingleton getInstance() { return instance; }
     }
     ```
     - Lazy initialization: Creates instance on first request
     ```java
     public class LazySingleton {
         private static LazySingleton instance;
         private LazySingleton() {}
         public static synchronized LazySingleton getInstance() {
             if (instance == null) instance = new LazySingleton();
             return instance;
         }
     }
     ```
     - Double-checked locking: Thread-safe lazy initialization
     ```java
     public class DoubleCheckedSingleton {
         private volatile static DoubleCheckedSingleton instance;
         private DoubleCheckedSingleton() {}
         public static DoubleCheckedSingleton getInstance() {
             if (instance == null) {
                 synchronized (DoubleCheckedSingleton.class) {
                     if (instance == null) instance = new DoubleCheckedSingleton();
                 }
             }
             return instance;
         }
     }
     ```
     - Bill Pugh Singleton: Uses static inner class for lazy loading
     ```java
     public class BillPughSingleton {
         private BillPughSingleton() {}
         private static class SingletonHelper {
             private static final BillPughSingleton INSTANCE = new BillPughSingleton();
         }
         public static BillPughSingleton getInstance() {
             return SingletonHelper.INSTANCE;
         }
     }
     ```
   - Use cases: 
     - Database connections: Managing single connection pool
     - Configuration managers: Centralized configuration access
     - Logger instances: Single logging point
     - Cache managers: Centralized caching

2. **Factory Pattern**
   - Creates objects without exposing creation logic
   - Types:
     - Simple Factory: Single factory class creating different objects
     ```java
     public class PaymentFactory {
         public Payment createPayment(String type) {
             switch(type) {
                 case "CREDIT": return new CreditCardPayment();
                 case "DEBIT": return new DebitCardPayment();
                 default: throw new IllegalArgumentException("Invalid payment type");
             }
         }
     }
     ```
     - Factory Method: Defines interface for object creation, lets subclasses decide
     ```java
     public abstract class PaymentFactory {
         public abstract Payment createPayment();
     }
     public class CreditCardFactory extends PaymentFactory {
         public Payment createPayment() { return new CreditCardPayment(); }
     }
     ```
     - Abstract Factory: Provides interface for creating families of related objects
     ```java
     public interface PaymentFactory {
         Payment createPayment();
         Receipt createReceipt();
     }
     public class CreditCardFactory implements PaymentFactory {
         public Payment createPayment() { return new CreditCardPayment(); }
         public Receipt createReceipt() { return new CreditCardReceipt(); }
     }
     ```
   - Use cases: 
     - Object creation with complex initialization
     - Creating objects based on configuration
     - Implementing dependency injection
     - Creating test doubles (mocks/stubs)

3. **Builder Pattern**
   - Constructs complex objects step by step
   - Separates object construction from representation
   ```java
   public class User {
       private final String name;
       private final String email;
       private final int age;
       private final String address;
       
       private User(Builder builder) {
           this.name = builder.name;
           this.email = builder.email;
           this.age = builder.age;
           this.address = builder.address;
       }
       
       public static class Builder {
           private String name;
           private String email;
           private int age;
           private String address;
           
           public Builder name(String name) {
               this.name = name;
               return this;
           }
           // Other builder methods...
           
           public User build() {
               return new User(this);
           }
       }
   }
   ```
   - Use cases: 
     - Creating complex objects with many optional parameters
     - Building immutable objects
     - Creating objects with validation rules
     - Implementing fluent interfaces

4. **Prototype Pattern**
   - Creates new objects by cloning existing ones
   - Reduces subclassing
   ```java
   public abstract class Shape implements Cloneable {
       private String id;
       protected String type;
       
       abstract void draw();
       
       public Object clone() {
           Object clone = null;
           try {
               clone = super.clone();
           } catch (CloneNotSupportedException e) {
               e.printStackTrace();
           }
           return clone;
       }
   }
   ```
   - Use cases: 
     - Object creation is expensive
     - Creating objects from default values
     - Reducing subclassing
     - Implementing undo/redo functionality

### Structural Patterns
1. **Adapter Pattern**
   - Allows incompatible interfaces to work together
   - Types: 
     - Class adapter: Uses inheritance
     ```java
     public class LegacyRectangle {
         public void drawRectangle() { /* ... */ }
     }
     
     public class RectangleAdapter extends LegacyRectangle implements Shape {
         public void draw() {
             drawRectangle();
         }
     }
     ```
     - Object adapter: Uses composition
     ```java
     public class RectangleAdapter implements Shape {
         private LegacyRectangle legacyRectangle;
         
         public RectangleAdapter(LegacyRectangle legacyRectangle) {
             this.legacyRectangle = legacyRectangle;
         }
         
         public void draw() {
             legacyRectangle.drawRectangle();
         }
     }
     ```
   - Use cases: 
     - Integrating legacy systems
     - Working with third-party libraries
     - Making incompatible interfaces work together
     - Implementing multiple interfaces

2. **Decorator Pattern**
   - Adds responsibilities to objects dynamically
   - Alternative to inheritance
   ```java
   public interface Coffee {
       double getCost();
       String getDescription();
   }
   
   public class SimpleCoffee implements Coffee {
       public double getCost() { return 1.0; }
       public String getDescription() { return "Simple coffee"; }
   }
   
   public class MilkDecorator implements Coffee {
       private Coffee coffee;
       
       public MilkDecorator(Coffee coffee) {
           this.coffee = coffee;
       }
       
       public double getCost() {
           return coffee.getCost() + 0.5;
       }
       
       public String getDescription() {
           return coffee.getDescription() + " with milk";
       }
   }
   ```
   - Use cases: 
     - Adding features to existing classes
     - Implementing cross-cutting concerns
     - Creating flexible class hierarchies
     - Adding responsibilities at runtime

3. **Proxy Pattern**
   - Provides a surrogate object
   - Controls access to another object
   - Types: 
     - Virtual: Lazy loading
     - Remote: Remote resource access
     - Protection: Access control
     - Smart: Additional functionality
   ```java
   public interface Image {
       void display();
   }
   
   public class RealImage implements Image {
       private String filename;
       
       public RealImage(String filename) {
           this.filename = filename;
           loadFromDisk();
       }
       
       private void loadFromDisk() {
           System.out.println("Loading " + filename);
       }
       
       public void display() {
           System.out.println("Displaying " + filename);
       }
   }
   
   public class ProxyImage implements Image {
       private RealImage realImage;
       private String filename;
       
       public ProxyImage(String filename) {
           this.filename = filename;
       }
       
       public void display() {
           if (realImage == null) {
               realImage = new RealImage(filename);
           }
           realImage.display();
       }
   }
   ```
   - Use cases: 
     - Lazy loading
     - Access control
     - Logging
     - Caching

4. **Facade Pattern**
   - Provides simplified interface to complex subsystem
   - Reduces coupling
   ```java
   public class ComputerFacade {
       private CPU cpu;
       private Memory memory;
       private HardDrive hardDrive;
       
       public ComputerFacade() {
           this.cpu = new CPU();
           this.memory = new Memory();
           this.hardDrive = new HardDrive();
       }
       
       public void start() {
           cpu.freeze();
           memory.load();
           cpu.jump();
           cpu.execute();
       }
   }
   ```
   - Use cases: 
     - Complex system integration
     - Simplifying complex APIs
     - Providing unified interface
     - Reducing dependencies

### Behavioral Patterns
1. **Observer Pattern**
   - Defines one-to-many dependency between objects
   - Publisher-Subscriber model
   ```java
   public interface Observer {
       void update(String message);
   }
   
   public class Subject {
       private List<Observer> observers = new ArrayList<>();
       
       public void attach(Observer observer) {
           observers.add(observer);
       }
       
       public void notifyObservers(String message) {
           for (Observer observer : observers) {
               observer.update(message);
           }
       }
   }
   ```
   - Use cases: 
     - Event handling
     - UI updates
     - Message broadcasting
     - State change notifications

2. **Strategy Pattern**
   - Defines family of algorithms
   - Encapsulates each algorithm
   - Makes algorithms interchangeable
   ```java
   public interface PaymentStrategy {
       void pay(int amount);
   }
   
   public class CreditCardStrategy implements PaymentStrategy {
       public void pay(int amount) {
           System.out.println("Paid " + amount + " using credit card");
       }
   }
   
   public class PayPalStrategy implements PaymentStrategy {
       public void pay(int amount) {
           System.out.println("Paid " + amount + " using PayPal");
       }
   }
   ```
   - Use cases: 
     - Different sorting algorithms
     - Payment methods
     - Compression algorithms
     - Validation strategies

3. **Command Pattern**
   - Encapsulates request as object
   - Parameterizes clients with different requests
   ```java
   public interface Command {
       void execute();
       void undo();
   }
   
   public class LightOnCommand implements Command {
       private Light light;
       
       public LightOnCommand(Light light) {
           this.light = light;
       }
       
       public void execute() {
           light.turnOn();
       }
       
       public void undo() {
           light.turnOff();
       }
   }
   ```
   - Use cases: 
     - Undo/Redo operations
     - Job scheduling
     - Macro recording
     - Transaction management

4. **Template Method Pattern**
   - Defines skeleton of algorithm
   - Lets subclasses override specific steps
   ```java
   public abstract class Game {
       abstract void initialize();
       abstract void startPlay();
       abstract void endPlay();
       
       public final void play() {
           initialize();
           startPlay();
           endPlay();
       }
   }
   ```
   - Use cases: 
     - Framework development
     - Algorithm customization
     - Code reuse
     - Standardizing workflow

## 2. Microservice Patterns

### Communication Patterns
1. **Synchronous Communication**
   - REST APIs
     - Stateless communication
     - Resource-based endpoints
     - HTTP methods (GET, POST, PUT, DELETE)
     - Status codes and error handling
   - gRPC
     - High-performance RPC framework
     - Protocol Buffers
     - Bi-directional streaming
     - Strong typing
   - GraphQL
     - Flexible data querying
     - Single endpoint
     - Client-specified data requirements
     - Real-time subscriptions
   - Best practices and trade-offs
     - Latency vs. throughput
     - Error handling
     - Circuit breaking
     - Retry mechanisms

2. **Asynchronous Communication**
   - Message Queues (RabbitMQ, Kafka)
     - Decoupled communication
     - Message persistence
     - Load balancing
     - Message routing
   - Event-driven architecture
     - Event sourcing
     - Event store
     - Event replay
     - Event versioning
   - CQRS (Command Query Responsibility Segregation)
     - Separate read and write models
     - Optimized data access
     - Scalability
     - Eventual consistency

### Service Discovery & Registration
1. **Service Registry**
   - Eureka
     - Service registration
     - Health monitoring
     - Load balancing
     - Service discovery
   - Consul
     - Service mesh
     - Key-value store
     - Health checking
     - Multi-datacenter support
   - Zookeeper
     - Distributed coordination
     - Configuration management
     - Leader election
     - Distributed locking

2. **Client-side Service Discovery**
   - Load balancing
     - Round-robin
     - Weighted round-robin
     - Least connections
     - Response time
   - Health checks
     - Active health checks
     - Passive health checks
     - Custom health indicators
   - Circuit breaking
     - Failure detection
     - Fallback mechanisms
     - Recovery strategies

### API Gateway Pattern
1. **Gateway Responsibilities**
   - Routing
     - Path-based routing
     - Header-based routing
     - Load balancing
     - Service aggregation
   - Authentication/Authorization
     - JWT validation
     - OAuth2 integration
     - Role-based access control
     - API key management
   - Rate limiting
     - Token bucket algorithm
     - Leaky bucket algorithm
     - IP-based limiting
     - User-based limiting
   - Request/Response transformation
     - Protocol translation
     - Data format conversion
     - Header manipulation
     - Response caching
   - API documentation
     - OpenAPI/Swagger
     - API versioning
     - Documentation generation
     - Interactive testing

2. **Implementation**
   - Spring Cloud Gateway
     - Route definitions
     - Filter chains
     - Global filters
     - Custom filters
   - Netflix Zuul
     - Dynamic routing
     - Request filtering
     - Response filtering
     - Error handling
   - Kong
     - Plugin system
     - Database-less mode
     - Declarative configuration
     - High performance

### Circuit Breaker Pattern
1. **Implementation**
   - Resilience4j
     - Circuit breaker
     - Rate limiter
     - Retry mechanism
     - Bulkhead
   - Hystrix
     - Fallback mechanisms
     - Thread isolation
     - Request caching
     - Request collapsing
   - Circuit breaker states
     - Closed (normal operation)
     - Open (failing)
     - Half-open (testing)
   - Fallback mechanisms
     - Default values
     - Cached responses
     - Alternative services
     - Error responses

### Bulkhead Pattern
1. **Thread Pool Isolation**
   - Resource isolation
     - Thread pools
     - Connection pools
     - Memory allocation
     - CPU allocation
   - Failure containment
     - Service isolation
     - Resource limits
     - Timeout handling
     - Error boundaries
   - Implementation strategies
     - Semaphore-based
     - Thread pool-based
     - Process-based
     - Container-based

### Caching Patterns
1. **Cache-Aside**
   - Read-through
     - Automatic cache population
     - Cache miss handling
     - Cache consistency
     - Cache invalidation
   - Write-through
     - Synchronous updates
     - Cache consistency
     - Write performance
     - Error handling
   - Write-behind
     - Asynchronous updates
     - Batch processing
     - Performance optimization
     - Data consistency
   - Cache invalidation strategies
     - Time-based
     - Event-based
     - Manual invalidation
     - Version-based

### Database Patterns
1. **Database per Service**
   - Advantages and challenges
     - Data isolation
     - Independent scaling
     - Technology freedom
     - Operational complexity
   - Data consistency
     - Eventual consistency
     - Strong consistency
     - Causal consistency
     - Read-your-writes consistency
   - Saga pattern
     - Compensating transactions
     - Event sourcing
     - Choreography
     - Orchestration
   - Two-phase commit
     - Prepare phase
     - Commit phase
     - Rollback mechanism
     - Coordinator role

2. **Shared Database**
   - When to use
     - Legacy systems
     - Small applications
     - Tight coupling
     - Performance requirements
   - Challenges and solutions
     - Schema management
     - Data access control
     - Performance optimization
     - Backup and recovery

### Deployment Patterns
1. **Containerization**
   - Docker
     - Image creation
     - Container management
     - Networking
     - Volume management
   - Kubernetes
     - Pod management
     - Service discovery
     - Load balancing
     - Auto-scaling
   - Service mesh (Istio)
     - Traffic management
     - Security
     - Observability
     - Policy enforcement

2. **CI/CD**
   - Pipeline design
     - Build automation
     - Test automation
     - Deployment automation
     - Environment management
   - Automated testing
     - Unit testing
     - Integration testing
     - End-to-end testing
     - Performance testing
   - Deployment strategies
     - Blue-green deployment
     - Canary deployment
     - Rolling update
     - Feature flags

## 3. Best Practices

### Microservices Best Practices
1. **Service Design**
   - Bounded contexts
     - Domain boundaries
     - Context mapping
     - Ubiquitous language
     - Anti-corruption layer
   - Domain-driven design
     - Entities
     - Value objects
     - Aggregates
     - Domain events
   - Service granularity
     - Size considerations
     - Responsibility boundaries
     - Communication overhead
     - Deployment complexity
   - API versioning
     - URI versioning
     - Header versioning
     - Content negotiation
     - Backward compatibility

2. **Security**
   - OAuth2/JWT
     - Token types
     - Token validation
     - Token storage
     - Token refresh
   - API security
     - Rate limiting
     - Input validation
     - Output encoding
     - Error handling
   - Service-to-service authentication
     - Mutual TLS
     - API keys
     - Service accounts
     - Trust boundaries

3. **Monitoring & Observability**
   - Distributed tracing
     - Trace context
     - Span management
     - Correlation IDs
     - Sampling strategies
   - Logging
     - Log levels
     - Log aggregation
     - Log analysis
     - Log retention
   - Metrics
     - Business metrics
     - Technical metrics
     - Resource metrics
     - Custom metrics
   - Health checks
     - Liveness probes
     - Readiness probes
     - Startup probes
     - Custom health indicators

### Design Pattern Best Practices
1. **When to Use Patterns**
   - Problem identification
     - Code smells
     - Design smells
     - Anti-patterns
     - Refactoring opportunities
   - Pattern selection
     - Pattern catalog
     - Pattern relationships
     - Pattern combinations
     - Pattern alternatives
   - Anti-patterns to avoid
     - God object
     - Golden hammer
     - Premature optimization
     - Architecture astronaut

2. **Implementation Guidelines**
   - Code organization
     - Package structure
     - Class responsibilities
     - Interface design
     - Dependency management
   - Testing strategies
     - Unit testing
     - Integration testing
     - Mocking
     - Test coverage
   - Documentation
     - Code comments
     - API documentation
     - Architecture documentation
     - Pattern usage documentation

## 4. Interview Questions

### Design Pattern Questions
1. Explain the difference between Factory and Abstract Factory patterns
   - Factory: Creates single type of object
   - Abstract Factory: Creates families of related objects
   - Implementation differences
   - Use case differences

2. When would you use the Builder pattern over constructor?
   - Many optional parameters
   - Immutable objects
   - Object validation
   - Fluent interface requirements

3. How does the Observer pattern help in microservices?
   - Event-driven communication
   - Loose coupling
   - Real-time updates
   - Scalability

4. Explain the Strategy pattern with a real-world example
   - Payment processing
   - Sorting algorithms
   - Compression strategies
   - Validation rules

5. When would you use the Decorator pattern?
   - Adding responsibilities dynamically
   - Cross-cutting concerns
   - Multiple combinations
   - Runtime flexibility

### Microservice Questions
1. How do you handle distributed transactions in microservices?
   - Saga pattern
   - Eventual consistency
   - Compensation transactions
   - Two-phase commit

2. Explain the Circuit Breaker pattern and its implementation
   - Failure detection
   - State management
   - Fallback mechanisms
   - Recovery strategies

3. How do you ensure data consistency across microservices?
   - Eventual consistency
   - CQRS
   - Event sourcing
   - Saga pattern

4. What are the challenges of service discovery?
   - Dynamic scaling
   - Health monitoring
   - Load balancing
   - Failure handling

5. How do you handle API versioning in microservices?
   - URI versioning
   - Header versioning
   - Content negotiation
   - Backward compatibility

## 5. Resources

### Books
1. "Design Patterns: Elements of Reusable Object-Oriented Software" by Gang of Four
   - Classic patterns
   - Implementation details
   - Use cases
   - Best practices

2. "Building Microservices" by Sam Newman
   - Microservice architecture
   - Design principles
   - Implementation strategies
   - Operational concerns

3. "Domain-Driven Design" by Eric Evans
   - Domain modeling
   - Bounded contexts
   - Strategic design
   - Tactical design

### Online Resources
1. Spring.io documentation
   - Spring Boot
   - Spring Cloud
   - Spring Security
   - Spring Data

2. Martin Fowler's blog
   - Architecture patterns
   - Design patterns
   - Refactoring
   - Best practices

3. Microservices.io
   - Pattern catalog
   - Implementation examples
   - Best practices
   - Case studies

4. Refactoring.guru
   - Design patterns
   - Anti-patterns
   - Code smells
   - Refactoring techniques

### Practice Projects
1. Build a microservice architecture with different patterns
   - Service design
   - Communication patterns
   - Data management
   - Deployment

2. Implement various design patterns in a sample application
   - Pattern selection
   - Implementation
   - Testing
   - Documentation

3. Create a service mesh implementation
   - Traffic management
   - Security
   - Observability
   - Policy enforcement

4. Design and implement an event-driven system
   - Event sourcing
   - CQRS
   - Message queues
   - Event processing 