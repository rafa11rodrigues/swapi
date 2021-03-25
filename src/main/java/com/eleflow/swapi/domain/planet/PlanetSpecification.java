package com.eleflow.swapi.domain.planet;

import com.querydsl.core.types.dsl.BooleanExpression;

public class PlanetSpecification {

    public static BooleanExpression byName(String name) {
        return QPlanet.planet.name.containsIgnoreCase(name);
    }
}
