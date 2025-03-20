package com.dld.monopoly.service;

import com.dld.monopoly.model.GameManager;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameManager gameManager;

    public GameService(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
