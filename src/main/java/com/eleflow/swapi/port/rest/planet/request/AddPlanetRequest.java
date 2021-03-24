package com.eleflow.swapi.port.rest.planet.request;

import javax.validation.constraints.NotBlank;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.*;

public class AddPlanetRequest {

    @NotBlank(message = PLANET_NAME_REQUIRED)
    private String name;

    @NotBlank(message = PLANET_CLIMATE_REQUIRED)
    private String climate;

    @NotBlank(message = PLANET_TERRAIN_REQUIRED)
    private String terrain;


    public String getName() {
        return name;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }
}
