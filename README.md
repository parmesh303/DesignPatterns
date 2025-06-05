# Design Patterns & Microservice Patterns

This repository contains implementations of both traditional design patterns and microservice patterns in Java.

## 🎯 Design Patterns

### Creational Patterns
- **Singleton**: Ensure single instance of a class
- **Factory Method**: Create objects without specifying exact class
- **Abstract Factory**: Create families of related objects
- **Builder**: Construct complex objects step by step
- **Prototype**: Create new objects by cloning existing ones

### Structural Patterns
- **Adapter**: Make incompatible interfaces work together
- **Bridge**: Separate abstraction from implementation
- **Composite**: Compose objects into tree structures
- **Decorator**: Add responsibilities to objects dynamically
- **Facade**: Provide simplified interface to complex subsystem
- **Flyweight**: Share objects to reduce memory usage
- **Proxy**: Control access to another object

### Behavioral Patterns
- **Chain of Responsibility**: Pass request along chain of handlers
- **Command**: Encapsulate request as an object
- **Iterator**: Access elements of collection sequentially
- **Mediator**: Define object communication
- **Memento**: Capture and restore object state
- **Observer**: Define one-to-many dependency
- **State**: Allow object to alter behavior when state changes
- **Strategy**: Define family of algorithms
- **Template Method**: Define skeleton of algorithm
- **Visitor**: Add operations to objects without modifying them

## 🚀 Microservice Patterns

### API Patterns
- **API Gateway**: Single entry point for all clients
- **Backend for Frontend**: Custom API for each client type
- **Aggregator**: Combine data from multiple services

### Resilience Patterns
- **Circuit Breaker**: Prevent cascading failures
- **Bulkhead**: Isolate components to prevent cascading failures
- **Retry**: Automatically retry failed operations
- **Timeout**: Prevent long-running operations

### Data Patterns
- **CQRS**: Separate read and write operations
- **Event Sourcing**: Store all changes as events
- **Saga**: Manage distributed transactions
- **Database per Service**: Each service has its own database

### Deployment Patterns
- **Service Mesh**: Handle service-to-service communication
- **Sidecar**: Extend service functionality
- **Ambassador**: Proxy remote service access

## 🛠️ Technology Stack

- Java 17
- Spring Boot 3.2.3
- Spring Cloud 2023.0.0
- Resilience4j 2.1.0
- Axon Framework 4.9.1

## 📋 Prerequisites

- JDK 17+
- Maven 3.6+
- IDE (IntelliJ IDEA recommended)

## 🚀 Quick Start

```bash
# Clone repository
git clone https://github.com/yourusername/patterns.git

# Build project
mvn clean install

# Run tests
mvn test
```

## 📁 Project Structure

```
src/main/java/com/parmesh/prepare/interviews/
├── designpatterns/
│   ├── creational/
│   ├── structural/
│   └── behavioral/
└── microservices/
    ├── api/
    ├── resilience/
    ├── data/
    └── deployment/
```

## 📚 Documentation

- Design Patterns: [Design Patterns Documentation](docs/design-patterns.md)
- Microservice Patterns: [Microservice Patterns Documentation](docs/microservice-patterns.md)
- API Documentation: [API Docs](docs/api.md)

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 📝 License

This project is licensed under the MIT License - see [LICENSE](LICENSE) file for details.

## 👥 Author

- **Parmesh Pawar** - *Initial work* - [GitHub](https://github.com/parmesh303)

## 🔄 Updates

### Latest Updates
- Added Creational Design Patterns
- Implemented Microservice Resilience Patterns
- Added Structural Design Patterns
- Implemented CQRS Pattern

### Roadmap
- [ ] Add more Design Patterns
- [ ] Implement Service Mesh Pattern
- [ ] Add more examples
- [ ] Enhance documentation
