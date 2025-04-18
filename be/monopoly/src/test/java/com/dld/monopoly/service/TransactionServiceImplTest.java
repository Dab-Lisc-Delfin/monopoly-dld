package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.PropertyField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {
    private static Game game;
    private static GameManager gameManager;
    private static GameManagerServiceImpl gameManagerServiceImpl;
    private static GameServiceImpl gameServiceImpl;
    private static TransactionServiceImpl transactionServiceImpl;

    @BeforeAll
    static void initializeGame() {
        gameManager = new GameManager();
        gameManagerServiceImpl = new GameManagerServiceImpl(gameManager);
        gameServiceImpl = new GameServiceImpl(gameManager, gameManagerServiceImpl);
        transactionServiceImpl = new TransactionServiceImpl(gameServiceImpl);

        game = gameManagerServiceImpl.createNewGame();
    }


    @Test
    void buyProperty_whenPlayerCanBuyProperty_returnCorrect() {
        Player player = new Player();
        player.setAfterRoll(true);
        PropertyField field = (PropertyField) gameServiceImpl.findFieldById(game, 4);
        player.setPosition(field);
        transactionServiceImpl.buyProperty(game, player);

        assertEquals(1440, player.getMoney());
        assertEquals(1, player.getProperties().size());
        assertEquals(player, field.getOwner());
    }
}