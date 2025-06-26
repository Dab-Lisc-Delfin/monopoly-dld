package com.dld.monopoly.service;

import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.Player;

public interface TransactionService {

    void buyProperty(Game game, Player player);

    void payPropertyOwner(Player owner, Player playerWhoPays);

}
