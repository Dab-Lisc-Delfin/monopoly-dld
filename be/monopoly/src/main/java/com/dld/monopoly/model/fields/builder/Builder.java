package com.dld.monopoly.model.fields.builder;

public interface Builder {
    void reset();

    void setId();

    void setName();


    void setPrice();

    void setFieldColor();

    void setRentPrices(int rent, int rentWithColorSet, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel);

    void setBuildingPrices(int housesCost, int hotelCost);
}
