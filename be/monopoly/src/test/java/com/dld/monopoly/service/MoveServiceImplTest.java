package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.FieldType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveServiceImplTest {

    private static Game game;
    private static GameManager gameManager;
    private static GameServiceImpl gameServiceImpl;
    private static MoveServiceImpl moveServiceImpl;

    @BeforeAll
    static void initializeGame() {
        gameManager = GameManager.getInstance();
        gameServiceImpl = new GameServiceImpl();
        moveServiceImpl = new MoveServiceImpl(gameServiceImpl);

        game = gameServiceImpl.createNewGame();
        gameManager.addGame(game);
    }


    @Test
    void throwDices_shouldReturnDicesBetween1and6() {
        int[] dices = moveServiceImpl.throwDices();

        assertTrue(dices[0] > 0);
        assertTrue(dices[0] < 7);

        assertTrue(dices[1] > 0);
        assertTrue(dices[1] < 7);
    }


    @Test
    void checkIfDoublet_whenGivenDoubletValues_thenReturnTrue() {
        int[] dices = {2, 2};
        assertTrue(moveServiceImpl.checkIfDoublet(dices));

        int[] dices2 = {6, 6};
        assertTrue(moveServiceImpl.checkIfDoublet(dices2));
    }


    @Test
    void checkIfDoublet_whenGivenNoNDoubletValues_thenReturnFalse() {
        int[] dices = {1, 4};
        assertFalse(moveServiceImpl.checkIfDoublet(dices));

        int[] dices2 = {3, 5};
        assertFalse(moveServiceImpl.checkIfDoublet(dices2));
    }


    @Test
    void makeMove_whenGivenAllFieldTypes_shouldBehaveCorrectly() {
        Player testPlayer = gameServiceImpl.addPlayerToGame(game.getGameId(), "testPlayer");
        int fixedDiceRolls = 0;


        fixedDiceRolls = 4;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.TAX, testPlayer.getPosition().getFieldType());
        assertEquals("INCOME TAX", testPlayer.getPosition().getName());
        assertEquals(5, testPlayer.getPosition().getId());
        assertEquals(1300, testPlayer.getMoney()); //-200$


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.CHANCE, testPlayer.getPosition().getFieldType());
        assertEquals("CHANCE", testPlayer.getPosition().getName());
        assertEquals(8, testPlayer.getPosition().getId());


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.JAIL, testPlayer.getPosition().getFieldType());
        assertEquals("JAIL", testPlayer.getPosition().getName());
        assertEquals(11, testPlayer.getPosition().getId());


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.PROPERTY, testPlayer.getPosition().getFieldType());
        assertEquals("STATES AVENUE", testPlayer.getPosition().getName());
        assertEquals(14, testPlayer.getPosition().getId());


        fixedDiceRolls = 4;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.COMMUNITY_CHEST, testPlayer.getPosition().getFieldType());
        assertEquals("COMMUNITY CHEST", testPlayer.getPosition().getName());
        assertEquals(18, testPlayer.getPosition().getId());


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.FREE_PARKING, testPlayer.getPosition().getFieldType());
        assertEquals("FREE PARKING", testPlayer.getPosition().getName());
        assertEquals(21, testPlayer.getPosition().getId());


        fixedDiceRolls = 5;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.RAILROADS, testPlayer.getPosition().getFieldType());
        assertEquals("B&O RAILROAD", testPlayer.getPosition().getName());
        assertEquals(26, testPlayer.getPosition().getId());


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.UTILITY, testPlayer.getPosition().getFieldType());
        assertEquals("WATER WORKS", testPlayer.getPosition().getName());
        assertEquals(29, testPlayer.getPosition().getId());


        fixedDiceRolls = 10;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.TAX, testPlayer.getPosition().getFieldType());
        assertEquals("LUXURY TAX", testPlayer.getPosition().getName());
        assertEquals(39, testPlayer.getPosition().getId());
        assertEquals(1200, testPlayer.getMoney()); //-100$


        fixedDiceRolls = 11;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.PROPERTY, testPlayer.getPosition().getFieldType());
        assertEquals("CONNECTICUT AVENUE", testPlayer.getPosition().getName());
        assertEquals(10, testPlayer.getPosition().getId());
        assertEquals(1400, testPlayer.getMoney()); //+200$ //START//


        fixedDiceRolls = 3;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.UTILITY, testPlayer.getPosition().getFieldType());
        assertEquals("ELECTRIC COMPANY", testPlayer.getPosition().getName());
        assertEquals(13, testPlayer.getPosition().getId());


        fixedDiceRolls = 18;
        testPlayer.setAllDiceRollsInThisTour(fixedDiceRolls);
        moveServiceImpl.makeMove(game, testPlayer);
        assertEquals(FieldType.JAIL, testPlayer.getPosition().getFieldType());
        assertEquals("JAIL", testPlayer.getPosition().getName());
        assertEquals(11, testPlayer.getPosition().getId());
    }
}