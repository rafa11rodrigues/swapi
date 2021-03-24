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
        var returnType = (Class<R>)
                ((ParameterizedType) (command.getClass().getGenericInterfaces()[0]))
                        .getActualTypeArguments()[0];

        String[] beanNames = applicationContext.getBeanNamesForType(
                ResolvableType.forClassWithGenerics(UseCase.class, command.getClass(), returnType)
        );

        if (beanNames.length == 0) {
            throw new ServerException(NO_HANDLER_DEFINED);
        }

        UseCase<T, R> useCase = (UseCase<T, R>) applicationContext.getBean(beanNames[0]);
        return useCase.execute(command);
    }
}
