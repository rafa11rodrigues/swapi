package com.eleflow.swapi.domain.swplanet.command;

import com.eleflow.swapi.domain.swplanet.SWPlanet;
import com.eleflow.swapi.domain.swplanet.SWPlanetsPaged;
import com.eleflow.swapi.infrastructure.domain.Command;

import java.util.List;

public class GetSWPlanetsPagedCommand implements Command<SWPlanetsPaged> {

    private final Integer page;

    public GetSWPlanetsPagedCommand(Integer page) {
        this.page = page;
    }


    public Integer getPage() {
        return page;
    }
}
