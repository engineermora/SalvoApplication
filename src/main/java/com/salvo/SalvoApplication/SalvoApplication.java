package com.salvo.SalvoApplication;

import org.springframework.boot.CommandLineRunner;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@SpringBootApplication // Con esto Spring conoce que este es el punto de partida de la app
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean  // con esto Spring sabe cual es el metodo que gestionara esos recursos.
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository,
									  SalvoRepository salvoRepository,
									  ScoreRepository scoreRepository) {
		return (args) ->
		{
			// Creo los Players
			Player player1 = new Player("Mora@col.co", "345");
			Player player2 = new Player("AccJava@mail.ar", "876");
			Player player3 = new Player("Acuna.CM@com.co", "7654");
			Player player4 = new Player("Mind@Hub.ar", "234");

			// Guardo los players
			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			// Creo los Games y sus fechas de creacion.
			Date creationDate1 = new Date();
			Game game1 = new Game();
			game1.setCreationDate(creationDate1);
			creationDate1 = Date.from(creationDate1.toInstant().plusSeconds(3600));
			Game game2 = new Game();
			game2.setCreationDate(creationDate1);
			creationDate1 = Date.from(creationDate1.toInstant().plusSeconds(3600));
			Game game3 = new Game();
			game3.setCreationDate(creationDate1);
			creationDate1 = Date.from(creationDate1.toInstant().plusSeconds(3600));
			Game game4 = new Game();
			game4.setCreationDate(creationDate1);

			// Guardo los Games
			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);

			// Creo los GamePlayers, y los asigno a un juego.
			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1, player2);
			GamePlayer gamePlayer3 = new GamePlayer(game2, player3);
			GamePlayer gamePlayer4 = new GamePlayer(game2, player4);

			// Guardamos los Games Players
			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
			gamePlayerRepository.save(gamePlayer3);


			// Creo ubicaciones de Los Ships
			List<String> ubicacion1 = new ArrayList<>();
			ubicacion1.add("A5");
			ubicacion1.add("A6");
			ubicacion1.add("A7");
			ubicacion1.add("A8");
			ubicacion1.add("A9");

			List<String> ubicacion2 = new ArrayList<>();
			ubicacion2.add("B3");
			ubicacion2.add("C3");
			ubicacion2.add("D3");

			List<String> ubicacion3 = new ArrayList<>();
			ubicacion3.add("E8");
			ubicacion3.add("E9");


			List<String> ubicacion4 = new ArrayList<>();
			ubicacion4.add("F9");
			ubicacion4.add("G9");
			ubicacion4.add("H9");
			ubicacion4.add("I9");

			// Aca instancio los Ships
			Ship ship1 = new Ship("Lancha", gamePlayer1, ubicacion1);
			Ship ship2 = new Ship("Barco", gamePlayer2, ubicacion2);
			Ship ship3 = new Ship("Catamaran",gamePlayer1, ubicacion3);
			Ship ship4 = new Ship("Velero", gamePlayer2, ubicacion4);

			// Aca Guardo los Ships
			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);

			// Aca creo los salvos
			List<String> salvoLocation1 = new ArrayList<>();
			salvoLocation1.add("A5");
			salvoLocation1.add("A6");
			salvoLocation1.add("C5");

			List<String> salvoLocation2 = new ArrayList<>();
			salvoLocation2.add("A1");
			salvoLocation2.add("B2");
			salvoLocation2.add("C3");

			List<String> salvoLocation3 = new ArrayList<>();
			salvoLocation3.add("H4");
			salvoLocation3.add("J5");
			salvoLocation3.add("I6");

			List<String> salvoLocation4 = new ArrayList<>();
			salvoLocation4.add("A8");
			salvoLocation4.add("I9");

			// Aca Asigno los salvos
			Salvo salvo1 = new Salvo(1, gamePlayer1, salvoLocation1);
			Salvo salvo2 = new Salvo(2, gamePlayer1, salvoLocation2);
			Salvo salvo3 = new Salvo(1, gamePlayer2, salvoLocation3);
			Salvo salvo4 = new Salvo(2, gamePlayer2, salvoLocation4);

			// Aca guardo los salvos
			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);

			Date finishDate1 = new Date();
			Date finishDate2 = new Date();

			double Puntaje1 = 1;
			double Puntaje2 = 0.5;

			Score score1 = new Score(game1, player1, Puntaje1, finishDate1);
			Score score2 = new Score(game1, player2, Puntaje2, finishDate1);

			scoreRepository.save(score1);
			scoreRepository.save(score2);


		};
	}

}
