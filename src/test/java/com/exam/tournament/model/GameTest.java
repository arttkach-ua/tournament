package com.exam.tournament.model;

import com.exam.tournament.exceptions.TournamentProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameTest {

    @Test
    void addTeam() {
        Game game = new Game();
        Team team1 = Team.builder().name("team1").build();
        Team team2 = Team.builder().name("team2").build();
        game.addTeam(team1);
        game.addTeam(team2);
        game.addTeam(team1);
        assertEquals(2, game.getTeams().size());
    }

    @Test
    void getTeamByNameTeamExists() {
        Game game = new Game();
        Team team1 = Team.builder().name("team1").build();
        Team team2 = Team.builder().name("team2").build();
        game.addTeam(team1);
        game.addTeam(team2);
        Team resultTeam = game.getTeamByName("team1");
        assertEquals(team1, resultTeam);
    }

    @Test
    void getTeamByNameTeamDoesNotExists() {
        Game game = new Game();
        Team team1 = Team.builder().name("team1").build();
        Team team2 = Team.builder().name("team2").build();
        game.addTeam(team1);
        game.addTeam(team2);
        TournamentProcessingException ex = assertThrows(TournamentProcessingException.class,()->game.getTeamByName("team3"));
        assertEquals("Team not found", ex.getMessage());
    }

    @Test
    void getTeamByNameTeamEmptyTeams() {
        Game game = new Game();
        TournamentProcessingException ex = assertThrows(TournamentProcessingException.class,()->game.getTeamByName("team3"));
        assertEquals("Team not found", ex.getMessage());
    }
}