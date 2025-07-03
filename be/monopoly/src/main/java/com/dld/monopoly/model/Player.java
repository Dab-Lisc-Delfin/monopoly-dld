package com.dld.monopoly.model;

import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.RentableProperty;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Player {

    public Player() {
        this.dices = null;
        this.money = 1500;
        this.allDiceRollsInThisTour = 0;
        this.doubletCounter = 0;
        this.isInJail = false;
        this.properties = new ArrayList<>();
        this.afterRoll = false;
        this.position = null;
    }

    private int id; //todo implement in service
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
