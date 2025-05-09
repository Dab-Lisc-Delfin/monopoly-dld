package com.dld.monopoly.model.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RailroadProperty extends RentableProperty {
    public RailroadProperty(int id, String name) {
        super(id, name, FieldType.RAILROADS, 200);
        this.isAvailable = true;
        this.owner = null;
        this.oneRailroadRent = 25;
        this.twoRailroadRent = 50;
        this.threeRailroadRent = 100;
        this.fourRailroadRent = 200;
    }

    private final int oneRailroadRent;
    private final int twoRailroadRent;
    private final int threeRailroadRent;
    private final int fourRailroadRent;
    private boolean isAvailable;

    public int getRentCost() {
        int ownedRailroads = getAmountOwnedRailroads();

        if (ownedRailroads == 4) {
            return fourRailroadRent;
        } else if (ownedRailroads == 3) {
            return threeRailroadRent;
        } else if (ownedRailroads == 2) {
            return twoRailroadRent;
        } else {
            return oneRailroadRent;
        }
    }

    private int getAmountOwnedRailroads() {
        int ownedRailroads = 0;

        for (Field property : owner.getProperties()) {
            if (property.getFieldType().equals(FieldType.RAILROADS)) {
                ownedRailroads++;
            }
        }
        return ownedRailroads;
    }
}
