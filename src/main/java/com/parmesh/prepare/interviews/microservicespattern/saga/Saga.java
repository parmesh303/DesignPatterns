package com.parmesh.prepare.interviews.microservicespattern.saga;

import java.util.List;

public interface Saga {
    String getSagaId();
    List<SagaStep> getSteps();
    void execute();
    void compensate();
} 