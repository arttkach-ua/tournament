package com.exam.tournament.model.results.personal;

import com.exam.tournament.model.Game;
import com.exam.tournament.model.Player;
import com.exam.tournament.model.Team;
import lombok.Data;

import java.util.List;

@Data
public abstract class PersonalResult {
    private Player player;
    private Team team;
    private int mvpPoints;

    public abstract void calculatePersonalResult(Game game);

    public abstract void fillPersonalResult(Game game, List<String> playerInfo);
}
