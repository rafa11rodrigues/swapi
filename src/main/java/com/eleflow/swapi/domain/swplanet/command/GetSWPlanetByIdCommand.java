package com.eleflow.swapi.domain.swplanet.command;

import com.eleflow.swapi.domain.swplanet.SWPlanet;
import com.eleflow.swapi.infrastructure.domain.Command;

public class GetSWPlanetByIdCommand implements Command<SWPlanet> {

    private final Long id;


    public GetSWPlanetByIdCommand(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
