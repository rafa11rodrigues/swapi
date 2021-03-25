package com.eleflow.swapi.domain.planet;

import com.eleflow.swapi.infrastructure.domain.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "PLANET")
public class Planet extends EntityBase {

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "CLIMATE", nullable = false)
    private String climate;

    @Column(name = "TERRAIN", nullable = false)
    private String terrain;

    @Column(name = "POPULATION", nullable = false)
    private String population;


    protected Planet() {}

    public static PlanetName builder() {
        return new Builder();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return name.equals(planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", climate='" + climate + '\'' +
                ", terrain='" + terrain + '\'' +
                ", population=" + population +
                '}';
    }


    public static class Builder implements PlanetName, PlanetClimate,
    PlanetTerrain, PlanetPopulation, PlanetBuilder {
        private Planet planet;

        private Builder() {
            this.planet = new Planet();
        }


        @Override
        public PlanetClimate name(String name) {
            this.planet.name = name;
            return this;
        }

        @Override
        public PlanetTerrain climate(String climate) {
            this.planet.climate = climate;
            return this;
        }

        @Override
        public PlanetPopulation terrain(String terrain) {
            this.planet.terrain = terrain;
            return this;
        }

        @Override
        public PlanetBuilder population(String population) {
            this.planet.population = population;
            return this;
        }

        @Override
        public Planet build() {
            return this.planet;
        }
    }

    public static interface PlanetName {
        PlanetClimate name(String name);
    }

    public static interface PlanetClimate {
        PlanetTerrain climate(String climate);
    }

    public static interface PlanetTerrain {
        PlanetPopulation terrain(String terrain);
    }

    public static interface PlanetPopulation {
        PlanetBuilder population(String population);
    }

    public static interface PlanetBuilder {
        Planet build();
    }
}
