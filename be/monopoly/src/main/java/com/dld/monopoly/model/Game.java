package com.dld.monopoly.model;

import java.util.List;
import java.util.UUID;

public class Game {
    public Game(String gameId) {
        this.gameId = gameId;
    }
    private final String gameId;
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex = 0;
}
