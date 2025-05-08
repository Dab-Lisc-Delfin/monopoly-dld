package com.dld.monopoly.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    void deleteGame() {
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