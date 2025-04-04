package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyField extends Field {

    public PropertyField(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType);
        this.color = color;
        this.price = price;
        this.isAvailable = true;
        this.owner = null;
    }


    private FieldColor color;
    private int price;
    private boolean isAvailable;
    private Player owner;
}
