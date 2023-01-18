package com.exam.tournament.model.results.personal;

import com.exam.tournament.dataProviders.GameDataProvider;
import com.exam.tournament.model.Game;
import com.exam.tournament.model.Player;
import com.exam.tournament.model.Team;
import com.exam.tournament.model.Tournament;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class BasketBallPersonalResultTest {
    @Autowired
    private GameDataProvider dataProvider;
    @Mock
    Game game;
    @Mock
    Tournament tournament;


    @Test
    void fillPersonalResultTest() {
        prepareTestData();

        BasketBallPersonalResult pr = new BasketBallPersonalResult();
        List<String> data = dataProvider.getBasketBallDataAsFromFile().get(1);
        pr.fillPersonalResult(game, data);
        assertEquals(10, pr.getPointsScored());
        assertEquals(2, pr.getRebounds());
        assertEquals(7, pr.getAssists());
    }

    @Test
    void calculatePersonalResultTest(){
        prepareTestData();
        BasketBallPersonalResult pr = new BasketBallPersonalResult();
        List<String> data = dataProvider.getBasketBallDataAsFromFile().get(1);
        pr.fillPersonalResult(game, data);
        pr.calculatePersonalResult(game);
        assertEquals(29,pr.getMvpPoints());

    }
    private void prepareTestData(){
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        Player player = new Player("nick1");

        when(game.getWinner())
                .thenReturn(teamB);
        when(game.getTournament())
                .thenReturn(tournament);
        when(tournament.getTournamentMember(anyString()))
                .thenReturn(player);
    }
}