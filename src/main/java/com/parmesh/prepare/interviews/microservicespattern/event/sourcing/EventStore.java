package com.parmesh.prepare.interviews.microservicespattern.event.sourcing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventStore {
    private final Map<String, List<Event>> eventStore = new ConcurrentHashMap<>();

    public void save(Event event) {
        eventStore.computeIfAbsent(event.getAggregateId(), k -> new ArrayList<>())
                .add(event);
    }

    public List<Event> getEvents(String aggregateId) {
        return eventStore.getOrDefault(aggregateId, new ArrayList<>());
    }

    public List<Event> getEvents(String aggregateId, long fromVersion) {
        return eventStore.getOrDefault(aggregateId, new ArrayList<>())
                .stream()
                .filter(event -> event.getVersion() >= fromVersion)
                .toList();
    }

    public long getLatestVersion(String aggregateId) {
        List<Event> events = eventStore.getOrDefault(aggregateId, new ArrayList<>());
        return events.isEmpty() ? 0 : events.get(events.size() - 1).getVersion();
    }
} 