package com.exam.tournament.service;

import com.exam.tournament.model.Game;
import com.exam.tournament.model.Player;
import com.exam.tournament.model.Tournament;
import com.exam.tournament.model.results.personal.PersonalResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TournamentService {
    @Autowired
    private FilesService filesService;
    @Autowired
    private GameService gameService;
    /**
     * Main tournament processor.
     * In this code starts reading files, calculation result of games and
     */
    public void processTournament(){
        Tournament tournament = new Tournament();
        fillTournament(tournament);
        Player mvp = getMVP(tournament);
        printInfoAboutMVP(mvp);
    }

    /**
     * Prints into console info about MVP of tournament
     * @param mvp
     */
    public void printInfoAboutMVP(Player mvp){
        log.debug("Call print info about MVP. mvp is {}", mvp);
        System.out.println("================");
        System.out.print("MVP is ");
        System.out.println(mvp.getNickName());
        System.out.println("================");
    }

    /**
     * Gets mvp of tournament
     * @param tournament
     * @return *{@link Player} - player with max count of mvpPoints
     */
    public Player getMVP(Tournament tournament){
        log.debug("Calling get mvp");

        Set<PersonalResult> allResults = tournament.getGames().stream()
                .map(Game::getPersonalResults)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        Map<Player, Integer> myMap = allResults.stream()
                .collect(Collectors.groupingBy(PersonalResult::getPlayer,Collectors.summingInt(PersonalResult::getMvpPoints)));

        return Collections.max(myMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void fillTournament(Tournament tournament){
        Set<File> files = filesService.getFileNames();
        addGamesToTournament(files, tournament);
    }

    /**
     * For each file add info in
     * @param files
     * @param tournament
     */
    public void addGamesToTournament(Set<File> files, Tournament tournament){
        files.stream()
                .forEach(file->gameService.addGameToTournament(file, tournament));
    }
}
