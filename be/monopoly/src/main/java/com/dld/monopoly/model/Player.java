package com.dld.monopoly.model;

import com.dld.monopoly.model.fields.Field;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    public Player() {
        this.dices = null;
        this.money = 1500;
        this.allDiceRollsInThisTour = 0;
        this.doubletCounter = 0;
    }

    private String nickname;
    private int[] dices;
    private int money;
    private int playerIndex;
    private int allDiceRollsInThisTour;
    private int doubletCounter;
    private Field position;
}
