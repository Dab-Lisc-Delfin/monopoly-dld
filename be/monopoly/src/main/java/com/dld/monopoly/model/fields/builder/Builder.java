package com.dld.monopoly.model.fields.builder;

import com.dld.monopoly.model.fields.FieldColor;
import com.dld.monopoly.model.fields.ResidentialProperty;

public interface Builder {
    Builder setId(int id);

    Builder setName(String name);

    Builder setPrice(int price);

    Builder setFieldColor(FieldColor color);

    Builder setRentPrices(int rent, int rentWithColorSet, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel);

    Builder setBuildingPrices(int housesCost, int hotelCost);

    ResidentialProperty createObject();
}
