package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentableProperty extends Field {
    protected RentableProperty(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType);
        this.ownerId = 0;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRentCost(int dicesAmount) {
        return -1;
    }

    public int getRentCost() {
        return -1;
    }

    protected int ownerId;
    protected int price;
    protected boolean isAvailable;

}
