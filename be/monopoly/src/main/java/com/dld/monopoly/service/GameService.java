package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;

public interface GameService {

    Player addPlayerToGame(String gameId, String playerNick);

    Field findFieldById(Game game, int fieldId);

    Field findFieldByName(Game game, String fieldName);
}
