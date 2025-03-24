package com.dld.monopoly.dto;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameStateDTO {
    private String gameId;
    private String currentPlayer;
    private String gameStatus;
    private int currentTurn;
    private List<Player> players;
    private Board board;
}
