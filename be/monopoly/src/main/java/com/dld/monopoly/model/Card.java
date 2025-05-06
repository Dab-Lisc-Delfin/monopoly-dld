package com.dld.monopoly.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private String name;
    private String description;
}
