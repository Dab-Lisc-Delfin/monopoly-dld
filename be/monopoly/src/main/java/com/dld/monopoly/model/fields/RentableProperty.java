package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentableProperty extends Field {


    protected RentableProperty(int id, String name, FieldType fieldType,int price) { //todo keep price?
        super(id, name, fieldType);
        this.owner = null;
        this.price = price;
        this.isAvailable = true;
    }

    protected RentableProperty(int id, String name, FieldType fieldType) {
        super(id, name, fieldType);
        this.owner = null;
        this.isAvailable = true;
    }


    public int getRentCost(int dicesAmount) {
        return -1;
    }

    public int getRentCost() {
        return -1;
    }

    protected Player owner;
    protected int price;
    protected boolean isAvailable;

}
