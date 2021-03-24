package com.eleflow.swapi.infrastructure.domain;

public interface UseCase<T extends Command<R>, R> {

    R execute(T command);
}
