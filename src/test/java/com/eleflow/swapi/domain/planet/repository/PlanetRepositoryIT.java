package com.eleflow.swapi.domain.planet.repository;

import com.eleflow.swapi.domain.planet.Planet;
import com.eleflow.swapi.domain.planet.PlanetFilter;
import com.eleflow.swapi.tests.integration.IntegrationTestBase;
import com.eleflow.swapi.tests.integration.SqlScripts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = SqlScripts.PLANET_INSERT, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = SqlScripts.PLANET_DELETE, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PlanetRepositoryIT extends IntegrationTestBase {

    private static final UUID ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-1234567890ab");
    private static final UUID UNKNOWN_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Autowired
    private PlanetRepository planetRepository;


    @Test
    void findAll_whenFilterProducesNullPredicate_returnAllPlanets() {
        var filter = new PlanetFilter(null);
        var planets = planetRepository.findAll(filter);
        assertEquals(3, planets.size());
    }

    @Test
    void findAll_whenFilterHasName_returnPlanetsWhoseNameMatches() {
        var filter = new PlanetFilter("Test");
        var planets = planetRepository.findAll(filter);
        assertEquals(2, planets.size());
        var names = planets.stream().map(Planet::getName).collect(Collectors.toList());
        assertTrue(names.contains("TEST 1"));
        assertTrue(names.contains("Another test"));
    }

    @Test
    void findById_whenExistsPlanetWithGivenId_returnPlanet() {
        var planet = planetRepository.findById(ID);
        assertTrue(planet.isPresent());
        assertEquals(ID, planet.get().getId());
    }

    @Test
    void findById_whenNotExistsPlanetWithGivenId_returnEmpty() {
        var planet = planetRepository.findById(UNKNOWN_ID);
        assertTrue(planet.isEmpty());
    }

    @Test
    void deleteById_whenExistsPlanetWithGivenId_deletePlanet() {
        planetRepository.deleteById(ID);
        assertEquals(2, planetRepository.count());
    }

    @Test
    void deeteById_whenNotExistsPlanetWithGivenId_throwException() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            planetRepository.deleteById(UNKNOWN_ID);
        });
    }
}