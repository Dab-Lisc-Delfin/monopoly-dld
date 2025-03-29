package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.FieldType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private static Game game;
    private static GameManager gameManager;
    private static GameManagerService gameManagerService;
    private static GameService gameService;

    @BeforeAll
    static void initializeGame() {
        gameManager = new GameManager();
        gameManagerService = new GameManagerService(gameManager);
        gameService = new GameService(gameManager, gameManagerService);

        game = gameManagerService.createNewGame();
    }


    @Test
    void findFieldById_whenSearchingPropertyFields_shouldReturnCorrect() {
        Field field = gameService.findFieldById(game, 11);
        assertEquals(field.getId(), 11);
        assertEquals(field.getFieldType(), FieldType.JAIL);
        assertEquals(field.getName(), "JAIL");


        field = gameService.findFieldById(game, 40);
        assertEquals(field.getId(), 40);
        assertEquals(field.getFieldType(), FieldType.PROPERTY);
        assertEquals(field.getName(), "BOARDWALK");
    }


    @Test
    void findFieldByName_whenSearchingPropertyFields_shouldReturnCorrect() {
        Field field = gameService.findFieldByName(game, "JAIL");

        assertEquals(field.getId(), 11);
        assertEquals(field.getFieldType(), FieldType.JAIL);
        assertEquals(field.getName(), "JAIL");


        field = gameService.findFieldByName(game, "BOARDWALK");
        assertEquals(field.getId(), 40);
        assertEquals(field.getFieldType(), FieldType.PROPERTY);
        assertEquals(field.getName(), "BOARDWALK");
    }


    @Test
    void addPlayerToGame_whenAddingPlayerToExistingNotStartedGame_shouldReturnCorrect() {
        gameService.addPlayerToGame(game.getGameId(), "testNick");
        Player player1 = game.getPlayers().get(0);

        assertEquals(player1.getPlayerIndex(), 1);
        assertEquals(player1.getNickname(), "testNick");
        assertEquals(player1.getPosition().getFieldType(), FieldType.START);
        assertEquals(player1.getMoney(), 1500);
    }

}