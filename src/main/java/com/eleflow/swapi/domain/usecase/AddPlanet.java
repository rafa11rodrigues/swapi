package com.eleflow.swapi.domain.usecase;

import com.eleflow.swapi.domain.Planet;
import com.eleflow.swapi.domain.PlanetDTO;
import com.eleflow.swapi.domain.command.AddPlanetCommand;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import org.springframework.stereotype.Component;

@Component
public class AddPlanet implements UseCase<AddPlanetCommand, PlanetDTO> {

    @Override
    public PlanetDTO execute(AddPlanetCommand command) {
        var planet = Planet.builder()
                .name(command.getName())
                .climate(command.getClimate())
                .terrain(command.getTerrain())
                .population(1)
                .build();
        return PlanetDTO.of(planet);
    }
}
