package com.eleflow.swapi.tests.data_builder;

import com.eleflow.swapi.domain.planet.Planet;
import com.eleflow.swapi.domain.planet.PlanetDTO;
import com.eleflow.swapi.domain.planet.command.AddPlanetCommand;
import com.eleflow.swapi.port.rest.planet.request.AddPlanetRequest;

import static org.springframework.test.util.ReflectionTestUtils.setField;

public class PlanetTestDataBuilder {

    public static final String NAME = "Foo Planet";
    public static final String CLIMATE = "climate";
    public static final String TERRAIN = "terrain";
    public static final String POPULATION = "unknown";


    public static Planet newPlanet() {
        return Planet.builder()
                .name(NAME)
                .climate(CLIMATE)
                .terrain(TERRAIN)
                .population(POPULATION)
                .build();
    }

    public static AddPlanetCommand newAddPlanetCommand() {
        return new AddPlanetCommand(NAME, CLIMATE, TERRAIN);
    }

    public static AddPlanetRequest newAddPlanetRequest() {
        var request = new AddPlanetRequest();
        setField(request, "name", NAME);
        setField(request, "climate", CLIMATE);
        setField(request, "terrain", TERRAIN);
        return request;
    }

    public static PlanetDTO newPlanetDTO() {
        var planet = newPlanet();
        return PlanetDTO.of(planet);
    }
}
