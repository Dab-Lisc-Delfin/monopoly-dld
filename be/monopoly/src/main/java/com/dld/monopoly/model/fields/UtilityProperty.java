package com.dld.monopoly.model.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilityProperty extends RentableProperty {
    //rename
    public UtilityProperty(int id, String name) {
        super(id, name, FieldType.UTILITY, 150);
        this.hasBothUtilities = false;
    }

    private boolean hasBothUtilities;

    public int getRentCost(int dicesAmount) {

        if (dicesAmount < 3 || dicesAmount > 11) {
            throw new IllegalArgumentException("Incorrect dices amount. " + dicesAmount);
        }

        if (hasBothUtilities == true) {
            return dicesAmount * 10;
        } else {
            return dicesAmount * 4;
        }
    }
}
