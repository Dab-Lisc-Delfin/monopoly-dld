package com.dld.monopoly.model.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentialProperty extends RentableProperty {

    public ResidentialProperty(int id, String name, FieldColor color, int price, int rent, int rentWithColorSet, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel, int housesCost, int hotelCost) {
        super(id, name, FieldType.PROPERTY, price);
        this.rent = rent;
        this.color = color;
        this.housesCost = housesCost;
        this.rentWith1House = rentWith1House;
        this.rentWith2House = rentWith2House;
        this.rentWith3House = rentWith3House;
        this.rentWith4House = rentWith4House;
        this.rentWithHotel = rentWithHotel;
        this.rentWithColorSet = rentWithColorSet;
        this.hotelCost = hotelCost;

        this.housesAmount = 0;
        this.hotelsAmount = 0;
    }

    private final FieldColor color;
    private final int rent;
    private final int rentWithColorSet;
    private final int rentWith1House;
    private final int rentWith2House;
    private final int rentWith3House;
    private final int rentWith4House;
    private final int rentWithHotel;

    private final int housesCost;
    private final int hotelCost;

    private int housesAmount;
    private int hotelsAmount;

    public int getRentCost() {
        if (hotelsAmount == 1) {
            return rentWithHotel;
        } else if (housesAmount == 4) {
            return rentWith4House;
        } else if (housesAmount == 3) {
            return rentWith3House;
        } else if (housesAmount == 2) {
            return rentWith2House;
        } else if (housesAmount == 1) {
            return rentWith1House;
        } else if (checkIfOwnerHasColorSet()) {
            return rentWithColorSet;
        } else {
            return rent;
        }

    }


    private boolean checkIfOwnerHasColorSet() {
        int propertiesWithSameColor = 0;

        for (RentableProperty property : owner.getProperties()) {
            if (property.getFieldType().equals(FieldType.PROPERTY)) {
                ResidentialProperty residentialProperty = (ResidentialProperty) property;

                if (residentialProperty.getColor().equals(this.color)) {
                    propertiesWithSameColor++;
                }
            }
        }


        switch (this.color) {
            case FieldColor.BROWN, FieldColor.DARK_BLUE -> {
                return propertiesWithSameColor == 2;
            }
            case FieldColor.LIGHT_BLUE, FieldColor.PINK, FieldColor.ORANGE, FieldColor.RED, FieldColor.YELLOW, FieldColor.GREEN -> {
                return propertiesWithSameColor == 3;
            }
            default -> {
                return false;
            }
        }


    }
}
