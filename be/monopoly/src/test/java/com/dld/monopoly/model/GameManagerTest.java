package com.dld.monopoly.model;

import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.game.GameManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GameManagerTest {
    private static GameManager gameManager;

    @BeforeAll
    static void initialize() {
        gameManager = GameManager.getInstance();
    }


    @Test
    void getInstance() {
    }


    @Test
    void addGame_shouldIncreaseActiveGamesSize() {
    }


    @Test
    void deleteGame_addGameThenDeleteIt() {
        Game game = new Game("12345", null);
        gameManager.addGame(game);
        assertTrue(gameManager.getActiveGames().contains(game));

        gameManager.deleteGame("12345");
        assertFalse(gameManager.getActiveGames().contains(game));

    }


    @Test
    void getGameById_checkIfFindsCorrectly() {
        Game game = new Game("testBoard111", null);
        gameManager.addGame(game);
        Game gameFound = gameManager.getGameById("testBoard111");

        assertEquals(gameFound.getGameId(), "testBoard111");
    }


    @Test
    void getActiveGames() {
    }
}