package com.salvo.SalvoApplication;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

    @Entity
    public class Score {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private long id;

        @ManyToOne (fetch = FetchType.EAGER)
        @JoinColumn(name = "player_id")
        private Player player;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "game_id")
        private Game game;

        private double score;
        private Date finishDate;

        public Score() { }

        public Score(Game game, Player player, double score, Date finishDate) {
            this.game = game;
            this.player = player;
            this.score = score;
            this.finishDate = new Date();
        }

        public long getId() {
            return id;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }

        public void setGame(Game game) {
            this.game = game;
        }

        public Game getGame() {
            return game;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public double getScore() {
            return score;
        }

        public Date getFinishDate() {
            return finishDate;
        }

    }

