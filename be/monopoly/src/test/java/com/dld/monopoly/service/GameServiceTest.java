package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.FieldType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private static Game game;
    private static GameManager gameManager = new GameManager();
    private static GameManagerService gameManagerService = new GameManagerService(gameManager);
    private GameService gameService = new GameService(gameManager, gameManagerService);

    @BeforeAll
    static void initializeGame() {
        game = gameManagerService.createNewGame();
    }

    @Test
    void throwDices_shouldReturnDicesBetween1and6() {
        int[] dices = gameService.throwDices();

        assertTrue(dices[0] > 0);
        assertTrue(dices[0] < 7);

        assertTrue(dices[1] > 0);
        assertTrue(dices[1] < 7);
    }


    @Test
    void findFieldById_whenSearchingPropertyFields_shouldReturnCorrect() {
        Field field = gameService.findFieldById(game, 11); //id:11 - jail
        assertEquals(field.getId(), 11);
        assertEquals(field.getFieldType(), FieldType.JAIL);
        assertEquals(field.getName(), "JAIL");


        field = gameService.findFieldById(game, 40); //id:40 - last field
        assertEquals(field.getId(), 40);
        assertEquals(field.getFieldType(), FieldType.PROPERTY);
        assertEquals(field.getName(), "BOARDWALK");
    }


    @Test
    void findFieldByName_whenSearchingPropertyFields_shouldReturnCorrect() {
        Field field = gameService.findFieldByName(game, "JAIL"); //id:11 - jail
        assertEquals(field.getId(), 11);
        assertEquals(field.getFieldType(), FieldType.JAIL);
        assertEquals(field.getName(), "JAIL");


        field = gameService.findFieldByName(game, "BOARDWALK"); //id:40 - last field
        assertEquals(field.getId(), 40);
        assertEquals(field.getFieldType(), FieldType.PROPERTY);
        assertEquals(field.getName(), "BOARDWALK");
    }
}