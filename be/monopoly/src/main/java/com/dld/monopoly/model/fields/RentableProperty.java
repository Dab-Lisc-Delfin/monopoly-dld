package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class RentableProperty extends Field {
    protected RentableProperty(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType);
//        this.owner = null;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRentCost(int dicesAmount) {
        return -1;
    }

    public int getRentCost() {
        return -1;
    }
    @JsonIgnore
    protected Player owner;
    protected int price;
    protected boolean isAvailable;

}
