package com.salvo.SalvoApplication;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")				// Se le cambia la raiz a la ruta
public class SalvoRestController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    // Aqui van los objetos de transferencias de datos, empezamos con los games
    @RequestMapping("/games")        //servicio que puede solicitar el front
    public List<Object> getALL() {
        return gameRepository.findAll()
                .stream()
                .map(game -> GameDTO(game))
                .collect(Collectors.toList());
    }

    private Map<String, Object> GameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("creationDate", game.getCreationDate());
        dto.put("players", getListGamePlayers(game.getGamePlayers()));
        dto.put("score", getAllScore(game.getScores()));
        return dto;
    }
    //segundo servicio que puede solicitar el front, acompañado de un parametro pasado por ruta
    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepository.findOne(gamePlayerId);
        return getGameViewDTO(gamePlayer.getGame(), gamePlayer);

    }


// este se relaciona con los game players
    private Map<String, Object> getGameViewDTO(Game game, GamePlayer gamePlayer) {

        Map<String, Object> dtoGameView = new LinkedHashMap<String, Object>();
        List<Ship> Ships = gamePlayer.getShips();
        dtoGameView.put("id", game.getId());
        dtoGameView.put("creationDate", game.getCreationDate());
        dtoGameView.put("gamePlayers", getGamePlayersListDTO(game.getGamePlayers()));
        dtoGameView.put("ships", getLocationShips(gamePlayer.getShips()));
        dtoGameView.put("salvoes", getSalvos(game));
        return dtoGameView;
    }

    private List<Map<String, Object>> getGamePlayersListDTO(List<GamePlayer> gamePlayers) {

        return gamePlayers
                .stream()
                .map(gamePlayer -> getGamePlayerDTO(gamePlayer))
                .collect(Collectors.toList());
    }
    // Aqui van los GamePlayers

    private Map<String, Object> getGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dtoGamePlayer = new LinkedHashMap<String, Object>();
        dtoGamePlayer.put("idGamePlayer", gamePlayer.getId());
        dtoGamePlayer.put("player", gamePlayer.getPlayerPlaying());
        dtoGamePlayer.put("joinDate", gamePlayer.getJoinDate());
        return dtoGamePlayer;
    }

    private List<Object> getListGamePlayers(List<GamePlayer> gamePlayers) {
        return gamePlayers
                .stream()
                .map(gamePlayer -> gamePlayersDTO(gamePlayer))
                .collect(Collectors.toList());
    }

    private Map<String, Object> gamePlayersDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", gamePlayer.getPlayerPlaying());
        return dto;
    }

    // Aqui van los Players
    private Map<String, Object> playerDTO(Player player) {

        Map<String, Object> dtoPlayer = new LinkedHashMap<String, Object>();
        dtoPlayer.put("idPlayer", player.getId());
        dtoPlayer.put("email", player.getEmail());
        dtoPlayer.put("score", player.getScores());
      dtoPlayer.put("totalScore", getTotalScore(player.getTotalScore()));
        dtoPlayer.put("won", player.getWon(player.getScores()));
        dtoPlayer.put("total", player.getScore(player));
        dtoPlayer.put("tie", player.getTie(player.getScores()));
        dtoPlayer.put("lost", player.getLost(player.getScores()));
        return dtoPlayer;
    }

    // Genero una lista de Players
    private List<Object> getPlayersList(List<Player> playersList) {
        return playersList.stream()
                .map(player -> playerDTO(player))
                .collect(Collectors.toList());
    }

    // Aqui van las naves
    private List<Map<String, Object>> getLocationShips(List<Ship> ships) {
        return ships
                .stream()
                .map(ship -> getShipDTO(ship))
                .collect(Collectors.toList());
    }

    private Map<String, Object> getShipDTO(Ship ship) {

        Map<String, Object> dtoShip = new LinkedHashMap<String, Object>();
        dtoShip.put("type", ship.getType());
        dtoShip.put("locations", ship.getLocations());
        return dtoShip;
    }
// Aqui van los salvoes. Se mapean para ver sus atributos.

    private Map<String, Object> SalvoDTO(Salvo salvo) {
    Map<String, Object> dtoSalvo = new LinkedHashMap<String, Object>();
    dtoSalvo.put("player", salvo.getGamePlayer().getPlayerPlaying().getId());
    dtoSalvo.put("turn", salvo.getTurn());
    dtoSalvo.put("locations", salvo.getSalvoLocations());
    return dtoSalvo;

}

    private List<Map<String, Object>> getSalvos(Game game) {
        List<Map<String, Object>> SalvoList = new ArrayList<>();
        game.getGamePlayers().forEach(gamePlayer -> SalvoList.addAll(SalvoLocationsList(gamePlayer.getSalvoes())));
        return SalvoList;
    }

    private List<Map<String, Object>> SalvoLocationsList(List<Salvo> salvoList) {
        return salvoList
                .stream()
                .map(salvo -> SalvoDTO(salvo))
                .collect(Collectors.toList());
    }

    // Endpoint /api/ships_view
    @RequestMapping("/ships_view")
    public List<Map<String, Object>> getShips(){
        return shipRepository.findAll()
                .stream()
                .map(ship -> getShipDTO(ship))
                .collect(Collectors.toList());
    }


    // Aqui empiezan los Puntajes
    private Map<String, Object> scoreDTO(Score score) {
        Map<String, Object> scoreDTO = new LinkedHashMap<>();
        scoreDTO.put("player", score.getScore());
       scoreDTO.put("game",score.getScore());
        return scoreDTO;
    }
    // api/leaderBoard
    @RequestMapping("/leaderBoard")
    public List<Map<String, Object>> getLeaderBoard() {
        return playerRepository.findAll()
                .stream()
                .map(player-> playerDTO(player))
                .collect(Collectors.toList());
    }
    // Genero una lista de todos los Scores
    private List<Double> getAllScore(List<Score> scoreList) {
        return scoreList.stream()
                .map(score -> score.getScore())
                .collect(Collectors.toList());
    }

    // Con esta logica haremos los puntajes

    public double getWins(Player player) {
        double win = 1;
        return ++win;
    }

    public double getLosses(Player player) {
        double loss = 0;
        return ++loss;
    }

    public double getTies(Player player) {
        double tie = 0.5;
        return ++tie;
    }

    public double getTotalScore(Player player) {
        double totalScore;
        totalScore = getWins(player) + getLosses(player) + getTies(player);
        return totalScore;
    }

}
