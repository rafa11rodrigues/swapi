package com.eleflow.swapi.domain.usecase;

import com.eleflow.swapi.domain.Planet;
import com.eleflow.swapi.domain.PlanetDTO;
import com.eleflow.swapi.domain.command.FindPlanetByIdCommand;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import org.springframework.stereotype.Component;

@Component
public class FindPlanetById implements UseCase<FindPlanetByIdCommand, PlanetDTO> {

    @Override
    public PlanetDTO execute(FindPlanetByIdCommand command) {
        var planet = Planet.builder()
                .name("Foo")
                .climate("árido")
                .terrain("terreno")
                .population(12)
                .build();
        return PlanetDTO.of(planet);
    }
}
