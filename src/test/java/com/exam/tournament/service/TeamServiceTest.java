package com.exam.tournament.service;

import com.exam.tournament.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamService teamService;
    @Test
    void createTeam() {
        Team result = teamService.createTeam("test team");
        assertEquals("test team", result.getName());
    }
}