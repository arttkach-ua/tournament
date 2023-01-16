package com.exam.tournament.service;

import com.exam.tournament.exceptions.TournamentProcessingException;
import com.exam.tournament.model.Game;
import com.exam.tournament.model.GameTypes;
import com.exam.tournament.model.results.personal.BasketBallPersonalResult;
import com.exam.tournament.model.results.personal.HandBallPersonalResult;
import com.exam.tournament.model.results.personal.PersonalResult;
import com.exam.tournament.util.messages.ErrorMessages;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PersonalResultService {
    public PersonalResult initPersonalResult(GameTypes gameType){
        if (gameType==null) {
            throw new TournamentProcessingException(ErrorMessages.NULL_GAME_TYPE);
        }
        return switch (gameType){
            case HANDBALL -> new HandBallPersonalResult();
            case BASKETBALL-> new BasketBallPersonalResult();
            default -> throw new TournamentProcessingException(ErrorMessages.GAME_NOT_SUPPORTED);
        };
    }

    /**
     * Creates personal result and fill data to it
     * @param game
     * @param playerInfo
     */
    public PersonalResult createPersonalResult(Game game, List<String> playerInfo){
        PersonalResult personalResult = initPersonalResult(game.getType());
        personalResult.fillPersonalResult(game, playerInfo);
        personalResult.calculatePersonalResult(game);
        return personalResult;
    }
}
