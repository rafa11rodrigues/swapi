package com.eleflow.swapi.infrastructure.http;

import com.eleflow.swapi.infrastructure.JsonProcessor;
import com.eleflow.swapi.infrastructure.exception.ServerException;

import java.net.http.HttpResponse;
import java.util.List;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.JSON_NOT_LIST_ERROR;
import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.JSON_NOT_OBJECT_ERROR;

public class HttpResponseExtractor {

    private HttpResponse<?> response;

    public HttpResponseExtractor(HttpResponse<?> response) {
        this.response = response;
    }

    public String asString() {
        return ((HttpResponse<String>) response)
                .body();
    }

    public <T> T asObject(Class<T> objectType) {
        var body = asString();
        try {
            return (T) JsonProcessor.parse(body, objectType);
        } catch (ClassCastException e) {
            throw new ServerException(JSON_NOT_OBJECT_ERROR, e, body);
        }
    }

    public <T>List<T> asList(Class<T> objectType) {
        var body = asString();

        try {
            return (List<T>) JsonProcessor.parse(body, objectType);
        } catch (ClassCastException e) {
            throw new ServerException(JSON_NOT_LIST_ERROR, e, body);
        }
    }
}
