package com.eleflow.swapi.port.rest.planet;

import com.eleflow.swapi.domain.planet.PlanetDTO;
import com.eleflow.swapi.domain.planet.PlanetFilter;
import com.eleflow.swapi.domain.planet.command.AddPlanetCommand;
import com.eleflow.swapi.domain.planet.command.FilterPlanetsCommand;
import com.eleflow.swapi.domain.planet.command.FindPlanetByIdCommand;
import com.eleflow.swapi.infrastructure.domain.UseCaseBus;
import com.eleflow.swapi.port.rest.planet.request.AddPlanetRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Transactional
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

    @GetMapping
    public List<PlanetDTO> filter(@RequestParam(required = false) String name) {
        var filter = new PlanetFilter(name);
        return useCaseBus.execute(new FilterPlanetsCommand(filter));
    }
}
