package com.eleflow.swapi.domain.planet.command;

import com.eleflow.swapi.domain.planet.PlanetDTO;
import com.eleflow.swapi.infrastructure.domain.Command;

import java.util.UUID;

public class GetPlanetByIdCommand implements Command<PlanetDTO> {

    private final UUID id;

    public GetPlanetByIdCommand(UUID id) {
        this.id = id;
    }


    public UUID getId() {
        return id;
    }
}
