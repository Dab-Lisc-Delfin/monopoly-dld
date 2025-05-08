package com.dld.monopoly.dto;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.Player;

import java.util.List;

public record GameStateDTO(
        String gameId,
        String currentPlayer,
        String gameStatus,
        int currentTurn, List<Player> players,
        Board board
) {
}
