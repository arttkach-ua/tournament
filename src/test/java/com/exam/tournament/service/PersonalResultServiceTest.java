package com.exam.tournament.service;

import com.exam.tournament.exceptions.TournamentProcessingException;
import com.exam.tournament.model.GameTypes;
import com.exam.tournament.model.results.personal.BasketBallPersonalResult;
import com.exam.tournament.model.results.personal.HandBallPersonalResult;
import com.exam.tournament.model.results.personal.PersonalResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PersonalResultServiceTest {
    @Autowired
    private PersonalResultService personalResultService;

    @Test
    void initPersonalResultHandBall() {
        PersonalResult result = personalResultService.initPersonalResult(GameTypes.HANDBALL);
        assertEquals(result.getClass(), HandBallPersonalResult.class);
    }
    @Test
    void initPersonalResultBasketBall() {
        PersonalResult result = personalResultService.initPersonalResult(GameTypes.BASKETBALL);
        assertEquals(result.getClass(), BasketBallPersonalResult.class);
    }
    @Test
    void initPersonalResultNULL() {
        TournamentProcessingException ex = assertThrows(TournamentProcessingException.class, ()->personalResultService.initPersonalResult(null));
        assertEquals("Game type can't be null",ex.getMessage());
    }
}