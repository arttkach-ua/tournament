package com.exam.tournament;

import com.exam.tournament.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TournamentApplication implements CommandLineRunner {

	@Autowired
	private TournamentService tournamentService;

	public static void main(String[] args) {
		SpringApplication.run(TournamentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		tournamentService.processTournament();
	}
}
