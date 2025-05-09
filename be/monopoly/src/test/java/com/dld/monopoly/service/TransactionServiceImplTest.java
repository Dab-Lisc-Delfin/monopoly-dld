package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.RentableProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {
    private static Game game;
    private static GameManager gameManager;
    private static GameServiceImpl gameServiceImpl;
    private static TransactionServiceImpl transactionServiceImpl;

    @BeforeAll
    static void initializeGame() {
        gameManager = new GameManager();
        gameServiceImpl = new GameServiceImpl();
        transactionServiceImpl = new TransactionServiceImpl(gameServiceImpl);

        game = gameServiceImpl.createNewGame();
        gameManager.addGame(game);
    }


    @Test
    void buyProperty_whenPlayerCanBuyProperty_returnCorrect() {
        Player player = new Player();
        player.setAfterRoll(true);
        RentableProperty field = (RentableProperty) gameServiceImpl.findFieldById(game, 4);
        player.setPosition(field);
        transactionServiceImpl.buyProperty(game, player);

        assertEquals(1440, player.getMoney());
        assertEquals(1, player.getProperties().size());
        assertEquals(player, field.getOwner());
    }


    @Test
    void payOwnerOfTheProperty_buyResidentialProperty() {
        Player player1 = new Player();
        Player player2 = new Player();




    }
}