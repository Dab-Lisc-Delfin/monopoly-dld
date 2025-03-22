package com.dld.monopoly.service;

import com.dld.monopoly.model.GameManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    @Mock
    private GameManager gameManager;
    private GameService gameService = new GameService(gameManager);


    @Test
    void throwDices_shouldReturnDicesBetween1and6() {
        int[] dices = gameService.throwDices();

        assertTrue(dices[0] > 0);
        assertTrue(dices[0] < 7);

        assertTrue(dices[1] > 0);
        assertTrue(dices[1] < 7);
    }
}