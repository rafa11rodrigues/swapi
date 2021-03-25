package com.eleflow.swapi.domain.swplanet.usecase;

import com.eleflow.swapi.domain.swplanet.SWPlanetsPaged;
import com.eleflow.swapi.domain.swplanet.command.GetSWPlanetsPagedCommand;
import com.eleflow.swapi.domain.swplanet.service.SWApiService;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import org.springframework.stereotype.Component;

@Component
public class GetSWPlanetsPaged implements UseCase<GetSWPlanetsPagedCommand, SWPlanetsPaged> {

    private final SWApiService swApiService;

    public GetSWPlanetsPaged(SWApiService swApiService) {
        this.swApiService = swApiService;
    }


    @Override
    public SWPlanetsPaged execute(GetSWPlanetsPagedCommand command) {
        return swApiService.getAll(command.getPage());
    }
}
