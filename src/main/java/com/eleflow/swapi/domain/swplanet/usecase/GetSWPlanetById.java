package com.eleflow.swapi.domain.swplanet.usecase;

import com.eleflow.swapi.domain.swplanet.command.GetSWPlanetByIdCommand;
import com.eleflow.swapi.domain.swplanet.SWPlanet;
import com.eleflow.swapi.domain.swplanet.service.SWApiService;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import org.springframework.stereotype.Component;

@Component
public class GetSWPlanetById implements UseCase<GetSWPlanetByIdCommand, SWPlanet> {

    private final SWApiService swApiService;

    public GetSWPlanetById(SWApiService swApiService) {
        this.swApiService = swApiService;
    }


    @Override
    public SWPlanet execute(GetSWPlanetByIdCommand command) {
        return swApiService.get(command.getId());
    }
}
