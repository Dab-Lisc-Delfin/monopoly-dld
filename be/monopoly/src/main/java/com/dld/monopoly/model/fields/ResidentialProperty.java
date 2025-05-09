package com.dld.monopoly.model.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentialProperty extends RentableProperty {

    public ResidentialProperty(int id, String name, FieldColor color, int price, int rent, int rentWithColorSet, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel, int housesCost, int hotelsCost) {
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
        this.hotelsCost = hotelsCost;

        this.hasColorSet = false;
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
    private final int hotelsCost;

    private boolean hasColorSet;
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
        } else if (hasColorSet == true) {
            return rentWithColorSet;
        } else {
            return rent;
        }

    }
}
