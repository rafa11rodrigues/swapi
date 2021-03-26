package com.eleflow.swapi.port.rest.planet;

import com.eleflow.swapi.domain.planet.command.AddPlanetCommand;
import com.eleflow.swapi.domain.planet.command.GetPlanetByIdCommand;
import com.eleflow.swapi.infrastructure.JsonProcessor;
import com.eleflow.swapi.infrastructure.domain.UseCaseBus;
import com.eleflow.swapi.infrastructure.exception.NotFoundException;
import com.eleflow.swapi.port.rest.planet.request.AddPlanetRequest;
import com.eleflow.swapi.tests.ResourceTestBase;
import com.eleflow.swapi.tests.data_builder.PlanetTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.UUID;

import static com.eleflow.swapi.infrastructure.exception.ExceptionMessagesDictionary.PLANET_NOT_FOUND_BY_ID;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanetResource.class)
class PlanetResourceTest extends ResourceTestBase {

    @MockBean
    private UseCaseBus useCaseBus;

    private static final String URI = "/planets";


    @Test
    void getById_whenPlanetIsFoundSuccessfully_returnPlanet() throws Exception {
        var id = UUID.randomUUID();
        var dto = PlanetTestDataBuilder.newPlanetDTO();
        when(useCaseBus.execute(any(GetPlanetByIdCommand.class)))
                .thenReturn(dto);

        mockMvc.perform(get(String.format(URI + "/%s", id)))
                .andExpect(status().isOk())
                .andExpect(content().string(JsonProcessor.toJson(dto)));
    }

    @Test
    void getById_whenCouldNotFindPlanet_returnError() throws Exception {
        var id = UUID.randomUUID();
        var exception = new NotFoundException(PLANET_NOT_FOUND_BY_ID, id);

        when(useCaseBus.execute(any(GetPlanetByIdCommand.class)))
                .thenThrow(exception);

        mockMvc.perform(get(String.format(URI + "/%s", id)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("status", equalTo(404)))
                .andExpect(jsonPath("messages", hasSize(1)))
                .andExpect(jsonPath("messages[0]", equalTo(String.format("No planet with id '%s' was found", id))));
    }

    @Test
    void addPlanet_returnCreatedPlanet() throws Exception {
        var request = PlanetTestDataBuilder.newAddPlanetRequest();
        var dto = PlanetTestDataBuilder.newPlanetDTO();

        when(useCaseBus.execute(any(AddPlanetCommand.class)))
                .thenReturn(dto);

        mockMvc.perform(buildPostRequest(URI, request))
                .andExpect(status().isCreated())
                .andExpect(content().string(JsonProcessor.toJson(dto)));
    }

    @Test
    void addPlanet_whenRequestHasInvalidFields_returnError() throws Exception {
        var request = new AddPlanetRequest();

        mockMvc.perform(buildPostRequest(URI, request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status", equalTo(400)))
                .andExpect(jsonPath("messages", hasSize(3)))
                .andExpect(jsonPath("messages", containsInAnyOrder(
                        "Planet name is required",
                        "Planet climate is required",
                        "Planet terrain is required"
                )));
    }
}