package com.exam.tournament.model.results.personal;

import com.exam.tournament.model.Game;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = true)
public class HandBallPersonalResult extends PersonalResult{

    private int goalsMade;
    private int goalsReceived;

    @Override
    public void calculatePersonalResult(Game game) {
        int goalsMadeMultiplier = 2;
        int goalsReceivedMultiplier = -1;

        int tr = goalsMadeMultiplier*goalsMade + goalsReceivedMultiplier*goalsReceived + getWinnerBonus(game);
        setMvpPoints(Math.max(tr,0));
    }

    @Override
    public void fillPersonalResult(Game game, List<String> playerInfo) {
        setTeam(game.getTeamByName(playerInfo.get(3)));
        setPlayer(game.getTournament().getTournamentMember(playerInfo.get(1)));
        setGoalsMade(Integer.parseInt(playerInfo.get(4)));
        setGoalsReceived(Integer.parseInt(playerInfo.get(5)));
    }
    private int getWinnerBonus(Game game){
        int bonus = 0;
        if (game.getWinner() == getTeam()){
            bonus = 10;
        }
        return bonus;
    }
}
