package com.dzone.mancala.game.controller;

import com.dzone.mancala.game.exceptions.ResourceNotFoundException;
import com.dzone.mancala.game.model.KalahaGame;
import com.dzone.mancala.game.service.KalahaGameService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@RunWith(SpringRunner.class)
@DirtiesContext
public class KalahaGameControllerTests {

    @MockBean
    KalahaGameService mancalaGameService;

    private final Resource jsonOfJustCreatedKalahaGame = new ClassPathResource("mancala-creation.json");
    private final Resource jsonOfKalahaGameSowPit2JustAfterCreation = new ClassPathResource("mancala-sow-2.json");

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    private String asJson(Resource resource) {
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }

    @Test
    public void testGameCreation() throws Exception {

        KalahaGame expectedGame = new KalahaGame("5d414dcd24e4990006e7c900", 6);

        Mockito.when(this.mancalaGameService.createGame(6))
                .thenReturn(expectedGame);

        this.mockMvc.perform(post("/games"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(asJson(jsonOfJustCreatedKalahaGame), false))
                .andReturn();
    }

    @Test
    public void testSowPitIndex2() throws Exception {

        KalahaGame expectedGame = new KalahaGame("5d414dcd24e4990006e7c900", 6);

        Mockito.when(mancalaGameService.loadGame("5d414dcd24e4990006e7c900"))
                .thenReturn(expectedGame);

        this.mockMvc.perform(put("/games/5d414dcd24e4990006e7c900/pits/2"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(asJson(jsonOfKalahaGameSowPit2JustAfterCreation)))
                .andReturn();
    }

    @Test
    public void testSowingTheGameOfInvalidId() throws Exception {

        Mockito.when(mancalaGameService.loadGame("5d414dcd24e4990006e7c902"))
                .thenThrow(new ResourceNotFoundException("Game id 5d414dcd24e4990006e7c902 not found!"));

        this.mockMvc.perform(put("/games/5d414dcd24e4990006e7c902/pits/2"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("@.message").value("Game id 5d414dcd24e4990006e7c902 not found!"))
                .andReturn();
    }

    @Test
    public void testSowingTheGameAtInvalidPitIndex() throws Exception {
        this.mockMvc.perform(put("/games/5d414dcd24e4990006e7c900/pits/7"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("@.message").value("Invalid pit Index!. It should be between 1..6 or 8..13"))
                .andReturn();
    }
}
