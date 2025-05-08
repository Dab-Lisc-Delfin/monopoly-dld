package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerServiceTest {
    private GameManager gameManager = GameManager.getInstance();


    @Test
    void getGameById_checkIfFindsCorrectly() {
        Game game = new Game("testBoard111", null);
        gameManager.addGame(game);
        Game gameFound = gameManager.getGameById("testBoard111");

        assertEquals(gameFound.getGameId(), "testBoard111");
    }

}