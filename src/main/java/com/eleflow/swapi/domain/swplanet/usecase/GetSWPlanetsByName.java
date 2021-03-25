package com.eleflow.swapi.domain.swplanet.usecase;

import com.eleflow.swapi.domain.swplanet.SWPlanetsPaged;
import com.eleflow.swapi.domain.swplanet.command.GetSWPlanetsByNameCommand;
import com.eleflow.swapi.domain.swplanet.service.SWApiService;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import org.springframework.stereotype.Component;

@Component
public class GetSWPlanetsByName implements UseCase<GetSWPlanetsByNameCommand, SWPlanetsPaged> {

    private final SWApiService swApiService;

    public GetSWPlanetsByName(SWApiService swApiService) {
        this.swApiService = swApiService;
    }


    @Override
    public SWPlanetsPaged execute(GetSWPlanetsByNameCommand command) {
        return swApiService.getByName(command.getName());
    }
}
