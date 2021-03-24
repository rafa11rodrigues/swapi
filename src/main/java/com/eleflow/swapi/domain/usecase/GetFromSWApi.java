package com.eleflow.swapi.domain.usecase;

import com.eleflow.swapi.domain.command.GetFromSWApiCommand;
import com.eleflow.swapi.domain.service.SWApiService;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import org.springframework.stereotype.Component;

@Component
public class GetFromSWApi implements UseCase<GetFromSWApiCommand, String> {

    private final SWApiService swApiService;

    public GetFromSWApi(SWApiService swApiService) {
        this.swApiService = swApiService;
    }


    @Override
    public String execute(GetFromSWApiCommand command) {
        return swApiService.get();
    }
}
