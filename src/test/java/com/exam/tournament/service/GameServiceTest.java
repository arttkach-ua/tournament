package com.exam.tournament.service;

import com.exam.tournament.dataProviders.FileDataProvider;
import com.exam.tournament.dataProviders.GameDataProvider;
import com.exam.tournament.exceptions.TournamentProcessingException;
import com.exam.tournament.model.Game;
import com.exam.tournament.model.GameTypes;
import com.exam.tournament.model.Team;
import com.exam.tournament.model.Tournament;
import com.exam.tournament.util.messages.ErrorMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GameServiceTest {
    @Autowired
    private GameService gameService;

    @Autowired
    private FileDataProvider fileDataProvider;
    @Autowired
    private GameDataProvider gameDataProvider;

    @Test
    void getGameTypeByNameHandBallTest() {
        GameTypes result = gameService.getGameTypeByName("HANDBALL");
        assertEquals(GameTypes.HANDBALL, result);

        GameTypes result1 = gameService.getGameTypeByName("handball");
        assertEquals(GameTypes.HANDBALL, result1);
    }

    @Test
    void getGameTypeByNameBasketBallTest() {
        GameTypes result = gameService.getGameTypeByName("BASKETBALL");
        assertEquals(GameTypes.BASKETBALL, result);

        GameTypes result1 = gameService.getGameTypeByName("basketball");
        assertEquals(GameTypes.BASKETBALL, result1);

        assertEquals(GameTypes.BASKETBALL, gameService.getGameTypeByName("basKETball"));
    }

    @Test
    void getGameTypeByNameNULLTest() {
        Exception ex = assertThrows(TournamentProcessingException.class, ()->gameService.getGameTypeByName(null));
        assertEquals(ErrorMessages.NULL_GAME_NAME, ex.getMessage());
    }

    @Test
    void getGameTypeByNameNotSupportedGameTest() {
        String game = "football";
        Exception ex = assertThrows(TournamentProcessingException.class, ()->gameService.getGameTypeByName(game));
        assertEquals(String.format(ErrorMessages.GAME_NOT_SUPPORTED, game), ex.getMessage());
    }

    @Test
    void getTeamScoresTest() {
        Map<Team, Integer> result = gameService.getTeamScores(gameDataProvider.getBasketBallDataAsFromFile());
        assertEquals(25, result.get(new Team("Team A")));
        assertEquals(32, result.get(new Team("Team B")));
    }

    @Test
    void setTeamsTest() {
        Game game = new Game();
        gameService.setTeams(game, gameDataProvider.getBasketBallDataAsFromFile());
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        assertEquals(game.getWinner(), teamB);
        assertEquals(2,game.getTeams().size());
    }

    @Test
    void setGameTypeNormalCaseTest() {
        Game game = new Game();
        List<String> testData = List.of("BASKETBALL");
        gameService.setGameType(game, testData);
        assertEquals(GameTypes.BASKETBALL, game.getType());
    }
    @Test
    void setGameTypeToBigListCaseTest() {
        Game game = new Game();
        List<String> testData = List.of("BASKETBALL", "Some wrong data");
        TournamentProcessingException ex = assertThrows(TournamentProcessingException.class, ()->gameService.setGameType(game, testData));
        assertEquals("Wrong file data", ex.getMessage());
    }
    @Test
    void setGameTypeWrongTextCaseTest() {
        Game game = new Game();
        List<String> testData = List.of("BASSSKETBALL");
        TournamentProcessingException ex = assertThrows(TournamentProcessingException.class, ()->gameService.setGameType(game, testData));
        assertEquals("Game BASSSKETBALL is not supported. Please contact your administrator", ex.getMessage());
    }

    @Test
    void addGameToTournamentTest(){
        Tournament tournament = new Tournament();
        gameService.addGameToTournament(fileDataProvider.getBasketBallTestFile(), tournament);
        assertEquals(6,tournament.getMembers().size());
        assertEquals(1, tournament.getGames().size());
    }
}