package com.exam.tournament.service;

import com.exam.tournament.exceptions.TournamentProcessingException;
import com.exam.tournament.model.Game;
import com.exam.tournament.model.GameTypes;
import com.exam.tournament.model.Team;
import com.exam.tournament.model.Tournament;
import com.exam.tournament.model.results.personal.PersonalResult;
import com.exam.tournament.util.messages.ErrorMessages;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GameService {
    @Autowired
    private PersonalResultService personalResultService;
    @Autowired
    private FilesService filesService;

    /**
     * Sets into game type of game
     * @param game - *{@link Game} - game entity
     * @param data - list with data from file. It should contain 1 element with game name
     */
    public void setGameType(Game game, List<String> data){
        if (data.size()==1){
            game.setType(getGameTypeByName(data.get(0)));
        }else {
            throw new TournamentProcessingException(ErrorMessages.WRONG_FILE_DATA);
        }
    }

    /**
     * Gets enum type of game by String with name
     * @param name - name of game
     * @return *{@link GameTypes} - type of game
     * @throws *{@link RuntimeException} if name not supported
     */
    public GameTypes getGameTypeByName(String name){
        GameTypes result;
        if (name==null) throw new TournamentProcessingException(ErrorMessages.NULL_GAME_NAME);
        switch (name.toUpperCase()){
            case "HANDBALL": {
                result=GameTypes.HANDBALL;
                break;
            }
            case "BASKETBALL": {
                result=GameTypes.BASKETBALL;
                break;
            }
            default: {
                throw new TournamentProcessingException(String.format(ErrorMessages.GAME_NOT_SUPPORTED, name));
            }
        }
        return result;
    }

    public void setTeams(Game game, List<List<String>> gameData){
        Map<Team, Integer> teamScores = getTeamScores(gameData);

        Team winner = Collections.max(teamScores.entrySet(), Map.Entry.comparingByValue()).getKey();
        game.setWinner(winner);

        teamScores.keySet().stream()
                .forEach(game::addTeam);
    }

    /**
     * Calculates team scores and returns it as Map
     * @param gameData
     * @return
     */
    public Map<Team, Integer> getTeamScores(List<List<String>> gameData){
        return gameData.stream()
                .skip(1)
                .collect(Collectors.groupingBy(d->createTeam(d.get(3)),Collectors.summingInt(d->Integer.parseInt(d.get(4)))));
    }

    /**
     * Creates new team
     * @param teamName
     * @return
     */
    private Team createTeam(String teamName){
        return Team.builder()
                .name(teamName)
                .build();
    }

    /**
     * Gets personal result from data and adds it to game
     * @param game
     * @param playerInfo
     */
    public void addInfoAboutPlayer(Game game, List<String> playerInfo){
        log.debug("Call add info about player");
        PersonalResult personalResult = personalResultService.createPersonalResult(game, playerInfo);
        game.addPersonalResult(personalResult);
    }

    /**
     * Read data from file and add it to tournament
     * @param file
     * @param tournament
     */
    public void addGameToTournament(File file, Tournament tournament){
        List<List<String>> gameData= filesService.readCSV(file);
        if (gameData.isEmpty()) throw new TournamentProcessingException();
        Game game = new Game();
        tournament.addGame(game);
        game.setTournament(tournament);
        setGameType(game, gameData.get(0));
        setTeams(game, gameData);


        gameData.stream()
                .skip(1)
                .forEach(playerInfo->addInfoAboutPlayer(game, playerInfo));
    }

}
