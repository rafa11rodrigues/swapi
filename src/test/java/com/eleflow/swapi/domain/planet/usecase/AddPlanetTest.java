package com.eleflow.swapi.domain.planet.usecase;

import com.eleflow.swapi.domain.planet.Planet;
import com.eleflow.swapi.domain.planet.command.AddPlanetCommand;
import com.eleflow.swapi.domain.planet.repository.PlanetRepository;
import com.eleflow.swapi.domain.swplanet.SWPlanet;
import com.eleflow.swapi.domain.swplanet.SWPlanetsPaged;
import com.eleflow.swapi.domain.swplanet.service.SWApiService;
import com.eleflow.swapi.infrastructure.exception.BusinessException;
import com.eleflow.swapi.tests.data_builder.PlanetTestDataBuilder;
import com.eleflow.swapi.tests.data_builder.SWPlanetTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(SpringExtension.class)
class AddPlanetTest {

    @InjectMocks
    private AddPlanet addPlanet;

    @Mock
    private PlanetRepository planetRepository;

    @Mock
    private SWApiService swApiService;

    @Captor
    private ArgumentCaptor<Planet> planetCaptor;

    private AddPlanetCommand command;

    @BeforeEach
    void setup() {
        command = PlanetTestDataBuilder.newAddPlanetCommand();
    }


    @Test
    void execute_returnCreatedPlanet() {
        var swPlanets = SWPlanetTestDataBuilder.newSWPlanetsPaged();
        var planet = PlanetTestDataBuilder.newPlanet();

        when(swApiService.getByName(command.getName()))
                .thenReturn(swPlanets);
        when(planetRepository.saveAndFlush(planetCaptor.capture()))
                .thenReturn(planet);

        addPlanet.execute(command);
        verify(swApiService).getByName(command.getName());
        var builtPlanet = planetCaptor.getValue();
        verify(planetRepository).saveAndFlush(builtPlanet);
    }

    @Test
    void execute_whenNoSWPlanetWithGivenNameIsFound_throwException() {
        var swPlanets = SWPlanetTestDataBuilder.emptyPaged();

        when(swApiService.getByName(command.getName()))
                .thenReturn(swPlanets);

        var exception = assertThrows(BusinessException.class, () -> {
            addPlanet.execute(command);
        });
        assertEquals("No planet with name '" + command.getName() + "' was found",
                exception.getMessage());
    }

    @Test
    void execute_whenMoreThanOneSWPlanetWasFound_throwException() {
        var command = PlanetTestDataBuilder.newAddPlanetCommand();
        var swPlanets = SWPlanetTestDataBuilder.newSWPlanetsPaged();
        setField(swPlanets, "count", 2);

        when(swApiService.getByName(command.getName()))
                .thenReturn(swPlanets);

        var exception = assertThrows(BusinessException.class, () -> {
            addPlanet.execute(command);
        });
        assertEquals("Multiple planets were found with name '" + command.getName() + "'",
                exception.getMessage());
    }

    @Test
    void execute_whenPlanetNameNotMatchesSWPlanetName_throwException() {
        var command = PlanetTestDataBuilder.newAddPlanetCommand();
        setField(command, "name", "Another name");
        var swPlanets = SWPlanetTestDataBuilder.newSWPlanetsPaged();

        when(swApiService.getByName(command.getName()))
                .thenReturn(swPlanets);

        var exception = assertThrows(BusinessException.class, () -> {
            addPlanet.execute(command);
        });
        assertEquals("Planet name must match exactly with SWPlanet name",
                exception.getMessage());
    }

    @Test
    void execute_whenCouldNotGetResponseFromSWApi_forwardException() {
        var serviceException = new RuntimeException("foo");

        when(swApiService.getByName(command.getName()))
                .thenThrow(serviceException);

        var exception = assertThrows(serviceException.getClass(), () -> {
            addPlanet.execute(command);
        });
        assertEquals(serviceException.getMessage(), exception.getMessage());
    }
}