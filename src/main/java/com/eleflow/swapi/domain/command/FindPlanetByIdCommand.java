package com.eleflow.swapi.domain.command;

import com.eleflow.swapi.domain.PlanetDTO;
import com.eleflow.swapi.infrastructure.domain.Command;

import java.util.UUID;

public class FindPlanetByIdCommand implements Command<PlanetDTO> {

    private final UUID id;

    public FindPlanetByIdCommand(UUID id) {
        this.id = id;
    }


    public UUID getId() {
        return id;
    }
}
