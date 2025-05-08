package com.dld.monopoly.controller;

import com.dld.monopoly.dto.GameStateDTO;
import com.dld.monopoly.model.Game;
import com.dld.monopoly.service.GameServiceImpl;
import com.dld.monopoly.service.MoveServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private final GameServiceImpl gameServiceImpl;
    private final MoveServiceImpl moveServiceImpl;

    public TestController(GameServiceImpl gameServiceImpl, MoveServiceImpl moveServiceImpl) {
        this.gameServiceImpl = gameServiceImpl;
        this.moveServiceImpl = moveServiceImpl;
    }


    @GetMapping("/diceTest")
    @ResponseBody
    public String test1() {
        return moveServiceImpl.throwDices().toString();
    }


    @PostMapping("/createGame")
    public ResponseEntity<GameStateDTO> test2() {
        Game game = gameServiceImpl.createNewGame();

        GameStateDTO gameStateDTO = new GameStateDTO(game.getGameId(), null, null, 0, game.getPlayers(), game.getBoard());

        return ResponseEntity.ok(gameStateDTO);
    }


}
