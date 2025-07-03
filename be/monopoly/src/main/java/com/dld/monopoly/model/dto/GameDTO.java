package com.dld.monopoly.model.dto;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.Player;

import java.util.List;

public record GameDTO(
        String gameId,
        List<Player> players,
        Board board,
        Player currentPlayer,
        List<Card> chanceCards,
        List<Card> communityChestCards,
        boolean isStarted
) {


}
