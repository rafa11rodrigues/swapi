package com.eleflow.swapi.infrastructure.exception;

public interface ExceptionMessagesDictionary {

    String NO_HANDLER_DEFINED = "no.handler.defined";
    String REQUEST_ERROR = "request.error";
    String JSON_PARSE_ERROR = "json.parse.error";
    String JSON_OBJECT_ERROR = "json.object.error";
    String JSON_LIST_ERROR = "json.list.error";
    String JSON_NOT_OBJECT_ERROR = "json.not_object.error";
    String JSON_NOT_LIST_ERROR = "json.not_list.error";
    String OBJECT_TO_JSON_ERROR = "object.to_json.error";


    String PLANET_NAME_REQUIRED = "planet.name.required";
    String PLANET_CLIMATE_REQUIRED = "planet.climate.required";
    String PLANET_TERRAIN_REQUIRED = "planet.terrain.required";
    String PLANET_NAME_ALREADY_EXISTS = "planet.name.already_exists";
    String PLANET_NAME_NOT_MATCHES = "planet.name.not_matches";

    String PLANET_NOT_FOUND_BY_NAME = "planet.not_found.by_name";
    String PLANET_NOT_FOUND_BY_ID = "planet.not_found.by_id";
    String PLANET_MULTIPLE_WITH_NAME = "planet.multiple_with_name";
}
