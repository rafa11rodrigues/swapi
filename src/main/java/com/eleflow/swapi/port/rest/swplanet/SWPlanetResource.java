package com.eleflow.swapi.port.rest.swplanet;

import com.eleflow.swapi.domain.swplanet.SWPlanet;
import com.eleflow.swapi.domain.swplanet.SWPlanetsPaged;
import com.eleflow.swapi.domain.swplanet.command.GetSWPlanetsByNameCommand;
import com.eleflow.swapi.domain.swplanet.command.GetSWPlanetsPagedCommand;
import com.eleflow.swapi.domain.swplanet.command.GetSWPlanetByIdCommand;
import com.eleflow.swapi.infrastructure.domain.UseCaseBus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swplanets")
public class SWPlanetResource {

    private final UseCaseBus useCaseBus;

    public SWPlanetResource(UseCaseBus useCaseBus) {
        this.useCaseBus = useCaseBus;
    }


    @GetMapping("/{id}")
    public SWPlanet get(@PathVariable Long id) {
        return useCaseBus.execute(new GetSWPlanetByIdCommand(id));
    }

    @GetMapping
    public SWPlanetsPaged getPaged(@RequestParam(required = false) Integer page) {
        return useCaseBus.execute(new GetSWPlanetsPagedCommand(page));
    }

    @GetMapping(params = {"name"})
    public SWPlanetsPaged getByName(@RequestParam String name) {
        return useCaseBus.execute(new GetSWPlanetsByNameCommand(name));
    }
}
