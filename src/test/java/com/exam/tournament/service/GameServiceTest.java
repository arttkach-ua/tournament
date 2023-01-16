package com.exam.tournament.service;

import com.exam.tournament.exceptions.TournamentProcessingException;
import com.exam.tournament.model.GameTypes;
import com.exam.tournament.util.messages.ErrorMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GameServiceTest {
    @Autowired
    private GameService gameService;

    @Test
    void getGameTypeByNameHandBall() {
        GameTypes result = gameService.getGameTypeByName("HANDBALL");
        assertEquals(GameTypes.HANDBALL, result);

        GameTypes result1 = gameService.getGameTypeByName("handball");
        assertEquals(GameTypes.HANDBALL, result1);
    }

    @Test
    void getGameTypeByNameBasketBall() {
        GameTypes result = gameService.getGameTypeByName("BASKETBALL");
        assertEquals(GameTypes.BASKETBALL, result);

        GameTypes result1 = gameService.getGameTypeByName("basketball");
        assertEquals(GameTypes.BASKETBALL, result1);

        assertEquals(GameTypes.BASKETBALL, gameService.getGameTypeByName("basKETball"));
    }

    @Test
    void getGameTypeByNameNULL() {
        Exception ex = assertThrows(TournamentProcessingException.class, ()->gameService.getGameTypeByName(null));
        assertEquals(ErrorMessages.NULL_GAME_NAME, ex.getMessage());
    }

    @Test
    void getGameTypeByNameNotSupportedGame() {
        String game = "football";
        Exception ex = assertThrows(TournamentProcessingException.class, ()->gameService.getGameTypeByName(game));
        assertEquals(String.format(ErrorMessages.GAME_NOT_SUPPORTED, game), ex.getMessage());
    }
}