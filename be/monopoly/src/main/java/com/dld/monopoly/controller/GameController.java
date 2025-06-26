package com.dld.monopoly.controller;

import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.service.GameServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Game> createGame() {
        return ResponseEntity.ok(gameService.createNewGame());
    }

    @PostMapping
    public ResponseEntity<Game> getGameStatement(String gameId) {

        return null;
    }

}
