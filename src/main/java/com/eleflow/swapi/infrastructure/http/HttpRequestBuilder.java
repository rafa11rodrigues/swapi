package com.eleflow.swapi.infrastructure.http;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {

    private HttpRequest.Builder builder = HttpRequest.newBuilder();
    private Map<String, String> parameters = new HashMap<>();


    public HttpRequest get(String uri) {
        this.uri(uri);
        return builder.GET().build();
    }

    public HttpRequest post(String uri, String body) {
        this.uri(uri);
        return builder.POST(HttpRequest.BodyPublishers.ofString(body)).build();
    }

    public HttpRequestBuilder param(String key, String value) {
        key = URLEncoder.encode(key, StandardCharsets.UTF_8);
        value = URLEncoder.encode(value, StandardCharsets.UTF_8);

        if (this.parameters.containsKey(key)) {
            value = new StringBuilder()
                    .append(this.parameters.get(key))
                    .append(",")
                    .append(value)
                    .toString();
        }
        this.parameters.put(key, value);
        return this;
    }

    public HttpRequestBuilder params(Map<String, String> params) {
        params.forEach(this::param);
        return this;
    }

    public HttpRequestBuilder header(String key, String value) {
        this.builder.header(key, value);
        return this;
    }

    public HttpRequestBuilder headers(Map<String, String> headers) {
        headers.forEach(this::header);
        return this;
    }

    private void uri(String uri) {
        var joinedParameters = this.joinParameters();
        if (!joinedParameters.isBlank()) {
            uri = uri + "?" + joinedParameters;
        }
        builder.uri(URI.create(uri));
    }

    private String joinParameters() {
        var keyValues = new ArrayList<String>();
        this.parameters.forEach((key, value) -> {
            var values = this.parameters.get(key).split(",");
            for (String v: values) {
                keyValues.add(key + "=" + v);
            }
        });
        return String.join("&", keyValues);
    }
}
