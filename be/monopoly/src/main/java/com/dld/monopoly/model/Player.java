package com.dld.monopoly.model;

import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.RentableProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player {

    public Player() {
        this.dices = null;
        this.money = 1500;
        this.allDiceRollsInThisTour = 0;
        this.doubletCounter = 0;
        this.isInJail = false;
        this.properties = new ArrayList<>();
        this.afterRoll = false;
    }

    private String nickname;
    private int[] dices;
    private int money;
    private int playerIndex;
    private int allDiceRollsInThisTour;
    private int doubletCounter;
    private Field position;
    private boolean isInJail;
    private List<RentableProperty> properties;
    private boolean afterRoll;

}
