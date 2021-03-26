package com.eleflow.swapi.domain.planet.usecase;

import com.eleflow.swapi.domain.planet.Planet;
import com.eleflow.swapi.domain.planet.PlanetDTO;
import com.eleflow.swapi.domain.planet.command.AddPlanetCommand;
import com.eleflow.swapi.domain.planet.repository.PlanetRepository;
import com.eleflow.swapi.domain.swplanet.SWPlanet;
import com.eleflow.swapi.domain.swplanet.service.SWApiService;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import com.eleflow.swapi.infrastructure.exception.BusinessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.*;

@Component
public class AddPlanet implements UseCase<AddPlanetCommand, PlanetDTO> {

    private final PlanetRepository planetRepository;
    private final SWApiService swApiService;

    public AddPlanet(PlanetRepository planetRepository, SWApiService swApiService) {
        this.planetRepository = planetRepository;
        this.swApiService = swApiService;
    }


    @Override
    public PlanetDTO execute(AddPlanetCommand command) {
        var swPlanet = getMatchingSWPlanet(command.getName());
        checkPlanetNameMatch(command.getName(), swPlanet.getName());
        var planet = buildAndSave(command, swPlanet.getPopulation());
        return PlanetDTO.of(planet);
    }

    private SWPlanet getMatchingSWPlanet(String planetName) {
        var swPlanets = swApiService.getByName(planetName);
        if (swPlanets.getCount() == 0) {
            throw new BusinessException(PLANET_NOT_FOUND_BY_NAME, planetName);
        }
        if (swPlanets.getCount() > 1) {
            throw new BusinessException(PLANET_MULTIPLE_WITH_NAME, planetName);
        }
        return swPlanets.getResults()
                .get(0);
    }

    private void checkPlanetNameMatch(String planetName, String swPlanetName) {
        if (!planetName.equalsIgnoreCase(swPlanetName)) {
            throw new BusinessException(PLANET_NAME_NOT_MATCHES);
        }
    }

    private Planet buildAndSave(AddPlanetCommand command, String population) {
        var planet = Planet.builder()
                .name(command.getName())
                .climate(command.getClimate())
                .terrain(command.getTerrain())
                .population(population)
                .build();

        try {
            return planetRepository.saveAndFlush(planet);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(PLANET_NAME_ALREADY_EXISTS, planet.getName());
        }
    }
}
