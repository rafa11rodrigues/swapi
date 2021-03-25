package com.eleflow.swapi.domain.planet;

import java.util.UUID;

public class PlanetDTO {

    private UUID id;
    private String name;
    private String climate;
    private String terrain;
    private String population;


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

    public String getPopulation() {
        return population;
    }


    @Override
    public String toString() {
        return "PlanetDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
