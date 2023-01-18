package com.exam.tournament.model;

import com.exam.tournament.exceptions.TournamentProcessingException;
import com.exam.tournament.model.results.personal.PersonalResult;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Game {
    private GameTypes type;
    private Set<Team> teams = new HashSet<>();
    private Team winner;
    private Tournament tournament;
    private Set<PersonalResult> personalResults = new HashSet<>();

    public void addTeam(Team team){
        teams.add(team);
    }
    public Team getTeamByName(String teamName){
        return teams.stream()
                .filter(a->teamName.equals(a.getName()))
                .findFirst()
                .orElseThrow(()->new TournamentProcessingException("Team not found"));
    }

    public void addPersonalResult(PersonalResult personalResult){
        personalResults.add(personalResult);
    }

}
