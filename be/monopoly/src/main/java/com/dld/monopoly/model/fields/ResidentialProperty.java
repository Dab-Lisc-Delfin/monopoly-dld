package com.dld.monopoly.model.fields;

public class ResidentialProperty extends PropertyField implements Rentable {

    public ResidentialProperty(int id, String name, FieldType fieldType, FieldColor color, int price, int rent, int rentWithColorSet, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel, int housesCost, int hotelsCost) {
        super(id, name, fieldType, price);
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

        this.housesAmount = 0;
        this.hotelsAmount = 0;
    }

    private FieldColor color;
    private int rent;
    private int rentWithColorSet;
    private int rentWith1House;
    private int rentWith2House;
    private int rentWith3House;
    private int rentWith4House;
    private int rentWithHotel;

    private int housesCost;
    private int hotelsCost;
    private int housesAmount;
    private int hotelsAmount;


    @Override
    public int getRentCost() {
        return 0;//todo
    }
}
