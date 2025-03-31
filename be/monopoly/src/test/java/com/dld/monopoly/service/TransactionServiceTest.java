package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.PropertyField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {
    private static Game game;
    private static GameManager gameManager;
    private static GameManagerService gameManagerService;
    private static GameService gameService;
    private static TransactionService transactionService;

    @BeforeAll
    static void initializeGame() {
        gameManager = new GameManager();
        gameManagerService = new GameManagerService(gameManager);
        gameService = new GameService(gameManager, gameManagerService);
        transactionService = new TransactionService(gameService);

        game = gameManagerService.createNewGame();
    }


    @Test
    void buyProperty_whenPlayerCanBuyProperty_returnCorrect() {
        Player player = new Player();
        player.setAfterRoll(true);
        PropertyField field = (PropertyField) gameService.findFieldById(game, 4);
        player.setPosition(field);
        transactionService.buyProperty(game, player);

        assertEquals(1440, player.getMoney());
        assertEquals(1, player.getProperties().size());
        assertEquals(player, field.getOwner());
    }
}