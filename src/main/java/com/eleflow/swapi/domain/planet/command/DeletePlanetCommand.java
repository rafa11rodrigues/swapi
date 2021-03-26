package com.eleflow.swapi.domain.planet.command;

import com.eleflow.swapi.infrastructure.domain.Command;

import java.util.UUID;

public class DeletePlanetCommand implements Command<Void> {

    private final UUID id;

    public DeletePlanetCommand(UUID id) {
        this.id = id;
    }


    public UUID getId() {
        return id;
    }
}
