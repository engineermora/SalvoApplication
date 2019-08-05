package com.salvo.SalvoApplication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class GamePlayer {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player playerPlaying;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Ship> ships = new ArrayList<>();

   @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Salvo> salvoes = new ArrayList<>();

    public GamePlayer() {
    }

    public GamePlayer(Game game, Player playerPlaying) {
        this.joinDate = new Date();
        this.game = game;
        this.playerPlaying = playerPlaying;
    }

    public long getId(){
        return id;
    }

    public Date getJoinDate(){
        return joinDate;
    }

    public void setJoinDate(Date joinDate){
        this.joinDate = joinDate;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayerPlaying() {
        return playerPlaying;
    }

    public void setPlayerPlaying(Player playerPlaying) {
        this.playerPlaying = playerPlaying;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
        ship.setGamePlayer(this);
    }

    public List<Salvo> getSalvoes() {
        return salvoes;
    }

    public void addSalvo(Salvo salvo) {
        this.salvoes.add(salvo);
        salvo.setGamePlayer(this);
    }

    }
