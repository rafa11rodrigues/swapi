package com.eleflow.swapi.domain.planet.usecase;

import com.eleflow.swapi.domain.planet.Planet;
import com.eleflow.swapi.domain.planet.PlanetDTO;
import com.eleflow.swapi.domain.planet.command.FindPlanetByIdCommand;
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
                .population("12")
                .build();
        return PlanetDTO.of(planet);
    }
}
