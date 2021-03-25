package com.eleflow.swapi.domain.swplanet.service;

import com.eleflow.swapi.domain.swplanet.SWPlanet;
import com.eleflow.swapi.domain.swplanet.SWPlanetsPaged;
import com.eleflow.swapi.infrastructure.http.HttpRequestBuilder;
import com.eleflow.swapi.infrastructure.http.HttpRequestSender;
import com.eleflow.swapi.infrastructure.http.HttpResponseExtractor;
import org.springframework.stereotype.Service;

@Service
public class SWApiService {

    private final HttpRequestSender http = new HttpRequestSender();
    private final String uri = "https://swapi.dev/api/planets";



    public SWPlanet get(Long id) {
        var request = new HttpRequestBuilder()
                .get(uri + "/" + id);
        var response = http.send(request);
        return new HttpResponseExtractor(response).asObject(SWPlanet.class);
    }

    public SWPlanetsPaged getAll(Integer page) {
        var requestBuilder = new HttpRequestBuilder();
        if (page != null) {
            requestBuilder.param("page", page.toString());
        }
        var request = requestBuilder.get(uri);
        var response = http.send(request);
        return new HttpResponseExtractor(response).asObject(SWPlanetsPaged.class);
    }

    public SWPlanetsPaged getByName(String name) {
        var request = new HttpRequestBuilder()
                .param("search", name)
                .get(uri);
        var response = http.send(request);
        return new HttpResponseExtractor(response).asObject(SWPlanetsPaged.class);
    }
}
