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
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date creationDate;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    public Game() {
                this.creationDate = new Date();
            }

    @JsonIgnore
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    List<Score> scores = new ArrayList<>();

    @JsonIgnore
    public List<Score> getScores() { return scores; }

    public void setScores() {
        this.scores = scores;
    }

            public long getId() {
                return id;
            }

            public Date getCreationDate() {
                return creationDate;
            }

            public void setCreationDate(Date creationDate) {
                this.creationDate = creationDate;
            }

            @JsonIgnore
            public List<GamePlayer> getGamePlayers(){
                return gamePlayers;
            }

            public void addGamePlayer(GamePlayer gamePlayer){
                gamePlayer.setGame(this);
                gamePlayers.add(gamePlayer);
            }
    }
