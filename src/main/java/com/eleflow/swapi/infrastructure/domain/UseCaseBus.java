package com.eleflow.swapi.infrastructure.domain;

import com.eleflow.swapi.infrastructure.exception.ServerException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.NO_HANDLER_DEFINED;

@Component
public class UseCaseBus {

    private final ApplicationContext applicationContext;

    public UseCaseBus(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public <T extends Command<R>, R> R execute(T command) {
        final UseCase<T, R> useCase = getUseCaseForCommand(command.getClass());
        if (useCase == null) {
            throw new ServerException(NO_HANDLER_DEFINED);
        }
        return useCase.execute(command);
    }

    private <T extends Command<R>, R> UseCase<T, R> getUseCaseForCommand(Class<T> commandClass) {
        UseCase<T, R> useCase = null;

        var beans = applicationContext.getBeansOfType(UseCase.class);
        for (var name: beans.keySet()) {
            var bean = beans.get(name);
            var beanCommandType = ((ParameterizedType) bean.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
            if (beanCommandType.equals(commandClass)) {
                useCase = (UseCase<T, R>) bean;
            }
        }
        return useCase;
    }
}
