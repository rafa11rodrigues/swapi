package com.eleflow.swapi.domain.planet.usecase;

import com.eleflow.swapi.domain.planet.PlanetDTO;
import com.eleflow.swapi.domain.planet.command.GetPlanetByIdCommand;
import com.eleflow.swapi.domain.planet.repository.PlanetRepository;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import com.eleflow.swapi.infrastructure.exception.NotFoundException;
import org.springframework.stereotype.Component;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.PLANET_NOT_FOUND_BY_ID;

@Component
public class GetPlanetById implements UseCase<GetPlanetByIdCommand, PlanetDTO> {

    private final PlanetRepository planetRepository;

    public GetPlanetById(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }


    @Override
    public PlanetDTO execute(GetPlanetByIdCommand command) {
        var planet = planetRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(PLANET_NOT_FOUND_BY_ID, command.getId()));
        return PlanetDTO.of(planet);
    }
}
