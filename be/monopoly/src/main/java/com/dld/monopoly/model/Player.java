package com.dld.monopoly.model;

import com.dld.monopoly.model.fields.Field;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String nickname;
    private int[] dices;
    private  int money;
    private int playerIndex;
    private Field position;
}
