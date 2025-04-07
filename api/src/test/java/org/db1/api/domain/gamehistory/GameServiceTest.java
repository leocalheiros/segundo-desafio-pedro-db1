package org.db1.api.domain.gamehistory;

import org.assertj.core.api.Assertions;
import org.db1.api.domain.enums.MatchResult;
import org.db1.api.domain.enums.Move;
import org.db1.api.domain.gamehistory.dto.GameResultDTO;
import org.db1.api.domain.gamehistory.dto.GameStartDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class GameServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<GameStartDTO> jacksonTesterGameStart;

    @Autowired
    private JacksonTester<GameResultDTO> jacksonTesterGameResult;

    @MockitoBean
    private GameService gameService;

    @Test
    void play_withInvalidInputs_returnsError400() throws Exception {
        var response = mockMvc.perform(post("/game/play"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void play_ShouldReturnRockWinsWhenOtherIsScissors_returnsStatus200() throws Exception {
        GameStartDTO playerOneWinsPlay = new GameStartDTO("Jogador 1", Move.ROCK, "Jogador 2", Move.SCISSORS);
        GameResultDTO playerOneWinsResult = new GameResultDTO(playerOneWinsPlay, MatchResult.PLAYER_ONE_WINS);
        when(gameService.play(any())).thenReturn(playerOneWinsResult);

        var response = mockMvc.perform(post("/game/play")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTesterGameStart.write(playerOneWinsPlay).getJson()))
                .andReturn().getResponse();
        var expected = jacksonTesterGameResult.write(playerOneWinsResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);

        GameStartDTO playerTwoWinsPlay = new GameStartDTO("Jogador 1", Move.SCISSORS, "Jogador 2", Move.ROCK);
        GameResultDTO playerTwoWinsResult = new GameResultDTO(playerOneWinsPlay, MatchResult.PLAYER_TWO_WINS);
        when(gameService.play(any())).thenReturn(playerTwoWinsResult);

        response = mockMvc.perform(post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterGameStart.write(playerTwoWinsPlay).getJson()))
                .andReturn().getResponse();
        expected = jacksonTesterGameResult.write(playerTwoWinsResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void play_ShouldReturnPaperWinsWhenOtherIsRock_returnsStatus200() throws Exception {
        GameStartDTO playerOneWinsPlay = new GameStartDTO("Jogador 1", Move.PAPER, "Jogador 2", Move.ROCK);
        GameResultDTO playerOneWinsResult = new GameResultDTO(playerOneWinsPlay, MatchResult.PLAYER_ONE_WINS);
        when(gameService.play(any())).thenReturn(playerOneWinsResult);

        var response = mockMvc.perform(post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterGameStart.write(playerOneWinsPlay).getJson()))
                .andReturn().getResponse();
        var expected = jacksonTesterGameResult.write(playerOneWinsResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);

        GameStartDTO playerTwoWinsPlay = new GameStartDTO("Jogador 1", Move.ROCK, "Jogador 2", Move.PAPER);
        GameResultDTO playerTwoWinsResult = new GameResultDTO(playerOneWinsPlay, MatchResult.PLAYER_TWO_WINS);
        when(gameService.play(any())).thenReturn(playerTwoWinsResult);

        response = mockMvc.perform(post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterGameStart.write(playerTwoWinsPlay).getJson()))
                .andReturn().getResponse();
        expected = jacksonTesterGameResult.write(playerTwoWinsResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void play_ShouldReturnScissorsWinsWhenOtherIsPaper_returnsStatus200() throws Exception {
        GameStartDTO playerOneWinsPlay = new GameStartDTO("Jogador 1", Move.SCISSORS, "Jogador 2", Move.PAPER);
        GameResultDTO playerOneWinsResult = new GameResultDTO(playerOneWinsPlay, MatchResult.PLAYER_ONE_WINS);
        when(gameService.play(any())).thenReturn(playerOneWinsResult);

        var response = mockMvc.perform(post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterGameStart.write(playerOneWinsPlay).getJson()))
                .andReturn().getResponse();
        var expected = jacksonTesterGameResult.write(playerOneWinsResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);

        GameStartDTO playerTwoWinsPlay = new GameStartDTO("Jogador 1", Move.PAPER, "Jogador 2", Move.SCISSORS);
        GameResultDTO playerTwoWinsResult = new GameResultDTO(playerOneWinsPlay, MatchResult.PLAYER_TWO_WINS);
        when(gameService.play(any())).thenReturn(playerTwoWinsResult);

        response = mockMvc.perform(post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterGameStart.write(playerTwoWinsPlay).getJson()))
                .andReturn().getResponse();
        expected = jacksonTesterGameResult.write(playerTwoWinsResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);
    }

    @Test
    void play_shouldReturnDraw_returnsStatus200() throws Exception {
        GameStartDTO drawWithRock = new GameStartDTO("Jogador 1", Move.ROCK, "Jogador 2", Move.ROCK);
        GameResultDTO drawWithRockResult = new GameResultDTO(drawWithRock, MatchResult.DRAW);
        when(gameService.play(any())).thenReturn(drawWithRockResult);

        var response = mockMvc.perform(post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterGameStart.write(drawWithRock).getJson()))
                .andReturn().getResponse();
        var expected = jacksonTesterGameResult.write(drawWithRockResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);

        GameStartDTO drawWithPaper = new GameStartDTO("Jogador 1", Move.PAPER, "Jogador 2", Move.PAPER);
        GameResultDTO drawWithPaperResult = new GameResultDTO(drawWithPaper, MatchResult.PLAYER_TWO_WINS);
        when(gameService.play(any())).thenReturn(drawWithPaperResult);

        response = mockMvc.perform(post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterGameStart.write(drawWithPaper).getJson()))
                .andReturn().getResponse();
        expected = jacksonTesterGameResult.write(drawWithPaperResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);

        GameStartDTO drawWithScissors = new GameStartDTO("Jogador 1", Move.SCISSORS, "Jogador 2", Move.SCISSORS);
        GameResultDTO drawWithScissorsResult = new GameResultDTO(drawWithScissors, MatchResult.PLAYER_TWO_WINS);
        when(gameService.play(any())).thenReturn(drawWithScissorsResult);

        response = mockMvc.perform(post("/game/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTesterGameStart.write(drawWithScissors).getJson()))
                .andReturn().getResponse();
        expected = jacksonTesterGameResult.write(drawWithScissorsResult).getJson();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getContentAsString()).isEqualTo(expected);
    }
}