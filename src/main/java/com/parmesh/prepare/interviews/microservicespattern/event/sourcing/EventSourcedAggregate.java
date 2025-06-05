package com.parmesh.prepare.interviews.microservicespattern.event.sourcing;

import java.util.ArrayList;
import java.util.List;

public abstract class EventSourcedAggregate {
    private final String id;
    private long version;
    private final List<Event> uncommittedEvents = new ArrayList<>();

    protected EventSourcedAggregate(String id) {
        this.id = id;
        this.version = 0;
    }

    protected void apply(Event event) {
        uncommittedEvents.add(event);
        version++;
    }

    public void loadFromHistory(List<Event> history) {
        history.forEach(this::handle);
        version = history.size();
    }

    protected abstract void handle(Event event);

    public List<Event> getUncommittedEvents() {
        return new ArrayList<>(uncommittedEvents);
    }

    public void clearUncommittedEvents() {
        uncommittedEvents.clear();
    }

    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }
} 