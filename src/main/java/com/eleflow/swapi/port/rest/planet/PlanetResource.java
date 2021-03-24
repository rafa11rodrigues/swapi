package com.eleflow.swapi.port.rest.planet;

import com.eleflow.swapi.domain.PlanetDTO;
import com.eleflow.swapi.domain.command.AddPlanetCommand;
import com.eleflow.swapi.domain.command.FindPlanetByIdCommand;
import com.eleflow.swapi.domain.command.GetFromSWApiCommand;
import com.eleflow.swapi.infrastructure.domain.UseCaseBus;
import com.eleflow.swapi.port.rest.planet.request.AddPlanetRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/planets")
public class PlanetResource {

    private final UseCaseBus useCaseBus;

    public PlanetResource(UseCaseBus useCaseBus) {
        this.useCaseBus = useCaseBus;
    }


    @GetMapping("/{id}")
    public PlanetDTO findById(@PathVariable UUID id) {
        var command = new FindPlanetByIdCommand(id);
        return useCaseBus.execute(command);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanetDTO addPlanet(@RequestBody @Valid AddPlanetRequest requestBody) {
        var command = new AddPlanetCommand(
                requestBody.getName(),
                requestBody.getClimate(),
                requestBody.getTerrain()
        );
        return useCaseBus.execute(command);
    }

    @GetMapping("/sw")
    public String getFromSwApi() {
        return useCaseBus.execute(new GetFromSWApiCommand());
    }
}
