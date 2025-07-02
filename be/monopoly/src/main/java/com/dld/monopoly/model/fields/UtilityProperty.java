package com.dld.monopoly.model.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilityProperty extends RentableProperty {
    public UtilityProperty(int id, String name) {
        super(id, name, FieldType.UTILITY, 150);
    }

//    public int getRentCost(int dicesAmount) {
//
//        if (dicesAmount < 3 || dicesAmount > 11) {
//            throw new IllegalArgumentException("Incorrect dices amount. " + dicesAmount);
//        }
//
//        if (checkIfOwnerHasBothUtilities()) {
//            return dicesAmount * 10;
//        } else {
//            return dicesAmount * 4;
//        }
//    }


//    private boolean checkIfOwnerHasBothUtilities() {
//        int ownedUtilityProperties = 0;
//
//        for (Field property : owner.getProperties()) { //todo findPlayerById
//            if (property.getFieldType().equals(FieldType.UTILITY)) {
//                ownedUtilityProperties++;
//            }
//        }
//
//        if (ownedUtilityProperties == 2) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
