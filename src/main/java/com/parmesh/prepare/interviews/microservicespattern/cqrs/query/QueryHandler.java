package com.parmesh.prepare.interviews.microservicespattern.cqrs.query;

public interface QueryHandler<T extends Query, R> {
    R handle(T query);
} 