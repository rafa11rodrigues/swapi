package com.eleflow.swapi.domain;

import java.util.UUID;

public class PlanetDTO {

    private UUID id;
    private String name;
    private String climate;
    private String terrain;
    private Integer population;


    private PlanetDTO() {}

    public static PlanetDTO of(Planet planet) {
        var dto = new PlanetDTO();
        dto.id = planet.getId();
        dto.name = planet.getName();
        dto.climate = planet.getClimate();
        dto.terrain = planet.getTerrain();
        dto.population = planet.getPopulation();
        return dto;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public Integer getPopulation() {
        return population;
    }
}
