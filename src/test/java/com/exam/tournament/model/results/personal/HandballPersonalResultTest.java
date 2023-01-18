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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class HandballPersonalResultTest {
    @Mock
    Game game;
    @Mock
    Tournament tournament;
    @Autowired
    private GameDataProvider dataProvider;
    @Test
    void fillPersonalResultTest() {
        prepareTestData();

        HandBallPersonalResult pr = new HandBallPersonalResult();
        List<String> data = dataProvider.getHandBallDataAsFromFile().get(1);
        pr.fillPersonalResult(game, data);
        assertEquals(0, pr.getGoalsMade());
        assertEquals(20, pr.getGoalsReceived());
    }

    @Test
    void calculatePersonalResultTest(){
        prepareTestData();
        HandBallPersonalResult pr = new HandBallPersonalResult();
        List<String> data = dataProvider.getHandBallDataAsFromFile().get(1);
        pr.fillPersonalResult(game, data);
        pr.calculatePersonalResult(game);
        assertEquals(0,pr.getMvpPoints());

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
