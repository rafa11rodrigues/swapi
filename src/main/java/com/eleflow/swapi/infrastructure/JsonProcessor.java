package com.eleflow.swapi.infrastructure;

import com.eleflow.swapi.infrastructure.exception.ServerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.*;

public class JsonProcessor {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


    public static <T> Object parse(String value, Class<T> type) {
        JsonNode tree = parseAsTree(value);

        if (tree.isTextual()) {
            return tree.asText();
        }
        if (tree.isArray()) {
            return parseAsList(value, type);
        }
        return parseAsObject(value, type);
    }

    private static JsonNode parseAsTree(String value) {
        try {
            return MAPPER.readTree(value);
        } catch (JsonProcessingException e) {
            throw new ServerException(JSON_PARSE_ERROR, e, value);
        }
    }

    private static <T> T parseAsObject(String value, Class<T> type) {
        try {
            return MAPPER.readValue(value, type);
        } catch (JsonProcessingException e) {
            throw new ServerException(JSON_OBJECT_ERROR, e, value);
        }
    }

    private static <T> List<T> parseAsList(String value, Class<T> type) {
        var listType = MAPPER.getTypeFactory()
                .constructCollectionType(List.class, type);

        try {
            return MAPPER.readValue(value, listType);
        } catch (JsonProcessingException e) {
            throw new ServerException(JSON_LIST_ERROR, e, value);
        }
    }
}
