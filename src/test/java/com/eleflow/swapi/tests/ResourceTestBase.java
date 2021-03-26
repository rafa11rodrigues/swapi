package com.eleflow.swapi.tests;

import com.eleflow.swapi.infrastructure.JsonProcessor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
public class ResourceTestBase {

    @Autowired
    protected MockMvc mockMvc;


    protected MockHttpServletRequestBuilder buildPostRequest(String uri, String body) {
        return post(uri)
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .content(body);
    }

    protected MockHttpServletRequestBuilder buildPostRequest(String uri, Object body) {
        return buildPostRequest(uri, JsonProcessor.toJson(body));
    }
}
