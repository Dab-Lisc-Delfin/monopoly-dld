package com.dld.monopoly.controller;

import com.dld.monopoly.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private final GameService gameService;

    public TestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/diceTest")
    public int[] test1() {
        return gameService.throwDices();
    }

}
