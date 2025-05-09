package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentableProperty extends Field {
    protected RentableProperty(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType);
        this.owner = null;
        this.price = price;
        this.isAvailable = true;
    }

    protected Player owner;
    protected final int price;
    protected boolean isAvailable;

}
