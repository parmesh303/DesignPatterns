package com.parmesh.prepare.interviews.designpattern.behavioral;

import java.util.Stack;

/**
 * Command Pattern Example
 * Demonstrates command encapsulation and undo operations
 */
public class CommandPattern {
    
    // Command Interface
    interface Command {
        void execute();
        void undo();
    }
    
    // Receiver
    static class TextEditor {
        private StringBuilder text;
        
        public TextEditor() {
            this.text = new StringBuilder();
        }
        
        public void insert(String str, int position) {
            text.insert(position, str);
        }
        
        public void delete(int start, int end) {
            text.delete(start, end);
        }
        
        public String getText() {
            return text.toString();
        }
    }
    
    // Concrete Commands
    static class InsertCommand implements Command {
        private TextEditor editor;
        private String text;
        private int position;
        
        public InsertCommand(TextEditor editor, String text, int position) {
            this.editor = editor;
            this.text = text;
            this.position = position;
        }
        
        @Override
        public void execute() {
            editor.insert(text, position);
        }
        
        @Override
        public void undo() {
            editor.delete(position, position + text.length());
        }
    }
    
    static class DeleteCommand implements Command {
        private TextEditor editor;
        private int start;
        private int end;
        private String deletedText;
        
        public DeleteCommand(TextEditor editor, int start, int end) {
            this.editor = editor;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public void execute() {
            deletedText = editor.getText().substring(start, end);
            editor.delete(start, end);
        }
        
        @Override
        public void undo() {
            editor.insert(deletedText, start);
        }
    }
    
    // Invoker
    static class TextEditorInvoker {
        private Stack<Command> undoStack;
        private Stack<Command> redoStack;
        
        public TextEditorInvoker() {
            this.undoStack = new Stack<>();
            this.redoStack = new Stack<>();
        }
        
        public void executeCommand(Command command) {
            command.execute();
            undoStack.push(command);
            redoStack.clear();
        }
        
        public void undo() {
            if (!undoStack.isEmpty()) {
                Command command = undoStack.pop();
                command.undo();
                redoStack.push(command);
            }
        }
        
        public void redo() {
            if (!redoStack.isEmpty()) {
                Command command = redoStack.pop();
                command.execute();
                undoStack.push(command);
            }
        }
    }
    
    // Another example: Remote Control
    interface Device {
        void turnOn();
        void turnOff();
        void volumeUp();
        void volumeDown();
    }
    
    static class Television implements Device {
        private boolean isOn;
        private int volume;
        
        public Television() {
            this.isOn = false;
            this.volume = 50;
        }
        
        @Override
        public void turnOn() {
            isOn = true;
            System.out.println("TV is turned on");
        }
        
        @Override
        public void turnOff() {
            isOn = false;
            System.out.println("TV is turned off");
        }
        
        @Override
        public void volumeUp() {
            if (isOn && volume < 100) {
                volume++;
                System.out.println("Volume increased to " + volume);
            }
        }
        
        @Override
        public void volumeDown() {
            if (isOn && volume > 0) {
                volume--;
                System.out.println("Volume decreased to " + volume);
            }
        }
    }
    
    static class TVCommand implements Command {
        private Device device;
        private String command;
        
        public TVCommand(Device device, String command) {
            this.device = device;
            this.command = command;
        }
        
        @Override
        public void execute() {
            switch (command) {
                case "on":
                    device.turnOn();
                    break;
                case "off":
                    device.turnOff();
                    break;
                case "up":
                    device.volumeUp();
                    break;
                case "down":
                    device.volumeDown();
                    break;
            }
        }
        
        @Override
        public void undo() {
            switch (command) {
                case "on":
                    device.turnOff();
                    break;
                case "off":
                    device.turnOn();
                    break;
                case "up":
                    device.volumeDown();
                    break;
                case "down":
                    device.volumeUp();
                    break;
            }
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        // Text Editor Example
        System.out.println("=== Text Editor Example ===");
        TextEditor editor = new TextEditor();
        TextEditorInvoker invoker = new TextEditorInvoker();
        
        // Insert some text
        Command insertCommand = new InsertCommand(editor, "Hello, ", 0);
        invoker.executeCommand(insertCommand);
        System.out.println("After insert: " + editor.getText());
        
        // Insert more text
        insertCommand = new InsertCommand(editor, "World!", 7);
        invoker.executeCommand(insertCommand);
        System.out.println("After second insert: " + editor.getText());
        
        // Undo last command
        invoker.undo();
        System.out.println("After undo: " + editor.getText());
        
        // Redo last command
        invoker.redo();
        System.out.println("After redo: " + editor.getText());
        
        // TV Remote Example
        System.out.println("\n=== TV Remote Example ===");
        Television tv = new Television();
        TextEditorInvoker remote = new TextEditorInvoker();
        
        // Turn on TV
        Command turnOnCommand = new TVCommand(tv, "on");
        remote.executeCommand(turnOnCommand);
        
        // Increase volume
        Command volumeUpCommand = new TVCommand(tv, "up");
        remote.executeCommand(volumeUpCommand);
        remote.executeCommand(volumeUpCommand);
        
        // Undo volume increase
        remote.undo();
        
        // Turn off TV
        Command turnOffCommand = new TVCommand(tv, "off");
        remote.executeCommand(turnOffCommand);
        
        // Undo turn off
        remote.undo();
    }
} 