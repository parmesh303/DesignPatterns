package com.parmesh.prepare.interviews.microservicespattern.event.sourcing;

import java.time.Instant;
import java.util.UUID;

public abstract class Event {
    private final UUID eventId;
    private final Instant timestamp;
    private final String eventType;
    private final String aggregateId;
    private final long version;

    protected Event(String aggregateId, long version) {
        this.eventId = UUID.randomUUID();
        this.timestamp = Instant.now();
        this.eventType = this.getClass().getSimpleName();
        this.aggregateId = aggregateId;
        this.version = version;
    }

    public UUID getEventId() {
        return eventId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public long getVersion() {
        return version;
    }
} 