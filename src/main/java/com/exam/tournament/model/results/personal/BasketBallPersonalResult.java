package com.exam.tournament.model.results.personal;

import com.exam.tournament.model.Game;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BasketBallPersonalResult extends PersonalResult {
    private int pointsScored;
    private int rebounds;
    private int assists;

    @Override
    public void calculatePersonalResult(Game game) {
        int pointScoredMultiplier = 2;
        int reboundMultiplier = 1;
        int assistMultiplier = 1;

        int tr = pointScoredMultiplier*pointsScored + reboundMultiplier*rebounds + assistMultiplier*assists + getWinnerBonus(game);
        setMvpPoints(tr);
    }

    @Override
    public void fillPersonalResult(Game game, List<String> playerInfo) {
        setTeam(game.getTeamByName(playerInfo.get(3)));
        setPlayer(game.getTournament().getTournamentMember(playerInfo.get(1)));
        setPointsScored(Integer.parseInt(playerInfo.get(4)));
        setRebounds(Integer.parseInt(playerInfo.get(5)));
        setAssists(Integer.parseInt(playerInfo.get(6)));
    }

    private int getWinnerBonus(Game game){
        int bonus = 0;
        if (game.getWinner() == getTeam()){
            bonus = 10;
        }
        return bonus;
    }
}
