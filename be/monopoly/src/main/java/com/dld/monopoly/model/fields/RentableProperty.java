package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;

abstract class RentableProperty extends Field {
    protected RentableProperty(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType);
        this.owner = null;
        this.price = price;
    }

    protected Player owner;
    protected final int price;

    abstract int getRentCost();
}
