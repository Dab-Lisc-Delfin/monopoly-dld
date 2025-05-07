package com.dld.monopoly.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

    public Card(String name) {
        this.name = name;
    }

    private String name;
}
