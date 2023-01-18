package com.exam.tournament.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TournamentTest {

    @Test
    void getTournamentMember() {
        Tournament tournament = new Tournament();
        Player player1 = tournament.getTournamentMember("player 1");
        Player player2 = tournament.getTournamentMember("player 2");
        Player player3 = tournament.getTournamentMember("player 3");
        Player player4 = tournament.getTournamentMember("player 1");
        assertEquals(3,tournament.getMembers().size());
        assertEquals("player 2", player2.getNickName());
        assertEquals(player1, player4);
    }
}