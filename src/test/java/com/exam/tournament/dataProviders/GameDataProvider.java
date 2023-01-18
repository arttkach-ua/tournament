package com.exam.tournament.dataProviders;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameDataProvider {
    public List<List<String>> getBasketBallDataAsFromFile(){
        return List.of(
        List.of("BASKETBALL"),
        List.of("player 1","nick1","4","Team A","10","2","7"),
        List.of("player 2","nick2","8","Team A","0","10","0"),
        List.of("player 3","nick3","15","Team A","15","10","4"),
        List.of("player 4","nick4","16","Team B","20","0","0"),
        List.of("player 5","nick5","23","Team B","4","7","7"),
        List.of("player 6","nick6","42","Team B","8","10","0"));
    }
    public List<List<String>> getHandBallDataAsFromFile(){
        return List.of(
        List.of("HANDBALL"),
        List.of("player 1","nick1","4","Team A","0","20"),
        List.of("player 2","nick2","8","Team A","15","20"),
        List.of("player 3","nick3","15","Team A","10","20"),
        List.of("player 4","nick4","16","Team B","1","25"),
        List.of("player 5","nick5","23","Team B","12","25"),
        List.of("player 6","nick6","42","Team B","8","25"));
    }
}
