package com.exam.tournament.service;

import com.exam.tournament.model.Team;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TeamService {
    /**
     * Creates new team
     * @param teamName
     * @return
     */
    public Team createTeam(String teamName){
        return Team.builder()
                .name(teamName)
                .build();
    }
}
