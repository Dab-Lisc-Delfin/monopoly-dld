package com.dld.monopoly.model.fields.builder;

import com.dld.monopoly.model.fields.FieldColor;
import com.dld.monopoly.model.fields.ResidentialProperty;

public class ResidentalPropertyBuilder implements Builder {

    private int id;
    private String name;
    private int price;
    private FieldColor color;
    private int rent;
    private int rentWithColorSet;
    private int rentWith1House;
    private int rentWith2House;
    private int rentWith3House;
    private int rentWith4House;
    private int rentWithHotel;
    private int housesCost;
    private int hotelCost;


    @Override
    public Builder setId(int id) {
        this.id = id;
        return this;
    }


    @Override
    public Builder setName(String name) {
        this.name = name;
        return this;
    }


    @Override
    public Builder setPrice(int price) {
        this.price = price;
        return this;
    }


    @Override
    public Builder setFieldColor(FieldColor color) {
        this.color = color;
        return this;
    }

    @Override
    public Builder setRentPrices(int rent, int rentWithColorSet, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel) {
        this.rent = rent;
        this.rentWithColorSet = rentWithColorSet;
        this.rentWith1House = rentWith1House;
        this.rentWith2House = rentWith2House;
        this.rentWith3House = rentWith3House;
        this.rentWith4House = rentWith4House;
        this.rentWithHotel = rentWithHotel;
        return this;
    }


    @Override
    public Builder setBuildingPrices(int housesCost, int hotelCost) {
        this.housesCost = housesCost;
        this.hotelCost = hotelCost;
        return this;
    }


    @Override
    public ResidentialProperty createObject() {
        return new ResidentialProperty(id, name, color, price, rent, rentWithColorSet, rentWith1House, rentWith2House, rentWith3House, rentWith4House, rentWithHotel, housesCost, hotelCost);
    }

}