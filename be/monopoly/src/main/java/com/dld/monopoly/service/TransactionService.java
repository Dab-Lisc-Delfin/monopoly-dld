package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.Player;

public interface TransactionService {

    void buyProperty(Game game, Player player);

    void payOwnerOfTheProperty(Player owner, Player playerWhoPays);

}
