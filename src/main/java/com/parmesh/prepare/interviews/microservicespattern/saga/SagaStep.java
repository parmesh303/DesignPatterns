package com.parmesh.prepare.interviews.microservicespattern.saga;

public interface SagaStep {
    void execute();
    void compensate();
    String getStepId();
    boolean isCompleted();
    boolean isCompensated();
} 