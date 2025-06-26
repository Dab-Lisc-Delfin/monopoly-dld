package com.dld.monopoly.model.game;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.Player;
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
        isStarted = false;
    }

    private final String gameId;
    private List<Player> players;
    private Board board;
    private Player currentPlayer;
    private List<Card> chanceCards;
    private List<Card> communityChestCards;
    private boolean isStarted;

}
