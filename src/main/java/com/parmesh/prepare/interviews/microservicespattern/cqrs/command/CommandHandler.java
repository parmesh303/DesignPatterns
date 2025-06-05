package com.parmesh.prepare.interviews.microservicespattern.cqrs.command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
} 