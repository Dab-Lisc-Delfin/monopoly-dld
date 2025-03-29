package com.dld.monopoly.controller;

import com.dld.monopoly.dto.GameStateDTO;
import com.dld.monopoly.model.Game;
import com.dld.monopoly.service.GameManagerService;
import com.dld.monopoly.service.GameService;
import com.dld.monopoly.service.MoveService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private final GameService gameService;
    private final GameManagerService gameManagerService;
    private final MoveService moveService;

    public TestController(GameService gameService, MoveService moveService, GameManagerService gameManagerService) {
        this.gameManagerService = gameManagerService;
        this.gameService = gameService;
        this.moveService = moveService;
    }


    @GetMapping("/diceTest")
    @ResponseBody
    public String test1() {
        return moveService.throwDices().toString();
    }


    @PostMapping("/createGame")
    public ResponseEntity<GameStateDTO> test2() {
        Game game = gameManagerService.createNewGame();

        GameStateDTO gameStateDTO = new GameStateDTO();


        gameStateDTO.setGameId(game.getGameId());
//        gameStateDTO.setGameStatus();
        gameStateDTO.setBoard(game.getBoard());
        gameStateDTO.setPlayers(game.getPlayers());
        gameStateDTO.setCurrentTurn(0);
//        gameStateDTO.setCurrentPlayer();
        return ResponseEntity.ok(gameStateDTO);
    }


}
