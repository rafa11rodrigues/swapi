package com.eleflow.swapi.infrastructure.http;

import com.eleflow.swapi.infrastructure.exception.ServerException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.REQUEST_ERROR;

public class HttpRequestSender {

    private final HttpClient httpClient;


    public HttpRequestSender() {
        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
    }

    public HttpResponse<?> send(HttpRequest request) {
        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 400) {
                throw new ServerException(response.body());
            }
            return response;
        } catch (IOException | InterruptedException e) {
            throw new ServerException(REQUEST_ERROR, e);
        }
    }
}
