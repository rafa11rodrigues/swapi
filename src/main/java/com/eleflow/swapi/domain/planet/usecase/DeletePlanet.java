package com.eleflow.swapi.domain.planet.usecase;

import com.eleflow.swapi.domain.planet.command.DeletePlanetCommand;
import com.eleflow.swapi.domain.planet.repository.PlanetRepository;
import com.eleflow.swapi.infrastructure.domain.UseCase;
import com.eleflow.swapi.infrastructure.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.PLANET_NOT_FOUND_BY_ID;

@Component
public class DeletePlanet implements UseCase<DeletePlanetCommand, Void> {

    private final PlanetRepository planetRepository;

    public DeletePlanet(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }


    @Override
    public Void execute(DeletePlanetCommand command) {
        try{
            planetRepository.deleteById(command.getId());
            planetRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(PLANET_NOT_FOUND_BY_ID, command.getId());
        }

        return null;
    }
}
