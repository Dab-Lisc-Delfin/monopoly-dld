package com.dld.monopoly.controller;

import com.dld.monopoly.service.GameServiceImpl;
import com.dld.monopoly.service.MoveServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private final MoveServiceImpl moveServiceImpl;

    public TestController(GameServiceImpl gameServiceImpl, MoveServiceImpl moveServiceImpl) {
        this.moveServiceImpl = moveServiceImpl;
    }


    @GetMapping("/diceTest")
    @ResponseBody
    public String test1() {
        return moveServiceImpl.throwDices().toString();
    }



}
