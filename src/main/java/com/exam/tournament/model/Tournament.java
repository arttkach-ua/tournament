package com.exam.tournament.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Tournament {
    Set<Player> members = new HashSet<>();
    Set<Game> games = new HashSet<>();

    Player mvp;

    public Player getTournamentMember(String playerName){

        return members.stream()
                .filter(player -> player.getNickName().equals(playerName))
                .findFirst()
                .orElse(registerNewPlayerInTournament(playerName));
    }
    private Player registerNewPlayerInTournament(String playerName){
        Player player = new Player(playerName);
        members.add(player);
        return player;
    }

    public void addGame(Game game){
        games.add(game);
    }
}
