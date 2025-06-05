package com.parmesh.prepare.interviews.microservicespattern.saga;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SagaManager {
    private final ConcurrentMap<String, Saga> activeSagas = new ConcurrentHashMap<>();

    public void startSaga(Saga saga) {
        activeSagas.put(saga.getSagaId(), saga);
        try {
            saga.execute();
        } catch (Exception e) {
            compensateSaga(saga);
            throw e;
        }
    }

    public void compensateSaga(Saga saga) {
        saga.compensate();
        activeSagas.remove(saga.getSagaId());
    }

    public List<Saga> getActiveSagas() {
        return List.copyOf(activeSagas.values());
    }

    public Saga getSaga(String sagaId) {
        return activeSagas.get(sagaId);
    }
} 