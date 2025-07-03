package com.dld.monopoly.model.dto;

import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.RentableProperty;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class PlayerDTO {
    private int id;
    private String nickname;
    private int[] dices;
    private int money;
    private int playerIndex;
    private int allDiceRollsInThisTour;
    private int doubletCounter;
    private Field position;
    private boolean isInJail;
    private List<RentableProperty> propertiesDTO;
    private boolean afterRoll;
}
