package com.dld.monopoly.service;

import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;

import java.util.List;

public interface GameService {

    Player addPlayerToGame(String gameId, String playerNick);

    Field findFieldById(Game game, int fieldId);

    Field findFieldByName(Game game, String fieldName);

    List<Card> shuffleCards(List<Card> cards);

    Game createNewGame();

}
