package com.dld.monopoly.service;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.fields.*;
import com.dld.monopoly.model.GameManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private final GameManager gameManager;

    public GameService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public int[] throwDices() {
        int[] dices = new int[2];
        Random rnd = new Random();
        int dice1 = rnd.nextInt(6) + 1;
        int dice2 = rnd.nextInt(6) + 1;

        dices[0] = dice1;
        dices[1] = dice2;

        return dices;
    }
}
