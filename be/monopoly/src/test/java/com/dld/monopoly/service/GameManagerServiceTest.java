package com.dld.monopoly.service;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerServiceTest {
    private GameManager gameManager = new GameManager();
    private final GameManagerService gameManagerService = new GameManagerService(gameManager);


    @Test
    void createNewGame_shouldIncreaseActiveGamesSize() {
        gameManagerService.createNewGame();
        assertEquals(1, gameManager.getActiveGames().size());
    }


    @Test
    void createNewGame_checkGameIdLength_returnTrue() {
        Game game = gameManagerService.createNewGame();

        assertEquals(game.getGameId().length(), 5);
    }


    @Test
    void getGameById_checkIfFindsCorrectly() {
        Game game = new Game("testBoard111", null);
        gameManager.addGame(game);
        Game gameFound = gameManagerService.getGameById("testBoard111");

        assertEquals(gameFound.getGameId(), "testBoard111");
    }


    @Test
    void initializeBoard_whenBoardIsNotNull() {
        Board board = gameManagerService.initializeBoard();

        assertNotNull(board);
        assertNotNull(board.getFields());
    }

    @Test
    void initializeBoard_shouldAssignCorrectFieldIds() {
        Board board = gameManagerService.initializeBoard();

        for (int i = 0; i < 40; i++) {
            assertEquals(board.getFields().get(i).getId(), i + 1);
        }

    }
}