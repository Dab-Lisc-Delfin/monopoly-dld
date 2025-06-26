package com.dld.monopoly.service;

import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.game.GameManager;
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