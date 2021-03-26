package com.eleflow.swapi.tests.data_builder;

import com.eleflow.swapi.domain.swplanet.SWPlanet;
import com.eleflow.swapi.domain.swplanet.SWPlanetsPaged;

import java.util.List;

import static org.springframework.test.util.ReflectionTestUtils.setField;

public class SWPlanetTestDataBuilder {

    public static final String NAME = "Foo Planet";
    public static final String POPULATION = "10";


    public static SWPlanet newSWPlanet() {
        var swPlanet = new SWPlanet();
        setField(swPlanet, "name", NAME);
        setField(swPlanet, "population", POPULATION);
        return swPlanet;
    }

    public static SWPlanetsPaged newSWPlanetsPaged() {
        var swPlanetsPaged = new SWPlanetsPaged();
        setField(swPlanetsPaged, "count", 1);
        setField(swPlanetsPaged, "results", List.of(newSWPlanet()));
        return swPlanetsPaged;
    }

    public static SWPlanetsPaged emptyPaged() {
        var swPlanets = new SWPlanetsPaged();
        setField(swPlanets, "count", 0);
        setField(swPlanets, "results", List.of());
        return swPlanets;
    }
}
