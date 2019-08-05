package com.salvo.SalvoApplication;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

@Entity
public class Salvo {
	
	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER) // La relacion es de muchos a uno, muchas salvas de un solo jugador en un solo juego.
	@JoinColumn(name="idGamePlayer")
	private GamePlayer gamePlayer; // Se relaciona con un jugador en juego.

	private int turn; // para tener en cuenta el turno a tiro

	@ElementCollection // Para tener que vincular las salvas con donde se ubican.
	@Column(name = "locations")
	private List<String> locations = new ArrayList<>();

	public Salvo() {}

	public Salvo(int turn, GamePlayer gamePlayer, List<String> locations) {

		this.turn = turn;
		this.gamePlayer = gamePlayer;
		this.locations = locations;
	}

	//Getters
	public long getId() {
		return id;
	}

	public int getTurn() {
		return turn;
	}

	public GamePlayer getGamePlayer() {
		return gamePlayer;
	}

	public List<String> getSalvoLocations() {
		return locations;
	}

	// Setters

	public void setId(long id) {
		this.id = id;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void setGamePlayer(GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
	}

	public void setSalvoLocations(List<String> salvoLocations) {
		this.locations = locations;
	}

}



