package com.parmesh.prepare.interviews.designpattern.creational;

import java.util.HashMap;
import java.util.Map;

/**
 * Prototype Pattern Example
 * Demonstrates object cloning and prototype registry
 */
public class PrototypePattern {
    
    // Prototype interface
    interface Prototype {
        Prototype clone();
    }
    
    // Concrete prototype
    static class Document implements Prototype {
        private String name;
        private String content;
        private String type;
        
        public Document(String name, String content, String type) {
            this.name = name;
            this.content = content;
            this.type = type;
        }
        
        @Override
        public Prototype clone() {
            return new Document(this.name, this.content, this.type);
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public void setContent(String content) {
            this.content = content;
        }
        
        @Override
        public String toString() {
            return "Document{" +
                    "name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
    
    // Prototype Registry
    static class DocumentRegistry {
        private static Map<String, Document> registry = new HashMap<>();
        
        static {
            // Initialize registry with some default documents
            registry.put("report", new Document("Report", "Default report content", "PDF"));
            registry.put("letter", new Document("Letter", "Default letter content", "DOC"));
            registry.put("memo", new Document("Memo", "Default memo content", "TXT"));
        }
        
        public static Document getDocument(String type) {
            Document doc = registry.get(type);
            if (doc != null) {
                return (Document) doc.clone();
            }
            return null;
        }
        
        public static void addDocument(String type, Document doc) {
            registry.put(type, doc);
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Get a report document from registry
        Document report = DocumentRegistry.getDocument("report");
        report.setName("Annual Report 2024");
        report.setContent("This is the annual report content...");
        System.out.println("Report: " + report);
        
        // Get another report (clone)
        Document report2 = DocumentRegistry.getDocument("report");
        report2.setName("Quarterly Report Q1");
        report2.setContent("This is the quarterly report content...");
        System.out.println("Report2: " + report2);
        
        // Get a letter
        Document letter = DocumentRegistry.getDocument("letter");
        letter.setName("Job Application");
        letter.setContent("Dear Hiring Manager...");
        System.out.println("Letter: " + letter);
        
        // Create and register a new document type
        Document presentation = new Document("Presentation", "Default presentation content", "PPT");
        DocumentRegistry.addDocument("presentation", presentation);
        
        // Get the new document type
        Document presentation2 = DocumentRegistry.getDocument("presentation");
        presentation2.setName("Project Overview");
        presentation2.setContent("Project timeline and milestones...");
        System.out.println("Presentation: " + presentation2);
    }
} 