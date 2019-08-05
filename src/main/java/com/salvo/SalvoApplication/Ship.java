package com.salvo.SalvoApplication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Ship {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    @Column(name="Locations")
    private List<String> locations = new ArrayList<>();

    public Ship (){}

    public Ship (String type, GamePlayer gamePlayer, List<String> locations) {
        this.type = type;
        this.gamePlayer = gamePlayer;
        this.locations = locations;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> Locations) {
        this.locations = Locations;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", type='" +type + '\'' +
                ", gamePlayerPlay=" + gamePlayer +
                ", Locations=" + locations +
                '}';
    }
}
