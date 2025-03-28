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


    @Test
    void checkIfDoublet_whenGivenDoubletValues_thenReturnTrue() {
        int[] dices = {2, 2};
        assertTrue(gameService.checkIfDoublet(dices));

        int[] dices2 = {6, 6};
        assertTrue(gameService.checkIfDoublet(dices2));
    }


    @Test
    void checkIfDoublet_whenGivenNoNDoubletValues_thenReturnFalse() {
        int[] dices = {1, 4};
        assertFalse(gameService.checkIfDoublet(dices));

        int[] dices2 = {3, 5};
        assertFalse(gameService.checkIfDoublet(dices2));
    }


    @Test
    void makeMove_whenGivenAllFieldTypes_shouldBehaveCorrectly() {
        Player testPlayer = gameService.addPlayerToGame(game.getGameId(), "testPlayer");
        int fixedDiceRolls = 0;


        fixedDiceRolls = 4;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.TAX, testPlayer.getPosition().getFieldType());
        assertEquals("INCOME TAX", testPlayer.getPosition().getName());
        assertEquals(5, testPlayer.getPosition().getId());
        assertEquals(1300, testPlayer.getMoney()); //-200$


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.CHANCE, testPlayer.getPosition().getFieldType());
        assertEquals("CHANCE", testPlayer.getPosition().getName());
        assertEquals(8, testPlayer.getPosition().getId());


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.JAIL, testPlayer.getPosition().getFieldType());
        assertEquals("JAIL", testPlayer.getPosition().getName());
        assertEquals(11, testPlayer.getPosition().getId());


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.PROPERTY, testPlayer.getPosition().getFieldType());
        assertEquals("STATES AVENUE", testPlayer.getPosition().getName());
        assertEquals(14, testPlayer.getPosition().getId());


        fixedDiceRolls = 4;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.COMMUNITY_CHEST, testPlayer.getPosition().getFieldType());
        assertEquals("COMMUNITY CHEST", testPlayer.getPosition().getName());
        assertEquals(18, testPlayer.getPosition().getId());


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.FREE_PARKING, testPlayer.getPosition().getFieldType());
        assertEquals("FREE PARKING", testPlayer.getPosition().getName());
        assertEquals(21, testPlayer.getPosition().getId());


        fixedDiceRolls = 5;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.RAILROADS, testPlayer.getPosition().getFieldType());
        assertEquals("B&O RAILROAD", testPlayer.getPosition().getName());
        assertEquals(26, testPlayer.getPosition().getId());


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.WATER_WORKS, testPlayer.getPosition().getFieldType());
        assertEquals("WATER_WORKS", testPlayer.getPosition().getName());
        assertEquals(29, testPlayer.getPosition().getId());


        fixedDiceRolls = 10;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.TAX, testPlayer.getPosition().getFieldType());
        assertEquals("LUXURY TAX", testPlayer.getPosition().getName());
        assertEquals(39, testPlayer.getPosition().getId());
        assertEquals(1200, testPlayer.getMoney()); //-100$


        fixedDiceRolls = 11;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.PROPERTY, testPlayer.getPosition().getFieldType());
        assertEquals("CONNECTICUT AVENUE", testPlayer.getPosition().getName());
        assertEquals(10, testPlayer.getPosition().getId());
        assertEquals(1400, testPlayer.getMoney()); //+200$ //START//


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.ELECTRICITY, testPlayer.getPosition().getFieldType());
        assertEquals("ELECTRIC COMPANY", testPlayer.getPosition().getName());
        assertEquals(13, testPlayer.getPosition().getId());


        fixedDiceRolls = 18;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        gameService.makeMove(game, testPlayer);
        assertEquals(FieldType.GO_TO_JAIL, testPlayer.getPosition().getFieldType());
        assertEquals("JAIL", testPlayer.getPosition().getName());
        assertEquals(11, testPlayer.getPosition().getId());
    }

}