package com.eleflow.swapi.domain.command;

import com.eleflow.swapi.domain.PlanetDTO;
import com.eleflow.swapi.infrastructure.domain.Command;

public class AddPlanetCommand implements Command<PlanetDTO> {

    private final String name;
    private final String climate;
    private final String terrain;

    public AddPlanetCommand(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
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
}
