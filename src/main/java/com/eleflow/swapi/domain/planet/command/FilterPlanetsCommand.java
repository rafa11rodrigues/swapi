package com.eleflow.swapi.domain.planet.command;

import com.eleflow.swapi.domain.planet.PlanetDTO;
import com.eleflow.swapi.domain.planet.PlanetFilter;
import com.eleflow.swapi.infrastructure.domain.Command;

import java.util.List;

public class FilterPlanetsCommand implements Command<List<PlanetDTO>> {

    private final PlanetFilter filter;

    public FilterPlanetsCommand(PlanetFilter filter) {
        this.filter = filter;
    }

    public PlanetFilter getFilter() {
        return filter;
    }
}
