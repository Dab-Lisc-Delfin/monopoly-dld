package com.dld.monopoly.model.fields.builder;

import com.dld.monopoly.model.fields.ResidentialProperty;

public class ResidentalPropertyBuilder implements Builder {

    private ResidentialProperty residentialProperty;

    @Override
    public void reset() {
        this.residentialProperty = new ResidentialProperty();
    }

    @Override
    public void setId() {

    }

    @Override
    public void setName() {

    }

    @Override
    public void setPrice() {

    }

    @Override
    public void setFieldColor() {

    }

    @Override
    public void setRentPrices(int rent, int rentWithColorSet, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel) {

    }

    @Override
    public void setBuildingPrices(int housesCost, int hotelCost) {

    }
}
