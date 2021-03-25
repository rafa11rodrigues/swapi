package com.eleflow.swapi.domain.planet.usecase;

import com.eleflow.swapi.domain.planet.PlanetDTO;
import com.eleflow.swapi.domain.planet.command.FilterPlanetsCommand;
import com.eleflow.swapi.domain.planet.repository.PlanetRepository;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterPlanets implements UseCase<FilterPlanetsCommand, List<PlanetDTO>> {

    private final PlanetRepository planetRepository;

    public FilterPlanets(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }


    @Override
    public List<PlanetDTO> execute(FilterPlanetsCommand command) {
        return planetRepository.findAll(command.getFilter())
                .stream().map(PlanetDTO::of)
                .collect(Collectors.toList());
    }
}
