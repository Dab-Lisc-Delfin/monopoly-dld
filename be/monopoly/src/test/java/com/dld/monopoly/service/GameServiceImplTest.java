package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.FieldType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    private static Game game;
    private static GameManager gameManager;
    private static GameManagerServiceImpl gameManagerServiceImpl;
    private static GameServiceImpl gameServiceImpl;

    @BeforeAll
    static void initializeGame() {
        gameManager = new GameManager();
        gameManagerServiceImpl = new GameManagerServiceImpl(gameManager);
        gameServiceImpl = new GameServiceImpl(gameManager, gameManagerServiceImpl);

        game = gameManagerServiceImpl.createNewGame();
    }


    @Test
    void findFieldById_whenSearchingPropertyFields_shouldReturnCorrect() {
        Field field = gameServiceImpl.findFieldById(game, 11);
        assertEquals(field.getId(), 11);
        assertEquals(field.getFieldType(), FieldType.JAIL);
        assertEquals(field.getName(), "JAIL");


        field = gameServiceImpl.findFieldById(game, 40);
        assertEquals(field.getId(), 40);
        assertEquals(field.getFieldType(), FieldType.PROPERTY);
        assertEquals(field.getName(), "BOARDWALK");
    }


    @Test
    void findFieldByName_whenSearchingPropertyFields_shouldReturnCorrect() {
        Field field = gameServiceImpl.findFieldByName(game, "JAIL");

        assertEquals(field.getId(), 11);
        assertEquals(field.getFieldType(), FieldType.JAIL);
        assertEquals(field.getName(), "JAIL");


        field = gameServiceImpl.findFieldByName(game, "BOARDWALK");
        assertEquals(field.getId(), 40);
        assertEquals(field.getFieldType(), FieldType.PROPERTY);
        assertEquals(field.getName(), "BOARDWALK");
    }


    @Test
    void addPlayerToGame_whenAddingPlayerToExistingNotStartedGame_shouldReturnCorrect() {
        gameServiceImpl.addPlayerToGame(game.getGameId(), "testNick");
        Player player1 = game.getPlayers().get(0);

        assertEquals(player1.getPlayerIndex(), 1);
        assertEquals(player1.getNickname(), "testNick");
        assertEquals(player1.getPosition().getFieldType(), FieldType.START);
        assertEquals(player1.getMoney(), 1500);
    }

}