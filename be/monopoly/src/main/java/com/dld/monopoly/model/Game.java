package com.dld.monopoly.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Game {
    public Game(String gameId, Board board) {
        this.gameId = gameId;
        this.board = board;
        this.currentPlayer = null;
    }

    private final String gameId;
    private List<Player> players;
    private Board board;
    private Player currentPlayer;
}
