package com.salvo.SalvoApplication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player
{
    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;


    @OneToMany(mappedBy="playerPlaying", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    @JsonIgnore
    public List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    List<Score> scores = new ArrayList<>();

    @JsonIgnore
    public List<Score> getScores() { return scores; }

    public void setScores(Game game) {
        this.scores = scores;
    }

    @JsonIgnore
    public List<ScoreTie> getTie() { return scoresTie; }

    public void setScoresTie(Game game) {
        this.scoreTie = scores;


        @JsonIgnore
        public List<Score> getScores() { return scores; }

        public void setScores(Game game) {
            this.scores = scores;
        }
        @JsonIgnore
        public List<Score> getWon() { return scores; }

        public void setScores(Game game) {
            this.scores = scores;
        }}
    //CONSTRUCTOR
    public Player() { }

    public Player(String playerEmail, String playerPassword)
    {
        email = playerEmail;
        password = playerPassword;
    }

    //GETTERS
  /*  public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }*/

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public long getId()
    {
        return id;
    }

    //SETTERS
 /*   public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    } */

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayerPlaying(this);
        gamePlayers.add(gamePlayer);

    }

    public double getScore(Player player) {
        return getWon(player.getScores())*1 +
                getLost(player.getScores())*((double)0.5) +
                getTie(player.getScores())*0;
    }
    public double getTotalScore(Player player) {
        return getWon(player.getScores())*1 +
    }



    public double getWon(List<Score> scores) {
        return scores
                .stream()
                .filter(score -> score.getScore() == 1)
                .count();
    }
    public double getTie(List<Score> scores) {
        return scores
                .stream()
                .filter(score -> score.getScore() == 0.5)
                .count();
    }
    public double getLost(List<Score> scores) {
        return scores
                .stream()
                .filter(score -> score.getScore() == 0)
                .count();
    }
}