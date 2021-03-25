package com.eleflow.swapi.domain.swplanet.command;

import com.eleflow.swapi.domain.swplanet.SWPlanetsPaged;
import com.eleflow.swapi.infrastructure.domain.Command;

public class GetSWPlanetsByNameCommand implements Command<SWPlanetsPaged> {

    private final String name;


    public GetSWPlanetsByNameCommand(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
